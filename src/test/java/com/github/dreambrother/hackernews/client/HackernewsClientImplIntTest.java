package com.github.dreambrother.hackernews.client;

import com.github.dreambrother.hackernews.dto.HackernewsItem;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HackernewsClientImplIntTest {

    private static final String HACKERNEWS_URL = "https://hacker-news.firebaseio.com/v0";

    private HackernewsClientImpl sut = new HackernewsClientImpl();

    @Before
    public void setUp() throws Exception {
        sut.setHackernewsUrl(HACKERNEWS_URL);
        sut.setFetchLimit(100);
    }

    @Test
    public void shouldFetchNews() {
        List<Long> items = sut.fetchNewsIds();
        assertFalse(items.isEmpty());
    }

    @Test
    public void shouldFetchNewsItem() {
        HackernewsItem actual = sut.fetchNewsItem(8466437L);
        assertNotEmpty(actual);
    }

    @Test
    public void shouldFetchNewsWithLimit() {
        int limit = 10;
        HackernewsClientImpl sutWithSmallLimit = new HackernewsClientImpl();
        sutWithSmallLimit.setHackernewsUrl(HACKERNEWS_URL);
        sutWithSmallLimit.setFetchLimit(limit);

        List<Long> actual = sutWithSmallLimit.fetchNewsIds();

        assertEquals(limit, actual.size());
    }

    private void assertNotEmpty(HackernewsItem item) {
        assertTrue(item.getId() > 0);
        assertTrue(notEmpty(item.getTitle()));
        assertTrue(notEmpty(item.getUrl()));
    }

    private boolean notEmpty(String str) {
        return !"".equals(str);
    }
}
