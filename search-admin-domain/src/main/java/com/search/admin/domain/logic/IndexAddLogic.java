package com.search.admin.domain.logic;

import com.search.admin.domain.bo.IndexBO;
import com.search.admin.domain.bo.IndexMappingPropertiesBO;
import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.domain.convert.BO2EntityConvert;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.service.IIndexSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class IndexAddLogic {

    @Autowired
    private IIndexSettingsService iIndexSettingsService;

    public boolean addIndexSetting(IndexSettingBO indexSettingBO) {
        IndexSettings indexSettings = BO2EntityConvert.INSTANCE.convertIndexSettingBO2IndexSetting(indexSettingBO);
        return iIndexSettingsService.save(indexSettings);
    }


    public String addIndexMapping(IndexBO indexBO) {
        IndexSettings indexSettings = new IndexSettings();
        indexSettings.setIndexName(indexBO.getIndexName());
        List<IndexMappingPropertiesBO> fields = indexBO.getFields();
        for (IndexMappingPropertiesBO properties:fields){
            String fieldName = properties.getFieldName();
            String fieldType = properties.getFieldType();
        }
        return null;
    }
}
