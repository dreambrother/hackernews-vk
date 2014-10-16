package com.github.dreambrother.hackernews.client;

import com.github.dreambrother.hackernews.dto.HackernewsItem;

import java.util.List;

public interface HackernewsClient {

    List<Long> fetchNewsIds();
    HackernewsItem fetchNewsItem(Long id);
}
