package com.github.dreambrother.hackernews.wd;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class WatchdogPingHandlerTest {

    private WatchdogPingHandler sut = new WatchdogPingHandler();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldResponseToPing() {
        String actual = sut.handle("/ping");
        assertEquals(WatchdogPingHandler.RESPONSE_MESSAGE, actual);
    }
}
