package com.github.dreambrother.hackernews.wd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WatchdogPingHandlerTest {

    private WatchdogPingHandler sut = new WatchdogPingHandler();

    @Test
    public void shouldResponseToPing() {
        String actual = sut.handle("/ping");
        assertEquals(WatchdogPingHandler.RESPONSE_MESSAGE, actual);
    }
}
