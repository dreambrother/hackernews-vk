package com.github.dreambrother.hackernews.client;

import com.github.dreambrother.hackernews.dto.HackernewsItem;

import java.util.List;
import java.util.Map;

public interface HackernewsClient {

    List<HackernewsItem> fetchNews();
    Map<HackernewsItem, Integer> fetchRatings();
}
