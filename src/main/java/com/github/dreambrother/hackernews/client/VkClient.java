package com.github.dreambrother.hackernews.client;

import com.github.dreambrother.hackernews.dto.HackernewsItem;

public interface VkClient {

    void publish(HackernewsItem news);
}
