package com.github.dreambrother.hackernews.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class HackernewsJsonUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static final JavaType IDS_TYPE = MAPPER.getTypeFactory().constructCollectionType(List.class, Long.class);

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
