package com.github.dreambrother.hackernews.worker;

import com.github.dreambrother.hackernews.client.HackernewsClient;
import com.github.dreambrother.hackernews.client.VkClient;
import com.github.dreambrother.hackernews.dao.PublishedItemsDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.github.dreambrother.hackernews.fixture.HackernewsItems.threeItems;
import static com.github.dreambrother.hackernews.fixture.HackernewsItems.twoItems;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PublicatorTest {

    private Publicator sut = new Publicator();

    @Mock
    private HackernewsClient hackernewsClientMock;
    @Mock
    private PublishedItemsDao publishedItemsDaoMock;
    @Mock
    private VkClient vkClientMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        sut.setHackernewsClient(hackernewsClientMock);
        sut.setPublishedItemsDao(publishedItemsDaoMock);
        sut.setVkClient(vkClientMock);
        sut.setPostingDelayMillis(0);
    }

    @Test
    public void publishingScenario() {
        when(hackernewsClientMock.fetchNews()).thenReturn(threeItems());
        when(publishedItemsDaoMock.getLastPublishedItems()).thenReturn(twoItems());

        sut.run();

        verify(hackernewsClientMock).fetchNews();
        verify(publishedItemsDaoMock).getLastPublishedItems();
        verify(vkClientMock).publish(threeItems().get(2));
        verify(publishedItemsDaoMock).saveLastPublishedItems(threeItems());
    }
}