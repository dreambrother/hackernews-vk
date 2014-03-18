package com.github.dreambrother.hackernews.service;

import com.github.dreambrother.hackernews.dao.PublishedItemsDao;
import com.github.dreambrother.hackernews.dto.HackernewsItem;

import java.util.List;

public class PublishedItemsServiceImpl implements PublishedItemsService {

    private PublishedItemsDao publishedItemsDao;
    private int limit;

    @Override
    public List<HackernewsItem> getLastPublishedItems() {
        return publishedItemsDao.getLastPublishedItems();
    }

    @Override
    public void saveLastPublishedItems(List<HackernewsItem> hackernewsItems) {
        List<HackernewsItem> itemsToSave = hackernewsItems.subList(0, limit);
        publishedItemsDao.saveLastPublishedItems(itemsToSave);
    }

    public void setPublishedItemsDao(PublishedItemsDao publishedItemsDao) {
        this.publishedItemsDao = publishedItemsDao;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
