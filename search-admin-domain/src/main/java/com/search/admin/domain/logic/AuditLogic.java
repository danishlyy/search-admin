package com.search.admin.domain.logic;

import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.enums.ElasticSearchKeysEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import com.search.admin.infra.storage.entity.IndexSettings;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class AuditLogic {

    @Autowired
    private SearchAdminClient searchAdminClient;

    /**
     * sync index info to elasticsearch，save sync history
     * @param indexSettings
     * @return
     */
    public boolean syncIndexConfiguration(IndexSettings indexSettings) {
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        String indexName = indexSettings.getIndexName();
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        Settings settings = Settings.builder()
                .put(ElasticSearchKeysEnum.setting_number_of_shards.getCode(), indexSettings.getNumberOfShards())
                .put(ElasticSearchKeysEnum.setting_number_of_replicas.getCode(), indexSettings.getNumberOfReplicas())
                .build();
        createIndexRequest.settings(settings);
        createIndexRequest.mapping(indexSettings.getIndexMapping(), XContentType.JSON);
        CreateIndexResponse createIndexResponse = null;
        try {
            createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("sync index info to elasticsearch failed",e);
            throw new SearchFrameworkException(BusinessExceptionEnum.CREATE_INDEX_FAILED.getCode(),BusinessExceptionEnum.CREATE_INDEX_FAILED.getDesc());
        }
        return createIndexResponse.isAcknowledged();
    }
}
