package com.github.dreambrother.hackernews.dao;

import com.github.dreambrother.hackernews.dto.HackernewsItem;

import java.util.List;

public interface PublishedItemsDao {

    @Deprecated
    List getLastPublishedItems();
    @Deprecated
    void saveLastPublishedItems(List<HackernewsItem> items);

    List<Long> getLastPublishedIds();
    void saveLastPublishedIds(List<Long> ids);
}
