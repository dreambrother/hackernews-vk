package com.github.dreambrother.hackernews.service;

import com.github.dreambrother.hackernews.client.HackernewsClient;
import com.github.dreambrother.hackernews.dto.HackernewsItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HackernewsServiceImpl implements HackernewsService {

    private int minPopularity;
    private HackernewsClient hackernewsClient;

    @Override
    public List<HackernewsItem> getMostPopularNews() {
        List<HackernewsItem> hackernewsItems = hackernewsClient.fetchNews();
        Map<String, Integer> ratings = hackernewsClient.fetchRatings();

        List<HackernewsItem> filtered = new ArrayList<>();
        for (HackernewsItem news : hackernewsItems) {
            Integer rating = ratings.get(news.getTitle());
            if (rating != null && rating >= minPopularity) {
                filtered.add(news);
            }
        }
        return filtered;
    }

    public void setMinPopularity(int minPopularity) {
        this.minPopularity = minPopularity;
    }

    public void setHackernewsClient(HackernewsClient hackernewsClient) {
        this.hackernewsClient = hackernewsClient;
    }
}