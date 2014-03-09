package com.github.dreambrother.hackernews;

import com.github.dreambrother.hackernews.http.PingServer;
import com.github.dreambrother.hackernews.wd.WatchdogPingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HackernewsVkApp {

    private static final Logger log = LoggerFactory.getLogger(HackernewsVkApp.class);

    public static void main(String[] args) {
        log.info("Start hackernews-vk publicator application");

        HackernewsVkApp app = new HackernewsVkApp();
        app.startWdListener();
        app.registerShutdownHook();
        app.startApplication();
    }

    private void startApplication() {
        // TODO: stub implementation
        while (true) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void startWdListener() {
        PingServer server = new PingServer();
        server.setHandler(new WatchdogPingHandler());
        server.setPort(10000);

        log.info("Start PingServer...");
        server.start();
    }

    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                log.info("Just before shutdown the programm");
            }
        }));
    }
}
