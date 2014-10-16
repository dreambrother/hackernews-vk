package com.github.dreambrother.hackernews.dao;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dreambrother.hackernews.dto.HackernewsItem;
import com.github.dreambrother.hackernews.exceptions.RuntimeIOException;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class PublishedItemsDaoImpl implements PublishedItemsDao {

    private ObjectMapper objectMapper = new ObjectMapper();
    private JavaType idsType = objectMapper.getTypeFactory().constructCollectionType(List.class, Long.class);
    private File dbFile;

    @Override
    public List<HackernewsItem> getLastPublishedItems() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveLastPublishedItems(List<HackernewsItem> items) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveLastPublishedIds(List<Long> ids) {
        try {
            objectMapper.writeValue(dbFile, ids);
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }

    @Override
    public List<Long> getLastPublishedIds() {
        if (dbFile.length() == 0) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(dbFile, idsType);
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }

    public void setDbFile(File dbFile) {
        this.dbFile = dbFile;
    }
}
