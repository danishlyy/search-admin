package com.search.admin.domain.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.admin.domain.bo.IndexMappingPropertiesBO;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.enums.FieldTypeEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import com.search.admin.infra.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class IndexMappingHelper {

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static String parseMappingObj2Json(List<IndexMappingPropertiesBO> source){
        if (CollectionUtils.isEmpty(source)){
            return StringUtils.EMPTY;
        }
        Map<String, Object> mapping = null;
        try {
            mapping = new HashMap<>();
            Map<String, Object> properties = new HashMap<>();
            for (IndexMappingPropertiesBO item:source) {
                Map<String, Object> message = new HashMap<>();
                message.put("type", item.getFieldType());
                message.put("analyzer",item.getAnalyzeType());
                properties.put(item.getFieldName(), message);
            }
            mapping.put("properties", properties);
        } catch (Exception e) {
            log.error("parse obj to mapping json failed",e);
            throw new SearchFrameworkException(BusinessExceptionEnum.INDEX_SETTING_CONVERT.getCode(),
                    BusinessExceptionEnum.NUMBER_OF_REPLICAS_ILLEGAL.getDesc());
        }
        return JacksonUtil.toJsonString(mapping);
    }

    public static String toOriginalIndexMapping(List<IndexMappingPropertiesBO> list){
        return JacksonUtil.toJsonString(list);
    }

    public static List<IndexMappingPropertiesBO> convertFieldStr2List(String source){
        if (StringUtils.isBlank(source)){
            return Collections.emptyList();
        }
        try {
            return OBJECT_MAPPER.readValue(source, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new SearchFrameworkException(BusinessExceptionEnum.SYSTEM_ERROR.getCode(), BusinessExceptionEnum.SYSTEM_ERROR.getDesc());
        }
    }


    public static XContentBuilder convertMapping2XContentBuilder(String indexName,String indexMapping) {
        XContentBuilder builder = null;
        try {
            builder = XContentFactory.jsonBuilder();
            List<IndexMappingPropertiesBO> fields = convertFieldStr2List(indexMapping);
            builder.startObject();
            {
                builder.startObject("properties");
                {
                    for (IndexMappingPropertiesBO item:fields){
                        builder.startObject(item.getFieldName());
                        {
                            if (FieldTypeEnum.TYPE_ARRAY.getCode().equals(item.getFieldType())){
                                builder.field("type",FieldTypeEnum.TYPE_TEXT.getCode());
                            }else {
                                builder.field("type", item.getFieldType());
                            }
                            if (StringUtils.isNotBlank(item.getAnalyzeType())){
                                builder.field("analyzer",item.getAnalyzeType());
                            }
                        }
                        builder.endObject();

                    }
                }
                builder.endObject();
            }
            builder.endObject();
        } catch (IOException e) {
            log.error("convert index mapping failed,indexName:{}",indexName,e);
            throw new RuntimeException();
        }
        return builder;
    }
}
