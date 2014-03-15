package com.github.dreambrother.hackernews.client;

import org.junit.Test;

import static com.github.dreambrother.hackernews.fixture.HackernewsItems.oneItem;

public class VkClientImplIntTest {

    private static final String TOKEN_URL = "https://oauth.vk.com/authorize?client_id=4245307&scope=wall,offline&redirect_uri=https://oauth.vk.com/blank.html&display=page&v=5.14&response_type=token";
    private String token = "ecb0cff76d9600ba52dc1ed6daf135ea73f01dc6c4e067bf41d866e3c638ee81a58768033d77cc227b067";
    private String communityId = "-68122109";

    private VkClientImpl sut = new VkClientImpl();

    public VkClientImplIntTest() {
        sut.setToken(token);
        sut.setCommunityId(communityId);
    }

    @Test
    public void shouldPublishNews() {
        sut.publish(oneItem());
    }
}
