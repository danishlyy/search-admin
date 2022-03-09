package com.search.admin.domain.logic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.search.admin.domain.bo.AuditInfoBO;
import com.search.admin.domain.bo.PageBO;
import com.search.admin.domain.convert.Entity2BOConvert;
import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.enums.ElasticSearchKeysEnum;
import com.search.admin.infra.enums.SyncStatusEnum;
import com.search.admin.infra.enums.YesNoEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import com.search.admin.infra.storage.entity.AuditIndexInfo;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.service.IAuditIndexInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
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
    @Autowired
    private IndexDeleteLogic indexDeleteLogic;
    @Autowired
    private AuditUpdateLogic auditUpdateLogic;



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

                    boolean updateFlag = auditUpdateLogic.updateAuditIndexInfoInEffectiveByIndexName(indexName);
                    if (updateFlag){
                        indexDeleteLogic.physicalDeleteByIndexName(indexName);
                    }
                    return;
                }

                @Override
                public void onFailure(Exception e) {
                    log.error("delete index failed,indexName:{}",indexName,e);
                    return;
                }
            });
        }catch (Exception e){
            log.error("delete index failed,indexName:{}",indexName,e);
            throw new SearchFrameworkException(BusinessExceptionEnum.INDEX_DELETE_FAILED.getCode(),BusinessExceptionEnum.INDEX_DELETE_FAILED.getDesc() );
        }
        return true;
    }

    public PageBO<AuditInfoBO> auditInfoPageQuery(AuditInfoBO auditInfoBO) {
        LambdaQueryWrapper<AuditIndexInfo> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(auditInfoBO.getIndexName())){
            queryWrapper.like(AuditIndexInfo::getIndexName,auditInfoBO.getIndexName());
        }
        if (StringUtils.isNotBlank(auditInfoBO.getAuditType())){
            queryWrapper.eq(AuditIndexInfo::getAuditType,auditInfoBO.getAuditType());
        }
        queryWrapper.eq(AuditIndexInfo::getDeleteFlag, YesNoEnum.YES.getCode());
        queryWrapper.orderByDesc(AuditIndexInfo::getModifyTime);
        Page<AuditIndexInfo> page = new Page<>();
        page.setCurrent(Long.parseLong(auditInfoBO.getPageNumber()));
        page.setSize(Long.parseLong(auditInfoBO.getPageSize()));
        Page<AuditIndexInfo> pageResult = iAuditIndexInfoService.page(page, queryWrapper);
        return Entity2BOConvert.INSTANCE.convertPageAuditIndexInfo2PageAuditInfoBO(pageResult);
    }

    public AuditInfoBO findAuditInfoNewestByIndexId(String indexId) {
        LambdaQueryWrapper<AuditIndexInfo> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AuditIndexInfo::getIndexSettingsId,indexId);
        queryWrapper.orderByDesc(AuditIndexInfo::getModifyTime);
        queryWrapper.eq(AuditIndexInfo::getDeleteFlag,YesNoEnum.YES.getCode());
        queryWrapper.last(" limit 1");
        AuditIndexInfo auditIndexInfo = iAuditIndexInfoService.getOne(queryWrapper);
        return Entity2BOConvert.INSTANCE.convertAuditIndexInfo2AuditIndexInfoBO(auditIndexInfo);
    }

    public String queryIndexAuditInfoHistory(String indexName) {
        LambdaQueryWrapper<AuditIndexInfo> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.select(AuditIndexInfo::getSyncStatus)
                .eq(AuditIndexInfo::getIndexName,indexName)
                .eq(AuditIndexInfo::getDeleteFlag,YesNoEnum.YES.getCode())
                .orderByDesc(AuditIndexInfo::getModifyTime)
                .last(" limit 1");
        AuditIndexInfo one = iAuditIndexInfoService.getOne(queryWrapper);
        return one != null ? one.getSyncStatus() : SyncStatusEnum.WAIT_SYNC.getCode();
    }
}
