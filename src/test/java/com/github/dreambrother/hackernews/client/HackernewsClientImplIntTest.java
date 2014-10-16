package com.github.dreambrother.hackernews.client;

import com.github.dreambrother.hackernews.dto.HackernewsItem;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HackernewsClientImplIntTest {

    private HackernewsClientImpl sut = new HackernewsClientImpl();

    @Before
    public void setUp() throws Exception {
        sut.setHackernewsUrl("https://hacker-news.firebaseio.com/v0");
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

    private void assertNotEmpty(HackernewsItem item) {
        assertTrue(item.getId() > 0);
        assertTrue(notEmpty(item.getTitle()));
        assertTrue(notEmpty(item.getUrl()));
    }

    private boolean notEmpty(String str) {
        return !"".equals(str);
    }
}
