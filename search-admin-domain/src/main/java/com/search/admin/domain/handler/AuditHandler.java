package com.search.admin.domain.handler;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.SourceField;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.domain.logic.IndexQueryLogic;
import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.ex.SearchFrameworkException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class AuditHandler {

    @Autowired
    private IndexQueryLogic indexQueryLogic;

    @Autowired
    private SearchAdminClient searchAdminClient;


    public boolean confirmIndexConfiguration(List<String> indexIds) {
        List<IndexSettingBO> list = indexQueryLogic.findIndexByIds(indexIds);
        ElasticsearchClient client = searchAdminClient.elasticsearchClient();
        try {
            for (IndexSettingBO bo:list){
                IndexSettings indexSettings = new IndexSettings.Builder()
                        .numberOfReplicas(bo.getNumberOfReplicas())
                        .numberOfShards(bo.getNumberOfShards())
                        .build();
                TypeMapping typeMapping = new TypeMapping.Builder()
                        .build();
                CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                        .index(bo.getIndexName())
                        .mappings(typeMapping)
                        .settings(indexSettings)
                        .build();
                CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest);
            }
        } catch (IOException e) {
            log.error("sync index configuration to elasticsearch failed",e);
            throw new SearchFrameworkException("","");
        }
        return false;
    }
}
