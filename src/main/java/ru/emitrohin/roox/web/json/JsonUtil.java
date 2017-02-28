package ru.emitrohin.roox.web.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */
public class JsonUtil {

    @Autowired
    private static ObjectMapper jacksonObjectMapper;

    public static <T> List<T> readValues(String json, Class<T> clazz) {
        ObjectReader reader = jacksonObjectMapper.readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return jacksonObjectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> String writeValue(T obj) {
        try {
            return jacksonObjectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }
}
