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

    @Override
    public void run() {
        log.info("Publish new items to VK");

        List<HackernewsItem> items = hackernewsClient.fetchNews();
        List<HackernewsItem> publishedNews = publishedItemsDao.getLastPublishedItems();

        List<HackernewsItem> itemsToPublish = new ArrayList<>(items);
        itemsToPublish.removeAll(publishedNews);

        publish(itemsToPublish);
        publishedItemsDao.saveLastPublishedItems(items);
    }

    private void publish(List<HackernewsItem> newsList) {
        for (HackernewsItem news : newsList) {
            try {
                vkClient.publish(news);
            } catch (RuntimeException ex) {
                log.error("Cannot publish news", ex);
            }
        }
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
}
