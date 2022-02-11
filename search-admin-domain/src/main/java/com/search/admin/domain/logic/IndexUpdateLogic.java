package com.search.admin.domain.logic;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.search.admin.domain.bo.IndexBO;
import com.search.admin.domain.convert.BO2EntityConvert;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.service.IIndexSettingsService;
import com.search.admin.infra.util.Constant;
import com.search.admin.infra.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    public boolean updateIndexMapping(IndexBO indexBO) {
        IndexSettings indexSettings = BO2EntityConvert.INSTANCE.convertIndexBO2IndexSetting(indexBO);
        LambdaUpdateWrapper<IndexSettings> updateWrapper = Wrappers.lambdaUpdate(new IndexSettings());
        updateWrapper.eq(IndexSettings::getId,indexSettings.getId())
                .set(IndexSettings::getIndexMapping,indexSettings.getIndexMapping())
                .set(IndexSettings::getModifier, StringUtils.isBlank(indexSettings.getModifier()) ? Constant.ADMIN : indexSettings.getModifier())
                .set(IndexSettings::getModifyTime, DateTimeUtil.formatLocalDateTimeNow2String());
        return iIndexSettingsService.update(null,updateWrapper);
    }
}
