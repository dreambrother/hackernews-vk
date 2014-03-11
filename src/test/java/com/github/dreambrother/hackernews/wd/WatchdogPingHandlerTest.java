package com.github.dreambrother.hackernews.wd;

import com.github.dreambrother.hackernews.client.HackernewsClient;
import com.github.dreambrother.hackernews.dto.HackernewsItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WatchdogPingHandlerTest {

    private WatchdogPingHandler sut = new WatchdogPingHandler();

    @Mock
    private HackernewsClient hackernewsClientMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        sut.setHackernewsClient(hackernewsClientMock);
    }

    @Test
    public void shouldResponseToPing() {
        String actual = sut.handle("/ping");
        assertEquals(WatchdogPingHandler.RESPONSE_MESSAGE, actual);
    }

    @Test
    public void shouldPingHackernews() {
        when(hackernewsClientMock.fetchNews()).thenReturn(new ArrayList<HackernewsItem>());

        sut.handle("/ping");

        verify(hackernewsClientMock).fetchNews();
    }
}
