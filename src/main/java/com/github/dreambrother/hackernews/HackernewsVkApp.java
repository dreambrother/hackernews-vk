package com.github.dreambrother.hackernews;

import com.github.dreambrother.hackernews.io.StdIOImpl;
import com.github.dreambrother.hackernews.wd.WatchdogPingListener;
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
        WatchdogPingListener listener = new WatchdogPingListener();
        listener.setStdIO(new StdIOImpl());
        listener.start();
    }

    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                log.info("Just before shutdown the programm");
            }
        }));
    }
}
