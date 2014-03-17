package com.github.dreambrother.hackernews.worker;

import com.github.dreambrother.hackernews.client.VkClient;
import com.github.dreambrother.hackernews.service.HackernewsService;
import com.github.dreambrother.hackernews.service.PublishedItemsService;
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
    private HackernewsService hackernewsServiceMock;
    @Mock
    private PublishedItemsService publishedItemsServiceMock;
    @Mock
    private VkClient vkClientMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        sut.setHackernewsService(hackernewsServiceMock);
        sut.setPublishedItemsService(publishedItemsServiceMock);
        sut.setVkClient(vkClientMock);
        sut.setPostingDelayMillis(0);
    }

    @Test
    public void publishingScenario() {
        when(hackernewsServiceMock.getMostPopularNews()).thenReturn(threeItems());
        when(publishedItemsServiceMock.getLastPublishedItems()).thenReturn(twoItems());

        sut.run();

        verify(hackernewsServiceMock).getMostPopularNews();
        verify(publishedItemsServiceMock).getLastPublishedItems();
        verify(vkClientMock).publish(threeItems().get(2));
        verify(publishedItemsServiceMock).saveLastPublishedItems(threeItems());
    }
}