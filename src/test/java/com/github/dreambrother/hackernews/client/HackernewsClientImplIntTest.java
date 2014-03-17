package com.github.dreambrother.hackernews.client;

import com.github.dreambrother.hackernews.dto.HackernewsItem;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class HackernewsClientImplIntTest {

    private HackernewsClientImpl sut = new HackernewsClientImpl();

    @Test
    public void shouldFetchNews() {
        List<HackernewsItem> items = sut.fetchNews();
        assertNotEmpty(items);
    }

    @Test
    public void shouldFetchRatings() {
        Map<String, Integer> ratings = sut.fetchRatings();
        assertNotEmpty(ratings);
    }

    private void assertNotEmpty(List<HackernewsItem> items) {
        assertTrue(items != null && !items.isEmpty());

        HackernewsItem item = items.get(0);
        assertTrue(notEmpty(item.getTitle()));
        assertTrue(notEmpty(item.getLink()));
        assertTrue(notEmpty(item.getCommentsLink()));
    }

    private void assertNotEmpty(Map<String, Integer> ratings) {
        assertTrue(!ratings.isEmpty());
        for (Map.Entry<String, Integer> rating : ratings.entrySet()) {
            assertTrue(!rating.getKey().isEmpty());
            assertTrue(rating.getValue() != null);
            return;
        }
    }

    private boolean notEmpty(String str) {
        return !"".equals(str);
    }
}
