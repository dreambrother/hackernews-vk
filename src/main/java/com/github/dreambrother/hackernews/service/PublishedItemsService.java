package com.github.dreambrother.hackernews.service;

import com.github.dreambrother.hackernews.dto.HackernewsItem;

import java.util.List;

public interface PublishedItemsService {

    List<HackernewsItem> getLastPublishedItems();
    void saveLastPublishedItems(List<HackernewsItem> hackernewsItems);
}
