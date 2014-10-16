package com.github.dreambrother.hackernews.dao;

import java.util.List;

public interface PublishedItemsDao {

    List<Long> getLastPublishedIds();
    void saveLastPublishedIds(List<Long> ids);
}
