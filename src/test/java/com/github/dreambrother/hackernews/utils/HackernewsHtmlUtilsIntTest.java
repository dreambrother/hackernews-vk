package com.github.dreambrother.hackernews.utils;

import org.junit.Test;

import java.io.*;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class HackernewsHtmlUtilsIntTest {

    private String html;

    public HackernewsHtmlUtilsIntTest() throws IOException {
        StringBuilder htmlBuilder = new StringBuilder();

        InputStream htmlStream = this.getClass().getResourceAsStream("/hackernews.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(htmlStream));

        String line;
        while ((line = reader.readLine()) != null) {
            htmlBuilder.append(line);
        }

        html = htmlBuilder.toString();
    }

    @Test
    public void shouldParseRatings() {
        Map<String, Integer> rating = HackernewsHtmlUtils.parseRating(html);
        assertEquals(30, rating.size());
        assertEquals(Integer.valueOf(420), rating.get("Gravity waves from Big Bang detected"));
    }

    @Test
    public void raitingShouldBeZeroIfNotExists() {
        Map<String, Integer> rating = HackernewsHtmlUtils.parseRating(html);
        assertEquals(Integer.valueOf(0), rating.get("FOBO (YC S11) – Typescript, Web – Efficient market for local used goods"));
    }
}
