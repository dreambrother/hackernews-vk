package com.github.dreambrother.hackernews.worker;

import com.github.dreambrother.hackernews.client.HackernewsClient;
import com.github.dreambrother.hackernews.client.VkClient;
import com.github.dreambrother.hackernews.dao.PublishedItemsDao;
import com.github.dreambrother.hackernews.dto.HackernewsItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Publicator implements Runnable {

    public static final Logger log = LoggerFactory.getLogger(Publicator.class);

    private HackernewsClient hackernewsClient;
    private VkClient vkClient;
    private PublishedItemsDao publishedItemsDao;
    private int postingDelayMillis;

    @Override
    public void run() {
        log.info("Publish new items to VK");
        try {
            publishNews();
        } catch (RuntimeException ex) {
            log.error("Cannot publish news", ex);
        }
    }

    private void publishNews() {
        List<HackernewsItem> items = hackernewsClient.fetchNews();
        List<HackernewsItem> publishedNews = publishedItemsDao.getLastPublishedItems();

        List<HackernewsItem> itemsToPublish = new ArrayList<>(items);
        itemsToPublish.removeAll(publishedNews);

        publish(itemsToPublish);
        log.info("Save published items");
        publishedItemsDao.saveLastPublishedItems(items);
    }

    private void publish(List<HackernewsItem> newsList) {
        log.info("Publish {} news", newsList.size());
        for (HackernewsItem news : newsList) {
            publishWithInterruptionHandling(news);
        }
        log.info("Finish publishing");
    }

    private void publishWithInterruptionHandling(HackernewsItem news) {
        try {
            log.info("Wait for {} millis before publishing to VK", postingDelayMillis);
            Thread.sleep(postingDelayMillis);
        } catch (InterruptedException ex) {
            log.warn("Seems that app is dying, but we need to finish current publishing anyway!");
            // ignore interruption
        }
        vkClient.publish(news);
    }

    public void setHackernewsClient(HackernewsClient hackernewsClient) {
        this.hackernewsClient = hackernewsClient;
    }

    public void setVkClient(VkClient vkClient) {
        this.vkClient = vkClient;
    }

    public void setPublishedItemsDao(PublishedItemsDao publishedItemsDao) {
        this.publishedItemsDao = publishedItemsDao;
    }

    public void setPostingDelayMillis(int postingDelayMillis) {
        this.postingDelayMillis = postingDelayMillis;
    }
}
