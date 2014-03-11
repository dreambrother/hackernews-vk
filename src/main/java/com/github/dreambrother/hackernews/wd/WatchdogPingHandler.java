package com.github.dreambrother.hackernews.wd;

import com.github.dreambrother.hackernews.client.HackernewsClient;
import com.github.dreambrother.hackernews.http.HttpGetHandler;

public class WatchdogPingHandler implements HttpGetHandler {

    public static final String RESPONSE_MESSAGE = "pong";

    private HackernewsClient hackernewsClient;

    @Override
    public String handle(String requestUrl) {
        hackernewsClient.fetchNews();
        return RESPONSE_MESSAGE;
    }

    public void setHackernewsClient(HackernewsClient hackernewsClient) {
        this.hackernewsClient = hackernewsClient;
    }
}
