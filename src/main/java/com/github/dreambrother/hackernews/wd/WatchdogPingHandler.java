package com.github.dreambrother.hackernews.wd;

import com.github.dreambrother.hackernews.http.HttpGetHandler;

public class WatchdogPingHandler implements HttpGetHandler {

    public static final String RESPONSE_MESSAGE = "pong";

    @Override
    public String handle(String requestUrl) {
        return RESPONSE_MESSAGE;
    }
}
