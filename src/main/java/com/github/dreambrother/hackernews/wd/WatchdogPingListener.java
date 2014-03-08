package com.github.dreambrother.hackernews.wd;

import com.github.dreambrother.hackernews.io.StdIO;

public class WatchdogPingListener {

    public static final String RESPONSE_MESSAGE = "pong";

    private StdIO stdIO;

    public void setStdIO(StdIO stdIO) {
        this.stdIO = stdIO;
    }

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
        stdIO.read();
        stdIO.write(RESPONSE_MESSAGE);
    }
}
