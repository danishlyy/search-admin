package com.search.admin.domain.handler;

import com.search.admin.domain.bo.IndexBO;
import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.domain.bo.PageBO;
import com.search.admin.domain.convert.Entity2BOConvert;
import com.search.admin.domain.logic.IndexAddLogic;
import com.search.admin.domain.logic.IndexDeleteLogic;
import com.search.admin.domain.logic.IndexQueryLogic;
import com.search.admin.domain.logic.IndexUpdateLogic;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import com.search.admin.infra.storage.entity.IndexSettings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public boolean createIndexSetting(IndexSettingBO indexSettingBO) {
        boolean exist = indexQueryLogic.findIndexByIndexName(indexSettingBO.getIndexName());
        if (exist) {
            log.warn("indexName:{}", indexSettingBO.getIndexName());
            throw new SearchFrameworkException(BusinessExceptionEnum.INDEX_NAME_EXIST.getCode(), BusinessExceptionEnum.INDEX_NAME_EXIST.getDesc());
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

        // 设置索引的分片新副本数
        indexSettings.setNumberOfReplicas(indexSettingBO.getNumberOfReplicas());
        return indexUpdateLogic.updateIndexSetting(indexSettings);
    }

    public IndexSettingBO getIndexSetting(IndexSettingBO indexSettingBO) {
        IndexSettings entity = indexQueryLogic.findIndexByIndexId(indexSettingBO.getIndexId());
        return Entity2BOConvert.INSTANCE.convertIndexSettings2IndexSettingBO(entity);
    }

    public PageBO<IndexSettingBO> pageQueryIndexes() {
        return indexQueryLogic.pageQueryIndexes();
    }

    public String createIndexMapping(IndexBO indexBO) {
        boolean exist = indexQueryLogic.findIndexByIndexName(indexBO.getIndexName());
        if (exist) {
            return indexUpdateLogic.updateIndexMapping(indexBO);
        }
        return indexAddLogic.addIndexMapping(indexBO);
    }
}
