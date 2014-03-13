package com.github.dreambrother.hackernews.dao;

import com.github.dreambrother.hackernews.dto.HackernewsItem;
import com.github.dreambrother.hackernews.dto.HackernewsItemList;

import javax.xml.bind.JAXB;
import java.io.File;
import java.util.List;

public class PublishedItemsDaoImpl implements PublishedItemsDao {

    private File dbFile;

    @Override
    public List<HackernewsItem> getLastPublishedItems() {
        return JAXB.unmarshal(dbFile, HackernewsItemList.class).getItems();
    }

    @Override
    public void saveLastPublishedItems(List<HackernewsItem> items) {
        HackernewsItemList itemList = new HackernewsItemList();
        itemList.setItems(items);
        JAXB.marshal(itemList, dbFile);
    }

    public void setDbFile(File dbFile) {
        this.dbFile = dbFile;
    }
}
