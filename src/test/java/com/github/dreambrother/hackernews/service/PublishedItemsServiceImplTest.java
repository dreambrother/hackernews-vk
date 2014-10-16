package com.github.dreambrother.hackernews.service;

import com.github.dreambrother.hackernews.dao.PublishedItemsDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PublishedItemsServiceImplTest {

    private PublishedItemsServiceImpl sut = new PublishedItemsServiceImpl();

    @Mock
    private PublishedItemsDao publishedItemsDaoMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        sut.setPublishedItemsDao(publishedItemsDaoMock);
        sut.setLimit(20);
    }

    @Test
    public void shouldReturnLastPublishedIds() {
        List<Long> ids = Arrays.asList(1l, 2l, 3l);
        when(publishedItemsDaoMock.getLastPublishedIds()).thenReturn(ids);

        List<Long> actual = sut.getLastPublishedIds();

        assertEquals(ids, actual);
    }

    @Test
    public void shouldSaveLimitedPublishedItems() {
        PublishedItemsServiceImpl sutWithSmallLimit = new PublishedItemsServiceImpl();
        sutWithSmallLimit.setLimit(2);
        sutWithSmallLimit.setPublishedItemsDao(publishedItemsDaoMock);

        when(publishedItemsDaoMock.getLastPublishedIds()).thenReturn(Collections.EMPTY_LIST);
        sutWithSmallLimit.saveNewPublishedIds(Arrays.asList(1l, 2l, 3l));

        verify(publishedItemsDaoMock).saveLastPublishedIds(Arrays.asList(1l, 2l));
    }

    @Test
    public void shouldSaveNewItemsWithAlreadyPublished() {
        when(publishedItemsDaoMock.getLastPublishedIds()).thenReturn(Arrays.asList(1l ,2l));

        sut.saveNewPublishedIds(Arrays.asList(3l ,4l));

        verify(publishedItemsDaoMock).saveLastPublishedIds(Arrays.asList(1l, 2l, 3l, 4l));
    }
}
