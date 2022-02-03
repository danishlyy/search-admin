package com.search.admin.domain.logic;

import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.domain.convert.BO2EntityConvert;
import com.search.admin.infra.storage.service.IIndexSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IndexAddLogic {

    @Autowired
    private IIndexSettingsService iIndexSettingsService;

    public boolean addIndexSetting(IndexSettingBO indexSettingBO) {
        return iIndexSettingsService.save(BO2EntityConvert.INSTANCE.convertIndexSettingBO2IndexSetting(indexSettingBO));
    }
}
