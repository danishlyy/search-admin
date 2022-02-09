package com.search.admin.domain.logic;

import com.search.admin.domain.bo.IndexBO;
import com.search.admin.infra.storage.service.IIndexSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class IndexDeleteLogic {

    @Autowired
    private IIndexSettingsService iIndexSettingsService;

    public boolean deleteIndex(List<IndexBO> list) {
        List<String> indexIds = list.stream().map(IndexBO::getIndexId).collect(Collectors.toList());
        return iIndexSettingsService.removeBatchByIds(indexIds,list.size());
    }
}
