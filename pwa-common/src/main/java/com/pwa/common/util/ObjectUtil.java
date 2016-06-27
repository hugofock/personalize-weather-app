package com.pwa.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObjectUtil {

    private ObjectUtil() {
    }

    public static String toJson(final Object object) {
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            // do nothing
        }
        return json;
    }
}
