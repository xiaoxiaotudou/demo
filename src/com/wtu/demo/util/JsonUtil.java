package com.wtu.demo.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {

    public static String convertObjectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;

        if (!object.equals(null)) {
            try {
                jsonStr = objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                throw new RuntimeException();
            }
        }

        return jsonStr;
    }

    public static Object convertJsonToObject(String jsonStr) {
        ObjectMapper objectMapper = new ObjectMapper();
        Object object = new Object();

        if (!jsonStr.equals(null)) {
            try {
                object = objectMapper.readValue(jsonStr, Object.class);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        return object;
    }
}