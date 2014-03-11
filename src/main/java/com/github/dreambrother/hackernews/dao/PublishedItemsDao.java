package com.github.dreambrother.hackernews.dao;

import com.github.dreambrother.hackernews.dto.HackernewsItem;

import java.util.List;

public interface PublishedItemsDao {

    List<HackernewsItem> getLastPublishedItems();
    void saveLastPublishedItems(List<HackernewsItem> items);
}
