package com.github.dreambrother.hackernews.properties;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AppPropertiesIntTest {

    private AppProperties sut = new AppProperties("classpath:/app.properties");

    @Test
    public void shouldGetStringProperty() {
        String actual = sut.getStringProperty("db.file");
        assertTrue(!actual.isEmpty());
    }

    @Test
    public void shouldGetIntProperty() {
        int actual = sut.getIntProperty("wd.ping.port");
        assertTrue(actual > 0);
    }
}
