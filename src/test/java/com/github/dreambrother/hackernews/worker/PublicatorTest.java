package com.github.dreambrother.hackernews.worker;

import com.github.dreambrother.hackernews.client.HackernewsClient;
import com.github.dreambrother.hackernews.client.VkClient;
import com.github.dreambrother.hackernews.dao.PublishedItemsDao;
import com.github.dreambrother.hackernews.dto.HackernewsItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    }

    @Test
    public void publishingScenario() {
        when(hackernewsClientMock.fetchNews()).thenReturn(threeItems());
        when(publishedItemsDaoMock.getLastPublishedItems()).thenReturn(twoItems());

        sut.run();

        verify(hackernewsClientMock).fetchNews();
        verify(publishedItemsDaoMock).getLastPublishedItems();
        verify(vkClientMock).publish(Collections.singletonList(threeItems().get(2)));
        verify(publishedItemsDaoMock).saveLastPublishedItems(threeItems());
    }
    
    private List<HackernewsItem> twoItems() {
        List<HackernewsItem> items = new ArrayList<>();

        HackernewsItem itemOne = new HackernewsItem();
        itemOne.setTitle("Item One");
        itemOne.setLink("http://item.one");
        itemOne.setCommentsLink("http://item.one/comments");
        items.add(itemOne);

        HackernewsItem itemTwo = new HackernewsItem();
        itemTwo.setTitle("Item two");
        itemTwo.setLink("http://item.two");
        itemTwo.setCommentsLink("http://item.two/comments");
        items.add(itemTwo);

        return items;
    }

    private List<HackernewsItem> threeItems() {
        List<HackernewsItem> items = new ArrayList<>(twoItems());

        HackernewsItem item = new HackernewsItem();
        item.setTitle("Item Three");
        item.setLink("http://item.three");
        item.setCommentsLink("http://item.three/comments");
        items.add(item);

        return items;
    }
}