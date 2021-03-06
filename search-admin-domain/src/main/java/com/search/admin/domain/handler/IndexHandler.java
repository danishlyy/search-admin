package com.search.admin.domain.handler;

import com.search.admin.domain.bo.*;
import com.search.admin.domain.convert.Entity2BOConvert;
import com.search.admin.domain.logic.*;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import com.search.admin.infra.storage.entity.IndexSettings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class IndexHandler {
    @Autowired
    private IndexQueryLogic indexQueryLogic;
    @Autowired
    private IndexAddLogic indexAddLogic;
    @Autowired
    private IndexUpdateLogic indexUpdateLogic;
    @Autowired
    private IndexDeleteLogic indexDeleteLogic;
    @Autowired
    private FieldLogic fieldLogic;
    @Autowired
    private AuditLogic auditLogic;

    public boolean createIndexSetting(IndexSettingBO indexSettingBO) {
        boolean exist = indexQueryLogic.findIndexByIndexName(indexSettingBO.getIndexName());
        if (exist) {
            log.warn("indexName:{}", indexSettingBO.getIndexName());
            throw new SearchFrameworkException(BusinessExceptionEnum.INDEX_NAME_EXIST.getCode(), String.format(BusinessExceptionEnum.INDEX_NAME_EXIST.getDesc(),indexSettingBO.getIndexName()));
        }
        return indexAddLogic.addIndexSetting(indexSettingBO);
    }

    public boolean updateIndexSetting(IndexSettingBO indexSettingBO) {
        if (Integer.valueOf(indexSettingBO.getNumberOfReplicas()) < 1) {
            throw new SearchFrameworkException(BusinessExceptionEnum.NUMBER_OF_REPLICAS_ILLEGAL.getCode(), BusinessExceptionEnum.NUMBER_OF_REPLICAS_ILLEGAL.getDesc());
        }
        IndexSettings indexSettings = indexQueryLogic.findIndexByIndexId(indexSettingBO.getIndexId());
        if (Objects.isNull(indexSettings)) {
            log.warn("indexId:{} not exist", indexSettingBO.getIndexId());
            throw new SearchFrameworkException(BusinessExceptionEnum.INDEX_NOT_EXIST.getCode(), BusinessExceptionEnum.INDEX_NOT_EXIST.getDesc());
        }
        String shards = indexSettings.getNumberOfShards();
        String replicas = indexSettings.getNumberOfReplicas();
        if (StringUtils.isBlank(shards) ||  StringUtils.isBlank(replicas)) {
            log.warn("indexId:{}", indexSettingBO.getIndexId());
            throw new SearchFrameworkException(BusinessExceptionEnum.INDEX_SETTING_EMPTY.getCode(), BusinessExceptionEnum.INDEX_SETTING_EMPTY.getDesc());
        }

        // ?????????????????????????????????
        indexSettings.setNumberOfReplicas(indexSettingBO.getNumberOfReplicas());
        indexSettings.setIndexDesc(indexSettingBO.getIndexDesc());
        return indexUpdateLogic.updateIndexSetting(indexSettings);
    }

    public IndexSettingBO getIndexSetting(IndexSettingBO indexSettingBO) {
        if (StringUtils.isBlank(indexSettingBO.getIndexId())){
            log.warn("indexId:{}",indexSettingBO.getIndexId());
            throw new SearchFrameworkException(BusinessExceptionEnum.INDEX_NOT_EXIST.getCode(),BusinessExceptionEnum.INDEX_NOT_EXIST.getDesc() );
        }
        IndexSettings entity = indexQueryLogic.findIndexByIndexId(indexSettingBO.getIndexId());
        IndexSettingBO settingBO = Entity2BOConvert.INSTANCE.convertIndexSettings2IndexSettingBO(entity);
        ;
        AuditInfoBO auditInfoBO = auditLogic.findAuditInfoNewestByIndexId(indexSettingBO.getIndexId());
        if (Objects.nonNull(auditInfoBO) && Objects.nonNull(settingBO)){
            settingBO.setSyncStatus(auditInfoBO.getSyncStatus());
        }

        return settingBO;
    }

    public PageBO<IndexSettingBO> pageQueryIndexes(IndexPageConditionBO pageConditionBO) {
        return indexQueryLogic.pageQueryIndexes(pageConditionBO);
    }

    public boolean createIndexMapping(IndexBO indexBO) {
        boolean fieldsRepeat = fieldLogic.validateIndexMapping(indexBO);
        if (fieldsRepeat){
            throw new SearchFrameworkException(BusinessExceptionEnum.INDEX_FIELD_REPEATED.getCode(),
                    BusinessExceptionEnum.INDEX_FIELD_REPEATED.getDesc());
        }
        return indexUpdateLogic.updateIndexMapping(indexBO);
    }

    public boolean deleteIndex(List<IndexBO> list) {
        return indexDeleteLogic.deleteIndex(list);
    }

    public boolean updateIndexMapping(IndexBO indexBO) {
        return indexUpdateLogic.updateIndexMapping(indexBO);
    }

    public IndexBO getIndexMapping(String indexId) {
        AuditInfoBO auditInfoBO = auditLogic.findAuditInfoNewestByIndexId(indexId);
        IndexBO indexBo = indexQueryLogic.findIndexMappingByIndexId(indexId);
        if (ObjectUtils.isNotEmpty(indexBo) && ObjectUtils.isNotEmpty(auditInfoBO)){
            indexBo.setSyncStatus(auditInfoBO.getSyncStatus());
        }
        return indexBo;
    }
}
