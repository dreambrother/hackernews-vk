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
    public void saveNewPublishedIds(List<Long> ids) {
        if (ids.isEmpty()) {
            log.info("Nothing to save");
            return;
        }

        List<Long> lastPublishedIds = new ArrayList<>(publishedItemsDao.getLastPublishedIds());
        lastPublishedIds.addAll(ids);

        int saveLimit = limit < lastPublishedIds.size() ? limit : lastPublishedIds.size();
        log.info("Save {} published items", saveLimit);

        List<Long> idsToSave = lastPublishedIds.subList(0, saveLimit);
        publishedItemsDao.saveLastPublishedIds(idsToSave);
    }

    public void setPublishedItemsDao(PublishedItemsDao publishedItemsDao) {
        this.publishedItemsDao = publishedItemsDao;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
