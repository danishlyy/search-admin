package com.search.admin.domain.logic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.service.IIndexSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IndexQueryLogic {

    @Autowired
    private IIndexSettingsService iIndexSettingsService;

    public boolean findIndexByIndexName(String indexName) {
        LambdaQueryWrapper<IndexSettings> query = Wrappers.lambdaQuery();
        query.eq(IndexSettings::getIndexName,indexName);
        long count = iIndexSettingsService.count(query);
        return count > 0 ? true : false;
    }
}
