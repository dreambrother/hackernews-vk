package com.github.dreambrother.hackernews.worker;

import com.github.dreambrother.hackernews.client.VkClient;
import com.github.dreambrother.hackernews.dto.HackernewsItem;
import com.github.dreambrother.hackernews.service.HackernewsService;
import com.github.dreambrother.hackernews.service.PublishedItemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Publicator implements Runnable {

    public static final Logger log = LoggerFactory.getLogger(Publicator.class);

    private VkClient vkClient;
    private HackernewsService hackernewsService;
    private PublishedItemsService publishedItemsService;

    private int postingDelayMillis;

    @Override
    public void run() {
        log.info("Publish new items to VK");
        try {
            publishNews();
        } catch (Exception ex) {
            log.error("Cannot publish news", ex);
        }
    }

    private void publishNews() {
        List<HackernewsItem> mostPopularNews = hackernewsService.getMostPopularNonPublishedNews();

        log.info("Publish {} news", mostPopularNews.size());
        for (HackernewsItem news : mostPopularNews) {
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
        publish(news);
    }

    private void publish(HackernewsItem news) {
        try {
            vkClient.publish(news);
            publishedItemsService.saveNewPublishedId(news.getId());
        } catch (Exception ex) {
            log.warn("Error while publishing {}, skip it", news);
        }
    }

    public void setVkClient(VkClient vkClient) {
        this.vkClient = vkClient;
    }

    public void setHackernewsService(HackernewsService hackernewsService) {
        this.hackernewsService = hackernewsService;
    }

    public void setPublishedItemsService(PublishedItemsService publishedItemsService) {
        this.publishedItemsService = publishedItemsService;
    }

    public void setPostingDelayMillis(int postingDelayMillis) {
        this.postingDelayMillis = postingDelayMillis;
    }
}
