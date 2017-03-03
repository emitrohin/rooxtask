package ru.emitrohin.roox.web.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.util.List;

/**
 * Utility class that uses jacksonObjectMapper to objects to json
 *
 * @author Evgeniy Mitrokhin
 */
public class JsonUtil {

    private static final ObjectMapper jacksonObjectMapper = new ObjectMapper();

    /**
     * Read json string and write it to list of objects
     *
     * @param json - json string
     * @param clazz - Class to read
     * @param <T> - any parserable object
     * @return list of values
     */
    public static <T> List<T> readValues(String json, Class<T> clazz) {
        ObjectReader reader = jacksonObjectMapper.readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    /**
     * Read json string and write it objects
     *
     * @param json - json string
     * @param clazz - Class to read
     * @param <T> - any parserable object
     * @return list of values
     */
    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return jacksonObjectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    /**
     * Takes an objects and create json string from it
     *
     * @param obj - any parserable object
     * @param <T> - Class to read
     * @return json string
     */
    public static <T> String writeValue(T obj) {
        try {
            return jacksonObjectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }
}
