package com.github.dreambrother.hackernews.service;

import com.github.dreambrother.hackernews.dto.HackernewsItem;

import java.util.List;

public interface HackernewsService {

    List<HackernewsItem> getMostPopularNonPublishedNews();
}
