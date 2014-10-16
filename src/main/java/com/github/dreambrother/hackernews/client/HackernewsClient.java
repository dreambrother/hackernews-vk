package com.github.dreambrother.hackernews.client;

import com.github.dreambrother.hackernews.dto.HackernewsItem;

import java.util.List;
import java.util.Map;

public interface HackernewsClient {

    @Deprecated
    List<HackernewsItem> fetchNews();
    @Deprecated
    Map<String, Integer> fetchRatings();

    List<Long> fetchNewsIds();
    HackernewsItem fetchNewsItem(Long id);
}
