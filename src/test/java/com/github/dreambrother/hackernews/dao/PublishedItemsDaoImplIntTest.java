package com.github.dreambrother.hackernews.dao;

import com.github.dreambrother.hackernews.dto.HackernewsItem;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.github.dreambrother.hackernews.fixture.HackernewsItems.threeItems;
import static com.github.dreambrother.hackernews.fixture.HackernewsItems.twoItems;
import static org.junit.Assert.assertEquals;

public class PublishedItemsDaoImplIntTest {

    private PublishedItemsDaoImpl sut = new PublishedItemsDaoImpl();

    private File tmp;

    public PublishedItemsDaoImplIntTest() throws IOException {
        tmp = File.createTempFile("items", "db");
        sut.setDbFile(tmp);
    }

    @Test
    public void shoudSaveAndGetPublishedItems() {
        sut.saveLastPublishedItems(threeItems());

        List<HackernewsItem> actual = sut.getLastPublishedItems();

        assertEquals(threeItems(), actual);
    }

    @Test
    public void shouldGetLastPublishedItems() {
        sut.saveLastPublishedItems(threeItems());
        sut.saveLastPublishedItems(twoItems());

        List<HackernewsItem> actual = sut.getLastPublishedItems();

        assertEquals(twoItems(), actual);
    }

    @Test
    public void shouldReturnEmptyListWhenFileIsEmpty() {
        List<HackernewsItem> actual = sut.getLastPublishedItems();
        assertEquals(0, actual.size());
    }

    @Test
    public void shouldReturnEmptyListWhenFileIsNotExists() {
        tmp.delete();
        List<HackernewsItem> actual = sut.getLastPublishedItems();
        assertEquals(0, actual.size());
    }

    @Test
    public void shouldCreateFileIfItNotExists() {
        tmp.delete();
        sut.saveLastPublishedItems(threeItems());
        assertEquals(threeItems(), sut.getLastPublishedItems());
    }
}
