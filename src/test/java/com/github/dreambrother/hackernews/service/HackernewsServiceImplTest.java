package com.github.dreambrother.hackernews.service;

import com.github.dreambrother.hackernews.client.HackernewsClient;
import com.github.dreambrother.hackernews.dto.HackernewsItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.dreambrother.hackernews.fixture.HackernewsItems.threeItems;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class HackernewsServiceImplTest {

    private HackernewsServiceImpl sut = new HackernewsServiceImpl();

    @Mock
    private HackernewsClient hackernewsClientMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        sut.setMinPopularity(50);
        sut.setHackernewsClient(hackernewsClientMock);
    }

    @Test
    public void shouldGetMostPopularNews() {
        when(hackernewsClientMock.fetchNews()).thenReturn(threeItems());
        when(hackernewsClientMock.fetchRatings()).thenReturn(withRating(threeItems()));

        List<HackernewsItem> mostPopularNews = sut.getMostPopularNews();

        assertEquals(Arrays.asList(threeItems().get(1), threeItems().get(2)), mostPopularNews);
    }

    private Map<String, Integer> withRating(List<HackernewsItem> items) {
        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < items.size(); i++) {
            result.put(items.get(i).getTitle(), i * 100);
        }
        return result;
    }
}
