package com.search.admin.domain.helper;

import com.search.admin.domain.origin.bo.SettingBO;
import com.search.admin.domain.origin.bo.SettingIndexBO;
import com.search.admin.domain.origin.bo.SettingIndexPropertiesBO;
import com.search.admin.infra.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnalyzeJsonHelper {


    private static SettingIndexPropertiesBO commonAnalyze(String source) {
        SettingBO settingBO = JacksonUtil.toObject(source, SettingBO.class);
        SettingIndexBO settings = settingBO.getSettings();
        return settings.getIndex();
    }

    public static String analyzeNumberOfShards(String source) {
        SettingIndexPropertiesBO index = commonAnalyze(source);
        return index.getNumber_of_shards();
    }


    public static String analyzeNumberOfReplicas(String source) {
        SettingIndexPropertiesBO index = commonAnalyze(source);
        return index.getNumber_of_replicas();
    }
}
