package com.search.admin.domain.logic;

import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.domain.convert.BO2EntityConvert;
import com.search.admin.domain.origin.bo.SettingBO;
import com.search.admin.domain.origin.bo.SettingIndexBO;
import com.search.admin.domain.origin.bo.SettingIndexPropertiesBO;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.service.IIndexSettingsService;
import com.search.admin.infra.util.DefaultValueUtil;
import com.search.admin.infra.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IndexAddLogic {

    @Autowired
    private IIndexSettingsService iIndexSettingsService;

    public boolean addIndexSetting(IndexSettingBO indexSettingBO) {
        IndexSettings indexSettings = BO2EntityConvert.INSTANCE.convertIndexSettingBO2IndexSetting(indexSettingBO);
        SettingBO settingBO = new SettingBO();
        SettingIndexBO settings = new SettingIndexBO();
        SettingIndexPropertiesBO index = new SettingIndexPropertiesBO();
        index.setNumber_of_shards(DefaultValueUtil.setDefaultNumberOfShardsIfNull(indexSettingBO.getNumberOfShards()));
        index.setNumber_of_replicas(DefaultValueUtil.setDefaultNumberOfReplicasIfNull(indexSettingBO.getNumberOfReplicas()));
        settings.setIndex(index);
        settingBO.setSettings(settings);
        indexSettings.setSettings(JacksonUtil.toJsonString(settingBO));
        return iIndexSettingsService.save(indexSettings);
    }


}
