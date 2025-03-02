package com.restfulbooker.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public abstract class JSONDataReader {
    protected final ObjectMapper MAPPER = new ObjectMapper();

    public JSONDataReader() {

    }

    public Object[][] read(String resourcePath) {
        ObjectArray[] data;
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath)) {
            data = parse(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Object[][] objects = new Object[data.length][];
        for (int i = 0; i < data.length; i++) {
            objects[i] = data[i].toArray();
            System.out.println(Arrays.toString(objects[i]));
        }
        return objects;
    }

    protected abstract ObjectArray[] parse(InputStream is) throws IOException;
}
