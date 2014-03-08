package com.github.dreambrother.hackernews.wd;

import com.github.dreambrother.hackernews.io.StdIO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class WatchdogPingListenerIntTest {

    private WatchdogPingListener sut = new WatchdogPingListener();

    @Mock
    private StdIO stdIOMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        sut.setStdIO(stdIOMock);
    }

    @Test
    public void shouldResponseToPing() {
        when(stdIOMock.read()).thenReturn("ping");

        sut.start();

        verify(stdIOMock, atLeastOnce()).write(WatchdogPingListener.RESPONSE_MESSAGE);
    }
}
