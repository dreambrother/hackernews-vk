package com.github.dreambrother.hackernews.service;

import java.util.List;

public interface PublishedItemsService {

    List<Long> getLastPublishedIds();
    void saveNewPublishedId(long id);
}
