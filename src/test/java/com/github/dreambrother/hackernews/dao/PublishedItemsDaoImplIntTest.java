package com.github.dreambrother.hackernews.dao;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        List<Long> ids = Arrays.asList(1l, 2l, 3l);
        sut.saveLastPublishedIds(ids);

        List<Long> actual = sut.getLastPublishedIds();

        assertEquals(ids, actual);
    }

    @Test
    public void shouldGetLastPublishedItems() {
        sut.saveLastPublishedIds(Arrays.asList(1l, 2l, 3l));
        List<Long> ids = Arrays.asList(1l, 2l);
        sut.saveLastPublishedIds(ids);

        List<Long> actual = sut.getLastPublishedIds();

        assertEquals(ids, actual);
    }

    @Test
    public void shouldReturnEmptyListWhenFileIsEmpty() {
        List<Long> actual = sut.getLastPublishedIds();
        assertEquals(0, actual.size());
    }

    @Test
    public void shouldReturnEmptyListWhenFileIsNotExists() {
        tmp.delete();
        List<Long> actual = sut.getLastPublishedIds();
        assertEquals(0, actual.size());
    }

    @Test
    public void shouldCreateFileIfItNotExists() {
        tmp.delete();
        List<Long> ids = Arrays.asList(1l);

        sut.saveLastPublishedIds(ids);

        assertEquals(ids, sut.getLastPublishedIds());
    }
}
