package com.wjz.springboot1.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by jingzhi.wu on 2018/1/16.
 */
public class RestUtil {
    private static ObjectMapper objectMapper = new CustomObjectMapper();

    public static <T> T inputStreamToObject(InputStream ins, Class<T> clazz) {
        try {
            return objectMapper.readValue(ins, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T mapToObject(Map<?, ?> map, Class<T> clazz) {
        return stringToObject(objectToJSONString(map), clazz);
    }

    public static String objectToJSONString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T stringToObject(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T stringToObject(String jsonString, TypeReference<T> type) {
        try {
            return objectMapper.readValue(jsonString, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
