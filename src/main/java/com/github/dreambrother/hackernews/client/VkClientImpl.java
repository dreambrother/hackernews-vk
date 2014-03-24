package com.github.dreambrother.hackernews.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dreambrother.hackernews.dto.HackernewsItem;
import com.github.dreambrother.hackernews.exceptions.RuntimeIOException;
import com.github.dreambrother.hackernews.exceptions.VkException;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

public class VkClientImpl implements VkClient {

    private static final Logger log = LoggerFactory.getLogger(VkClientImpl.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    private String token;
    private String communityId;

    @Override
    public void publish(HackernewsItem news) {
        log.info("Publish {}", news);
        try {
            String responseString = Request.Post("https://api.vk.com/method/wall.post")
                    .bodyForm(paramsForNews(news), Charset.forName("UTF-8"))
                    .execute().returnContent().asString();

            log.info("VK response {}", responseString);

            Map<String, String> response = mapper.readValue(responseString, Map.class);
            checkResponse(response);
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }

    private Iterable<? extends NameValuePair> paramsForNews(HackernewsItem news) {
        return Arrays.asList(
                new BasicNameValuePair("owner_id", communityId),
                new BasicNameValuePair("from_group", "1"),
                new BasicNameValuePair("message", formatMessage(news)),
                new BasicNameValuePair("attachments", news.getLink()),
                new BasicNameValuePair("access_token", token),
                new BasicNameValuePair("v", "5.14")
        );
    }

    private void checkResponse(Map<String, String> response) {
        if (response.get("response") == null) {
            throw new VkException("VK response contains error");
        }
    }

    private String formatMessage(HackernewsItem news) {
        return String.format("%s%n Comments: %s", news.getTitle(), news.getCommentsLink());
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }
}
