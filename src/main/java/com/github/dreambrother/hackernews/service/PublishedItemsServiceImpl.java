package com.github.dreambrother.hackernews.service;

import com.github.dreambrother.hackernews.dao.PublishedItemsDao;
import com.github.dreambrother.hackernews.dto.HackernewsItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PublishedItemsServiceImpl implements PublishedItemsService {

    private static final Logger log = LoggerFactory.getLogger(PublishedItemsServiceImpl.class);

    private PublishedItemsDao publishedItemsDao;
    private int limit;

    @Override
    public List<HackernewsItem> getLastPublishedItems() {
        return publishedItemsDao.getLastPublishedItems();
    }

    @Override
    public void saveLastPublishedItems(List<HackernewsItem> hackernewsItems) {
        int saveLimit = limit < hackernewsItems.size() ? limit : hackernewsItems.size();
        log.info("Save {} published items", saveLimit);

        List<HackernewsItem> itemsToSave = hackernewsItems.subList(0, saveLimit);
        publishedItemsDao.saveLastPublishedItems(itemsToSave);
    }

    public void setPublishedItemsDao(PublishedItemsDao publishedItemsDao) {
        this.publishedItemsDao = publishedItemsDao;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
