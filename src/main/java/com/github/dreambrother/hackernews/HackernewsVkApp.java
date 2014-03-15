package com.github.dreambrother.hackernews;

import com.github.dreambrother.hackernews.client.HackernewsClientImpl;
import com.github.dreambrother.hackernews.client.VkClientImpl;
import com.github.dreambrother.hackernews.dao.PublishedItemsDaoImpl;
import com.github.dreambrother.hackernews.http.PingServer;
import com.github.dreambrother.hackernews.wd.WatchdogPingHandler;
import com.github.dreambrother.hackernews.worker.Publicator;
import com.github.dreambrother.hackernews.worker.ScheduledPublicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class HackernewsVkApp {

    private static final Logger log = LoggerFactory.getLogger(HackernewsVkApp.class);

    public static void main(String[] args) throws InterruptedException {
        log.info("Start hackernews-vk publicator application");

        HackernewsVkApp app = new HackernewsVkApp();
        Properties properties = app.getProperties();

        VkClientImpl vkClient = new VkClientImpl();
        vkClient.setCommunityId(properties.getProperty("vk.communityId"));
        vkClient.setToken(properties.getProperty("vk.token"));

        HackernewsClientImpl hackernewsClient = new HackernewsClientImpl();

        PublishedItemsDaoImpl publishedItemsDao = new PublishedItemsDaoImpl();
        publishedItemsDao.setDbFile(new File(properties.getProperty("db.file")));

        Publicator publicator = new Publicator();
        publicator.setVkClient(vkClient);
        publicator.setPublishedItemsDao(publishedItemsDao);
        publicator.setHackernewsClient(hackernewsClient);
        publicator.setPostingDelayMillis(Integer.parseInt("publicator.delayBetweenPostingMillis"));

        ScheduledPublicator scheduledPublicator = new ScheduledPublicator();

        app.registerShutdownHook(scheduledPublicator);
        app.startWdListener(Integer.parseInt(properties.getProperty("wd.ping.port")));

        scheduledPublicator.start(publicator, Integer.parseInt(properties.getProperty("publicator.schedulerDelayMillis")));

        log.info("Application started");
    }

    private void startWdListener(int port) {
        PingServer server = new PingServer();
        server.setHandler(new WatchdogPingHandler());
        server.setPort(port);

        log.info("Start PingServer...");
        server.start();
    }

    private void registerShutdownHook(final ScheduledPublicator scheduledPublicator) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                log.info("Just before shutdown the programm");
                scheduledPublicator.shutdown();
            }
        }));
    }

    public Properties getProperties() {
        try {
            Properties properties = new Properties();
            if (System.getProperty("os.name").equals("Ubuntu")) {
                log.info("Start with PROD properties");
                properties.load(new FileInputStream("/etc/app.properties"));
            } else {
                log.info("Start with DEV properties");
                properties.load(getClass().getResourceAsStream("/app.properties"));
            }
            return properties;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
