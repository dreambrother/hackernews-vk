package com.github.dreambrother.hackernews.properties;

import com.github.dreambrother.hackernews.exceptions.RuntimeIOException;
import com.github.dreambrother.hackernews.exceptions.UnknownPropertyException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {

    public static final String CLASSPATH_PREFIX = "classpath:";
    private Properties source = new Properties();

    public AppProperties(String src) {
        loadProperties(src);
    }

    private void loadProperties(String src) {
        try {
            if (src.startsWith(CLASSPATH_PREFIX)) {
                source.load(getClass().getResourceAsStream(src.replace(CLASSPATH_PREFIX, "")));
            } else {
                source.load(new FileInputStream(src));
            }
        } catch (IOException ex) {
            throw new RuntimeIOException(ex);
        }
    }

    public String getStringProperty(String name) {
        String value = source.getProperty(name);
        if (value == null) {
            throw new UnknownPropertyException("Unknown property " + name);
        }
        return value;
    }

    public int getIntProperty(String name) {
        return Integer.parseInt(getStringProperty(name));
    }
}
