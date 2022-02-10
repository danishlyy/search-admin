package com.search.admin.domain.helper;

import com.search.admin.domain.bo.IndexBO;
import com.search.admin.domain.bo.IndexMappingPropertiesBO;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import com.search.admin.infra.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class IndexMappingHelper {


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

    public static String toOriginalIndexMapping(IndexBO indexBO){
        return JacksonUtil.toJsonString(indexBO);
    }
}
