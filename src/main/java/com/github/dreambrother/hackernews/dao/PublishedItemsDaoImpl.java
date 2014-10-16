package com.github.dreambrother.hackernews.dao;

import com.github.dreambrother.hackernews.dto.HackernewsItem;
import com.github.dreambrother.hackernews.exceptions.RuntimeIOException;
import com.github.dreambrother.hackernews.utils.HackernewsJsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class PublishedItemsDaoImpl implements PublishedItemsDao {

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
            HackernewsJsonUtils.MAPPER.writeValue(dbFile, ids);
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
            return HackernewsJsonUtils.MAPPER.readValue(dbFile, HackernewsJsonUtils.IDS_TYPE);
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }

    public void setDbFile(File dbFile) {
        this.dbFile = dbFile;
    }
}
