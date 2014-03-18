package com.github.dreambrother.hackernews.service;

import com.github.dreambrother.hackernews.dao.PublishedItemsDao;
import com.github.dreambrother.hackernews.dto.HackernewsItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.github.dreambrother.hackernews.fixture.HackernewsItems.threeItems;
import static com.github.dreambrother.hackernews.fixture.HackernewsItems.twoItems;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PublishedItemsServiceTest {

    private PublishedItemsServiceImpl sut = new PublishedItemsServiceImpl();

    @Mock
    private PublishedItemsDao publishedItemsDaoMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        sut.setPublishedItemsDao(publishedItemsDaoMock);
        sut.setLimit(2);
    }

    @Test
    public void shouldReturnLastPublishedItems() {
        when(publishedItemsDaoMock.getLastPublishedItems()).thenReturn(threeItems());

        List<HackernewsItem> actual = sut.getLastPublishedItems();

        assertEquals(actual, threeItems());
    }

    @Test
    public void shouldSaveLimitedPublishedItems() {
        sut.saveLastPublishedItems(threeItems());

        verify(publishedItemsDaoMock).saveLastPublishedItems(twoItems());
    }
}
