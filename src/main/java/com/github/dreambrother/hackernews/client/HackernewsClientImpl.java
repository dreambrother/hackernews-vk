package com.github.dreambrother.hackernews.client;

import com.github.dreambrother.hackernews.dto.HackernewsItem;
import com.github.dreambrother.hackernews.dto.HackernewsResponse;
import com.github.dreambrother.hackernews.exceptions.RuntimeIOException;
import com.github.dreambrother.hackernews.utils.HackernewsHtmlUtils;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class HackernewsClientImpl implements HackernewsClient {

    private static final Logger log = LoggerFactory.getLogger(HackernewsClientImpl.class);

    @Override
    public List<HackernewsItem> fetchNews() {
        log.info("Try to fetch news");
        try {
            InputStream response = Request.Get("https://news.ycombinator.com/rss").execute().returnContent().asStream();
            return unmarshall(response).getChannel().getItems();
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }

    @Override
    public Map<String, Integer> fetchRatings() {
        log.info("Try to fetch ratings");
        try {
            String html = Request.Get("https://news.ycombinator.com").execute().returnContent().asString();
            return HackernewsHtmlUtils.parseRating(html);
        } catch (IOException ex) {
            throw new RuntimeIOException(ex);
        }
    }

    private HackernewsResponse unmarshall(InputStream response) {
        return JAXB.unmarshal(response, HackernewsResponse.class);
    }
}
