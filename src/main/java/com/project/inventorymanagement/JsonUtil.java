package com.project.inventorymanagement;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


/**
 * Utility class for handling JSON serialization and deserialization using Jackson.
 * This class provides static methods to serialize objects to JSON and to deserialize JSON into objects.
 */
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();


    /**
     * Serializes an object to JSON and saves it to the specified file path.
     * @param object the object to serialize to JSON
     * @param path   the file path where the JSON output will be saved
     * @throws IOException if there is an issue writing to the file
     * @param <T>    the type of the object to serialize
     */
    public static <T> void writeToJson(T object, String path) throws IOException {
        // Write with default pretty printer indents the json file for readability
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), object);
    }


    /**
     * Deserializes JSON content from a specified file into an object of the specified class.
     *
     * @param path  the file path from which to read the JSON content
     * @param klass the class of the object into which the JSON content will be deserialized
     * @return the deserialized object of type {@code klass}
     * @throws IOException if there is a problem reading from the file
     * @param <T>   the type of the object to deserialize
     */
    public static <T> T readFromJson(String path, Class<T> klass) throws IOException {
        return mapper.readValue(new File(path), klass);
    }
}
