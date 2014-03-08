package com.github.dreambrother.hackernews.io;

import com.github.dreambrother.hackernews.exceptions.IORuntimeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StdIOImpl implements StdIO {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    @Override
    public void write(String value) {
        System.out.println(value);
    }
}
