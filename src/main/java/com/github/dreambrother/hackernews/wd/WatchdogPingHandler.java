package com.github.dreambrother.hackernews.wd;

public class WatchdogPingHandler {

    public static final String RESPONSE_MESSAGE = "pong";

    public void start() {
        startListenerDaemonThread();
    }

    private void startListenerDaemonThread() {
        Thread listenerThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    waitForPingAndResponse();
                }
            }
        });
        listenerThread.setDaemon(true);
        listenerThread.start();
    }

    private void waitForPingAndResponse() {
    }
}
