package com.github.dreambrother.hackernews.http;

import com.github.dreambrother.hackernews.exceptions.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class PingServer {

    private static final Logger log = LoggerFactory.getLogger(PingServer.class);

    private static final String RESPONSE_TEMPLATE =
                    "HTTP/1.1 %s %n" +
                    "Content-Type: text/html;charset=utf-8%n" +
                    "Content-Length: %d%n%n" +
                    "%s";

    private HttpGetHandler handler;
    private int port;

    public void start() {
        Thread serverThread = new Thread(serverRunnable());
        serverThread.setDaemon(true);
        serverThread.start();
    }

    public void stop() {
        Thread.currentThread().interrupt();
    }

    private Runnable serverRunnable() {
        return new Runnable() {
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(port)){
                    while (!Thread.currentThread().isInterrupted()) {
                        try (Socket clientSocket = serverSocket.accept()) {
                            handle(clientSocket);
                        }
                    }
                    log.info("Server was interrupted... shutdown now");
                } catch (IOException e) {
                    log.error("PingServer doesn't start", e);
                }
            }
        };
    }

    private void handle(Socket clientSocket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String request = reader.readLine();

            log.debug("Handle request: {}", request);
            String body = handler.handle(getUrl(request));
            writeResponse("200 OK", body, clientSocket);
        } catch (IOException e) {
            log.warn("Cannot read from socket!");
        } catch (InvalidRequestException e) {
            writeResponse("400 BAD REQUEST", "", clientSocket);
        } catch (Exception ex) {
            writeResponse("500 INTERNAL SERVER ERROR", "", clientSocket);
        }
    }

    private void writeResponse(String code, String body, Socket to) {
        try {
            String fullResponse = String.format(RESPONSE_TEMPLATE, code, body.getBytes().length, body);
            to.getOutputStream().write(fullResponse.getBytes());
        } catch (IOException e) {
            log.warn("Cannot write to socket");
        }
    }

    private String getUrl(String request) {
        String[] splittedRequest = request.split(" ");
        if (splittedRequest.length < 2) {
            throw new InvalidRequestException("Invalid request " + request);
        }
        return splittedRequest[1];
    }

    public void setHandler(HttpGetHandler handler) {
        this.handler = handler;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
