package com.search.admin.domain.logic;

import com.search.admin.domain.bo.IndexBO;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.service.IIndexSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IndexUpdateLogic {

    @Autowired
    private IIndexSettingsService iIndexSettingsService;

    public boolean updateIndexSetting(IndexSettings indexSettings) {
        return iIndexSettingsService.updateById(indexSettings);
    }

    public String updateIndexMapping(IndexBO indexBO) {
        return null;
    }
}
