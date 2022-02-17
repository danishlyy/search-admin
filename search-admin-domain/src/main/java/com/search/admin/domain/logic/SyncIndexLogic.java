package com.search.admin.domain.logic;

import com.search.admin.domain.bo.AuditInfoBO;
import com.search.admin.domain.helper.IndexMappingHelper;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.enums.SyncStatusEnum;
import com.search.admin.infra.enums.SyncTypeEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import com.search.admin.infra.storage.entity.IndexSettings;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class SyncIndexLogic {

    @Autowired
    private AuditUpdateLogic auditUpdateLogic;
    /**
     * 更新ES mapping 和副本数
     * @param index
     * @param client
     * @param auditInfoBO
     * @return
     */
    public boolean updateEsIndexInfo(IndexSettings index,RestHighLevelClient client, AuditInfoBO auditInfoBO) {
        AcknowledgedResponse acknowledgedResponse = null;
        boolean acknowledged = false;
        UpdateSettingsRequest updateSettingsRequest = new UpdateSettingsRequest(index.getIndexName());
        PutMappingRequest putMappingRequest = new PutMappingRequest(index.getIndexName());
        if (SyncTypeEnum.SYNC_ALL.equals(auditInfoBO.getSyncType())){
            try {
                updateSettingsRequest.settings(Settings.builder()
                        .put("index.number_of_replicas",Integer.parseInt(index.getNumberOfReplicas()))
                        .build());
                acknowledgedResponse = client.indices().putSettings(updateSettingsRequest, RequestOptions.DEFAULT);
                acknowledged = acknowledgedResponse.isAcknowledged();
                log.warn("sync all update replicas:{}",acknowledged);
                XContentBuilder xContentBuilder = IndexMappingHelper.convertMapping2XContentBuilder(index.getIndexName(),index.getIndexMapping());
                putMappingRequest.source(xContentBuilder);
                acknowledgedResponse = client.indices().putMapping(putMappingRequest, RequestOptions.DEFAULT);
                acknowledged = acknowledgedResponse.isAcknowledged();
                log.warn("sync all update mapping:{}",acknowledged);
            }catch (Exception e){
                log.error("sync all update es failed,indexName:{}",index.getIndexName(),e);
                auditInfoBO.setSyncStatus(SyncStatusEnum.SYNC_FAILURE.getCode());
                auditUpdateLogic.updateAuditIndexInfo(auditInfoBO);
                throw  new SearchFrameworkException(BusinessExceptionEnum.CREATE_INDEX_FAILED.getCode(), BusinessExceptionEnum.CREATE_INDEX_FAILED.getDesc());
            }


        }
        if (SyncTypeEnum.SYNC_SETTING.equals(auditInfoBO.getSyncType())){

            try {
                updateSettingsRequest.settings(Settings.builder()
                        .put("index.number_of_replicas",Integer.parseInt(index.getNumberOfReplicas()))
                        .build());
                acknowledgedResponse = client.indices().putSettings(updateSettingsRequest, RequestOptions.DEFAULT);
                acknowledged = acknowledgedResponse.isAcknowledged();
            } catch (IOException e) {
                log.error("sync update index replicas failed,indexName:{}",index.getIndexName(),e);
                auditInfoBO.setSyncStatus(SyncStatusEnum.SYNC_FAILURE.getCode());
                auditUpdateLogic.updateAuditIndexInfo(auditInfoBO);
                throw  new SearchFrameworkException(BusinessExceptionEnum.CREATE_INDEX_FAILED.getCode(), BusinessExceptionEnum.CREATE_INDEX_FAILED.getDesc());
            }

        }
        if (SyncTypeEnum.SYNC_MAPPING.equals(auditInfoBO.getSyncType())){
            try {
                XContentBuilder xContentBuilder = IndexMappingHelper.convertMapping2XContentBuilder(index.getIndexName(),index.getIndexMapping());
                putMappingRequest.source(xContentBuilder);
                acknowledgedResponse = client.indices().putMapping(putMappingRequest, RequestOptions.DEFAULT);
                acknowledged = acknowledgedResponse.isAcknowledged();
            } catch (IOException e) {
                log.error("sync update index mapping failed,indexName:{}",index.getIndexName(),e);
                auditInfoBO.setSyncStatus(SyncStatusEnum.SYNC_FAILURE.getCode());
                auditUpdateLogic.updateAuditIndexInfo(auditInfoBO);
                throw  new SearchFrameworkException(BusinessExceptionEnum.CREATE_INDEX_FAILED.getCode(), BusinessExceptionEnum.CREATE_INDEX_FAILED.getDesc());
            }
        }
        if (acknowledged){
            auditInfoBO.setSyncStatus(SyncStatusEnum.SYNC_SUCCESS.getCode());
        }else {
            auditInfoBO.setSyncStatus(SyncStatusEnum.SYNC_FAILURE.getCode());
        }
        return auditUpdateLogic.updateAuditIndexInfo(auditInfoBO);
    }

    /**
     * 创建ES索引信息
     * @param index
     * @param client
     * @param auditInfoBO
     * @return
     */
    public boolean createEsIndexInfo(IndexSettings index, RestHighLevelClient client, AuditInfoBO auditInfoBO) {
        CreateIndexResponse createIndexResponse = null;
        boolean acknowledged = false;
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index.getIndexName());
        if (SyncTypeEnum.SYNC_ALL.equals(auditInfoBO.getSyncType())){
            try {
                createIndexRequest.settings(Settings.builder()
                        .put("index.number_of_shards",Integer.parseInt(index.getNumberOfShards()))
                        .put("index.number_of_replicas",Integer.parseInt(index.getNumberOfReplicas())).build());
                XContentBuilder xContentBuilder = IndexMappingHelper.convertMapping2XContentBuilder(index.getIndexName(),index.getIndexMapping());
                createIndexRequest.mapping(xContentBuilder);
                createIndexResponse = client.indices().create(createIndexRequest,RequestOptions.DEFAULT);
                acknowledged = createIndexResponse.isAcknowledged();
            } catch (IOException e) {
                log.error("sync all index info failed,indexName:{}",index.getIndexName(),e);
                auditInfoBO.setSyncStatus(SyncStatusEnum.SYNC_FAILURE.getCode());
                auditUpdateLogic.updateAuditIndexInfo(auditInfoBO);
                throw new SearchFrameworkException(BusinessExceptionEnum.CREATE_INDEX_FAILED.getCode(), BusinessExceptionEnum.CREATE_INDEX_FAILED.getDesc());
            }


        }
        if (SyncTypeEnum.SYNC_SETTING.equals(auditInfoBO.getSyncType())){
            createIndexRequest.settings(Settings.builder()
                    .put("index.number_of_shards",Integer.parseInt(index.getNumberOfShards()))
                    .put("index.number_of_replicas",Integer.parseInt(index.getNumberOfReplicas())).build());
            try {
                 createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
                 acknowledged = createIndexResponse.isAcknowledged();
                // acknowledged true create success false create failed
            } catch (IOException e) {
                log.error("sync index shards or replicas failed,indexName:{}",index.getIndexName(),e);
                auditInfoBO.setSyncStatus(SyncStatusEnum.SYNC_FAILURE.getCode());
                auditUpdateLogic.updateAuditIndexInfo(auditInfoBO);
                throw new SearchFrameworkException(BusinessExceptionEnum.CREATE_INDEX_FAILED.getCode(), BusinessExceptionEnum.CREATE_INDEX_FAILED.getDesc());
            }
        }
        if (SyncTypeEnum.SYNC_MAPPING.equals(auditInfoBO.getSyncType())){
            try {
                XContentBuilder xContentBuilder = IndexMappingHelper.convertMapping2XContentBuilder(index.getIndexName(),index.getIndexMapping());
                createIndexRequest.mapping(xContentBuilder);
                createIndexResponse = client.indices().create(createIndexRequest,RequestOptions.DEFAULT);
                acknowledged = createIndexResponse.isAcknowledged();
            } catch (IOException e) {
                log.error("sync index mapping failed,indexName:{}",index.getIndexName(),e);
                auditInfoBO.setSyncStatus(SyncStatusEnum.SYNC_FAILURE.getCode());
                auditUpdateLogic.updateAuditIndexInfo(auditInfoBO);
                throw new SearchFrameworkException(BusinessExceptionEnum.CREATE_INDEX_FAILED.getCode(), BusinessExceptionEnum.CREATE_INDEX_FAILED.getDesc());
            }
        }
        if (acknowledged){
            auditInfoBO.setSyncStatus(SyncStatusEnum.SYNC_SUCCESS.getCode());
        }else {
            auditInfoBO.setSyncStatus(SyncStatusEnum.SYNC_FAILURE.getCode());
        }

        return auditUpdateLogic.updateAuditIndexInfo(auditInfoBO);
    }

    public boolean checkIndexExists(String indexName, RestHighLevelClient client) {
        GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
        boolean exists = false;
        try {
            exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("check elasticsearch exist index:{}",indexName,e);
            throw new SearchFrameworkException(BusinessExceptionEnum.SYSTEM_ERROR.getCode(), BusinessExceptionEnum.SYSTEM_ERROR.getDesc());
        }
        return exists;
    }
}
