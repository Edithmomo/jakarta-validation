package com.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>json 工具类</p>
 *
 * @author wangjiameng
 * @date 2020-08-20 16:35
 */
public class JsonUtil {
    private final static ObjectMapper MAPPER = new ObjectMapper();

    public static String toJsonString(Object obj) throws JsonProcessingException {
        return MAPPER.writeValueAsString(obj);
    }

    public static <T> T toObject(String jsonString, Class<T> clazz) throws JsonProcessingException {
        return MAPPER.readValue(jsonString, clazz);
    }
}
