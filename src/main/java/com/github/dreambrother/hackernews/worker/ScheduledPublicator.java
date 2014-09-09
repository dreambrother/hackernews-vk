package com.github.dreambrother.hackernews.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledPublicator {

    private static final Logger log = LoggerFactory.getLogger(ScheduledPublicator.class);

    private ScheduledExecutorService scheduledExecutorService;

    public void start(Publicator publicator, int delayMillis) {
        log.info("Start scheduled publicator");
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(publicator, 0, delayMillis, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        if (scheduledExecutorService != null) {
            log.info("Shutdown scheduled publicator");
            scheduledExecutorService.shutdown();
            log.info("Wait for termination of scheduled publicator");
            try {
                scheduledExecutorService.awaitTermination(5L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.error("Scheduled publicator termination failed", e);
            }
        } else {
            log.warn("Attempt to shutdown scheduled publicator without starting it!");
        }
    }
}
