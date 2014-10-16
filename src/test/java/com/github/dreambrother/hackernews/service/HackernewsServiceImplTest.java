package com.github.dreambrother.hackernews.service;

import com.github.dreambrother.hackernews.client.HackernewsClient;
import com.github.dreambrother.hackernews.dao.PublishedItemsDao;
import com.github.dreambrother.hackernews.dto.HackernewsItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static com.github.dreambrother.hackernews.fixture.HackernewsItems.twoItems;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class HackernewsServiceImplTest {

    private HackernewsServiceImpl sut = new HackernewsServiceImpl();

    @Mock
    private HackernewsClient hackernewsClientMock;
    @Mock
    private PublishedItemsDao publishedItemsDaoMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        sut.setMinPopularity(50);
        sut.setHackernewsClient(hackernewsClientMock);
        sut.setPublishedItemsDao(publishedItemsDaoMock);
    }

    @Test
    public void shouldGetMostPopularNews() {
        when(hackernewsClientMock.fetchNewsIds()).thenReturn(Arrays.asList(1l, 2l, 3l));
        when(publishedItemsDaoMock.getLastPublishedIds()).thenReturn(Arrays.asList(1l));
        when(hackernewsClientMock.fetchNewsItem(2l)).thenReturn(twoItems().get(0));
        when(hackernewsClientMock.fetchNewsItem(3l)).thenReturn(twoItems().get(1));

        List<HackernewsItem> mostPopularNews = sut.getMostPopularNonPublishedNews();

        assertEquals(Arrays.asList(twoItems().get(0)), mostPopularNews);
    }
}
