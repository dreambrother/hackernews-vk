package com.github.dreambrother.hackernews.client;

import com.github.dreambrother.hackernews.exceptions.VkException;
import org.junit.Ignore;
import org.junit.Test;

import static com.github.dreambrother.hackernews.fixture.HackernewsItems.oneItem;

@Ignore("Token is invalid on CI server")
public class VkClientImplIntTest {

    private static final String TOKEN_URL = "https://oauth.vk.com/authorize?client_id=4245307&scope=wall,offline&redirect_uri=https://oauth.vk.com/blank.html&display=page&v=5.14&response_type=token";
    private String token = "";
    private String communityId = "-78812350";

    private VkClientImpl sut = new VkClientImpl();

    public VkClientImplIntTest() {
        sut.setToken(token);
        sut.setCommunityId(communityId);
    }

    @Test
    public void shouldPublishNews() {
        sut.publish(oneItem());
    }

    @Test(expected = VkException.class)
    public void shouldThrowExceptionInCaseOfError() {
        VkClientImpl invalidClient = new VkClientImpl();
        invalidClient.setToken("invalid");
        invalidClient.setCommunityId("invalid");

        invalidClient.publish(oneItem());
    }
}
