package com.github.dreambrother.hackernews.client;

import com.github.dreambrother.hackernews.dto.HackernewsItem;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class HackernewsClientImplIntTest {

    private HackernewsClientImpl sut = new HackernewsClientImpl();

    @Test
    public void shouldUnmarshallHackernewsResponse() {
        List<HackernewsItem> items = sut.fetchNews();
        assertNotEmpty(items);
    }

    private void assertNotEmpty(List<HackernewsItem> items) {
        assertTrue(items != null && !items.isEmpty());

        HackernewsItem item = items.get(0);
        assertTrue(notEmpty(item.getTitle()));
        assertTrue(notEmpty(item.getLink()));
        assertTrue(notEmpty(item.getCommentsLink()));
    }

    private boolean notEmpty(String str) {
        return !"".equals(str);
    }
}
