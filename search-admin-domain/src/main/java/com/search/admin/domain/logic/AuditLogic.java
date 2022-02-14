package com.search.admin.domain.logic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.enums.ElasticSearchKeysEnum;
import com.search.admin.infra.enums.SyncStatusEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import com.search.admin.infra.storage.entity.AuditIndexInfo;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.service.IAuditIndexInfoService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.Cancellable;
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
    @Autowired
    private IAuditIndexInfoService iAuditIndexInfoService;



    public boolean auditIndexSetting(IndexSettings indexSettings) {
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        String indexName = indexSettings.getIndexName();
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        Settings settings = Settings.builder()
                .put(ElasticSearchKeysEnum.SETTING_NUMBER_OF_SHARDS.getCode(), indexSettings.getNumberOfShards())
                .put(ElasticSearchKeysEnum.SETTING_NUMBER_OF_REPLICAS.getCode(), indexSettings.getNumberOfReplicas())
                .build();
        createIndexRequest.settings(settings);
        CreateIndexResponse createIndexResponse = null;
        try {
            createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("sync index info to elasticsearch failed",e);
            throw new SearchFrameworkException(BusinessExceptionEnum.CREATE_INDEX_FAILED.getCode(),BusinessExceptionEnum.CREATE_INDEX_FAILED.getDesc());
        }
        return createIndexResponse.isAcknowledged();
    }

    public boolean auditIndexMapping(IndexSettings indexSettings) {
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        String indexName = indexSettings.getIndexName();
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
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

    public boolean auditDeleteIndex(String indexName) {
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
        try {
            client.indices().deleteAsync(deleteIndexRequest, RequestOptions.DEFAULT, new ActionListener<>() {
                @Override
                public void onResponse(AcknowledgedResponse acknowledgedResponse) {
                    log.info("delete index indexName:{} response:{}",indexName,acknowledgedResponse.isAcknowledged());
                    return;
                }

                @Override
                public void onFailure(Exception e) {
                    log.error("delete index failed,indexName:{}",indexName,e);
                    LambdaQueryWrapper<AuditIndexInfo> queryWrapper = Wrappers.lambdaQuery();
                    queryWrapper.eq(AuditIndexInfo::getIndexName,indexName);
                    int delete = iAuditIndexInfoService.getBaseMapper().delete(queryWrapper);
                    log.info("delete record count:{}",delete);
                    return;
                }
            });
        }catch (Exception e){
            log.error("delete index failed,indexName:{}",indexName,e);
            throw new SearchFrameworkException(BusinessExceptionEnum.INDEX_DELETE_FAILED.getCode(),BusinessExceptionEnum.INDEX_DELETE_FAILED.getDesc() );
        }
        return true;
    }
}
