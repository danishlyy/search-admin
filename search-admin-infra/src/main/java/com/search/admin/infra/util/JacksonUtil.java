package com.search.admin.infra.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JacksonUtil {

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJsonString(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("writeValueAsString failed", e);
            throw new SearchFrameworkException(BusinessExceptionEnum.INDEX_SETTING_CONVERT.getCode(), BusinessExceptionEnum.INDEX_SETTING_CONVERT.getDesc());
        }
    }

    public static <T> T toObject(String json, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(json, valueType);
        } catch (Exception e) {
            log.error("readValue string 2 object failed", e);
            throw new SearchFrameworkException(BusinessExceptionEnum.INDEX_SETTING_CONVERT.getCode(), BusinessExceptionEnum.INDEX_SETTING_CONVERT.getDesc());
        }
    }


}
