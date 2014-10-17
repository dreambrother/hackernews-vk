package com.github.dreambrother.hackernews.service;

import com.github.dreambrother.hackernews.dao.PublishedItemsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PublishedItemsServiceImpl implements PublishedItemsService {

    private static final Logger log = LoggerFactory.getLogger(PublishedItemsServiceImpl.class);

    private PublishedItemsDao publishedItemsDao;
    private int limit;

    @Override
    public List<Long> getLastPublishedIds() {
        return publishedItemsDao.getLastPublishedIds();
    }

    @Override
    public void saveNewPublishedIds(List<Long> newPublishedIds) {
        if (newPublishedIds.isEmpty()) {
            log.info("Nothing to save");
            return;
        }

        List<Long> lastPublishedIds = publishedItemsDao.getLastPublishedIds();

        newPublishedIds = new ArrayList<>(newPublishedIds);
        newPublishedIds.addAll(lastPublishedIds);

        int saveLimit = limit < newPublishedIds.size() ? limit : newPublishedIds.size();
        log.info("Save {} published items", saveLimit);

        List<Long> idsToSave = newPublishedIds.subList(0, saveLimit);
        publishedItemsDao.saveLastPublishedIds(idsToSave);
    }

    public void setPublishedItemsDao(PublishedItemsDao publishedItemsDao) {
        this.publishedItemsDao = publishedItemsDao;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
