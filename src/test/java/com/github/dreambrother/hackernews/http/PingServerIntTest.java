package com.github.dreambrother.hackernews.http;

import com.github.dreambrother.hackernews.exceptions.RuntimeIOException;
import org.apache.http.client.fluent.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PingServerIntTest {

    private PingServer sut = new PingServer();

    @Mock
    private HttpGetHandler handlerMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        sut.setHandler(handlerMock);
        sut.setPort(10000);
        sut.start();
    }

    @After
    public void destroy() {
        sut.stop();
    }

    @Test
    public void shouldHandleRequest() {
        String relativeUrl = "/ping";
        String response = "pong";

        when(handlerMock.handle(relativeUrl)).thenReturn(response);
        String actual = httpGet(relativeUrl);

        assertEquals(response, actual);
        verify(handlerMock).handle(relativeUrl);
    }

    private String httpGet(String relativeUrl) {
        try {
            return Request.Get("http://localhost:10000" + relativeUrl).execute().returnContent().asString();
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }
}
