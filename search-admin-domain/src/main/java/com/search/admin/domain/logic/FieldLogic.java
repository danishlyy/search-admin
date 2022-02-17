package com.search.admin.domain.logic;

import com.search.admin.domain.helper.IndexMappingHelper;
import com.search.admin.infra.storage.entity.IndexSettings;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.client.indices.GetMappingsResponse;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class FieldLogic {
    public void validateFields(IndexSettings index, RestHighLevelClient client) {

        try {
            GetMappingsRequest getMappingsRequest = new GetMappingsRequest();
            getMappingsRequest.indices(index.getIndexName());
            GetMappingsResponse mapping = client.indices().getMapping(getMappingsRequest, RequestOptions.DEFAULT);
            Map<String, MappingMetadata> mappings = mapping.mappings();
            Map.Entry<String, MappingMetadata> next = mappings.entrySet().iterator().next();
            MappingMetadata value = next.getValue();
            Map<String, Object> sourceAsMap = value.getSourceAsMap();
            XContentBuilder builder = IndexMappingHelper.convertMapping2XContentBuilder(index.getIndexName(), index.getIndexMapping());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
