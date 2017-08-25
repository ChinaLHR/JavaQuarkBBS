package com.quark.porent.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quark.porent.entity.QuarkResult;

import java.io.IOException;

/**
 * @Author LHR
 * Create By 2017/8/24
 */
public class JsonUtils {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();


    /**
     * 将json转换为QuarkResult
     * @param json
     * @param clazz
     * @return
     */
    public static QuarkResult jsonToQuarkResult(String json, Class<?> clazz) {

        try {
            if (clazz == null) {
                return MAPPER.readValue(json, QuarkResult.class);
            }

            JsonNode jsonNode = MAPPER.readTree(json);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }

            return new QuarkResult(jsonNode.get("status").intValue(), obj, jsonNode.get("error").asText());
        } catch (IOException e) {
            return null;
        }

    }


}
