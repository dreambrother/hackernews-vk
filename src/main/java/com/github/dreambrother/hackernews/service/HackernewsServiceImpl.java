package com.github.dreambrother.hackernews.service;

import com.github.dreambrother.hackernews.client.HackernewsClient;
import com.github.dreambrother.hackernews.dao.PublishedItemsDao;
import com.github.dreambrother.hackernews.dto.HackernewsItem;

import java.util.ArrayList;
import java.util.List;

public class HackernewsServiceImpl implements HackernewsService {

    private int minPopularity;
    private HackernewsClient hackernewsClient;
    private PublishedItemsDao publishedItemsDao;

    @Override
    public List<HackernewsItem> getMostPopularNonPublishedNews() {
        List<Long> publishedItems = publishedItemsDao.getLastPublishedIds();
        List<Long> nonPoblishedIds = new ArrayList<>(hackernewsClient.fetchNewsIds());
        nonPoblishedIds.removeAll(publishedItems);

        List<HackernewsItem> mostPopularIds = new ArrayList<>();
        for (Long id : nonPoblishedIds) {
            HackernewsItem item = hackernewsClient.fetchNewsItem(id);
            if (item.getScore() >= minPopularity) {
                mostPopularIds.add(item);
            }
        }
        return mostPopularIds;
    }

    public void setPublishedItemsDao(PublishedItemsDao publishedItemsDao) {
        this.publishedItemsDao = publishedItemsDao;
    }

    public void setMinPopularity(int minPopularity) {
        this.minPopularity = minPopularity;
    }

    public void setHackernewsClient(HackernewsClient hackernewsClient) {
        this.hackernewsClient = hackernewsClient;
    }
}
