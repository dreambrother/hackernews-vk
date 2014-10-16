package com.github.dreambrother.hackernews.client;

import com.github.dreambrother.hackernews.dto.HackernewsItem;
import com.github.dreambrother.hackernews.exceptions.RuntimeIOException;
import com.github.dreambrother.hackernews.utils.HackernewsJsonUtils;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HackernewsClientImpl implements HackernewsClient {

    private static final Logger log = LoggerFactory.getLogger(HackernewsClientImpl.class);

    private String hackernewsUrl;

    @Override
    public List<HackernewsItem> fetchNews() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Integer> fetchRatings() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Long> fetchNewsIds() {
        log.info("Try to fetch news");
        try {
            String response = Request.Get(hackernewsUrl + "/topstories.json").execute().returnContent().asString();
            return HackernewsJsonUtils.MAPPER.readValue(response, HackernewsJsonUtils.IDS_TYPE);
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }

    @Override
    public HackernewsItem fetchNewsItem(Long id) {
        log.info("Try to fetch news item={}", id);
        try {
            String response = Request.Get(hackernewsUrl + "/item/" + id + ".json").execute().returnContent().asString();
            return HackernewsJsonUtils.MAPPER.readValue(response, HackernewsItem.class);
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }

    public void setHackernewsUrl(String hackernewsUrl) {
        this.hackernewsUrl = hackernewsUrl;
    }
}
