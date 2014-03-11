package com.github.dreambrother.hackernews.http;

import com.github.dreambrother.hackernews.exceptions.RuntimeIOException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
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

    @Test
    public void shouldHandleError() {
        when(handlerMock.handle(anyString())).thenThrow(RuntimeException.class);
        expectErrorCode(500);
    }

    private String httpGet(String relativeUrl) {
        try {
            return Request.Get("http://localhost:10000" + relativeUrl).execute().returnContent().asString();
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }

    private void expectErrorCode(int code) {
        try {
            Request.Get("http://localhost:10000").execute().returnContent().asString();
            fail();
        } catch (HttpResponseException ex) {
            assertEquals(code, ex.getStatusCode());
        } catch (Exception ignored) {
            fail();
        }
    }
}
