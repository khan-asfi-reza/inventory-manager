package com.project.inventorymanagement;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> void serializeToJson(T object, String path) throws IOException {
        mapper.writeValue(new File(path), object);
    }

    public static <T> T deserializeFromJson(String path, Class<T> clazz) throws IOException {
        return mapper.readValue(new File(path), clazz);
    }
}
