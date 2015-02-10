package com.github.dreambrother.hackernews.worker;

import com.github.dreambrother.hackernews.client.VkClient;
import com.github.dreambrother.hackernews.service.HackernewsService;
import com.github.dreambrother.hackernews.service.PublishedItemsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.github.dreambrother.hackernews.fixture.HackernewsItems.twoItems;
import static org.mockito.Mockito.*;

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
        when(hackernewsServiceMock.getMostPopularNonPublishedNews()).thenReturn(twoItems());

        sut.run();

        verify(vkClientMock).publish(twoItems().get(0));
        verify(publishedItemsServiceMock).saveNewPublishedId(twoItems().get(0).getId());
        verify(vkClientMock).publish(twoItems().get(1));
        verify(publishedItemsServiceMock).saveNewPublishedId(twoItems().get(1).getId());
    }

    @Test
    public void shouldPublishNextNewsIfFirstCompletedWithError() {
        when(hackernewsServiceMock.getMostPopularNonPublishedNews()).thenReturn(twoItems());
        doThrow(new RuntimeException("OK")).when(vkClientMock).publish(twoItems().get(0));

        sut.run();

        verify(vkClientMock).publish(twoItems().get(0));
        verify(vkClientMock).publish(twoItems().get(1));
        verify(publishedItemsServiceMock).saveNewPublishedId(twoItems().get(1).getId());
        verifyNoMoreInteractions(publishedItemsServiceMock);
    }
}