package me.xiaoman.medicalassistant.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;

public class JsonParser {

    public static String toJson(Object object) {
        try {
            return objectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toJson(InputStream inputStream) {
        try {
            return objectMapper().writeValueAsString(inputStream);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T> T toObject(InputStream inputStream, Class<T> cls) {
        try {
            return objectMapper().readValue(inputStream, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toObject(String json, Class<T> cls) {
        try {
            return objectMapper().readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toObject(byte[] json, Class<T> cls) {
        try {
            return objectMapper().readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        objectMapper.findAndRegisterModules();
        return objectMapper;
    }

}
