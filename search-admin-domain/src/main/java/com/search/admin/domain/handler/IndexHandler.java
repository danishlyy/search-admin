package com.search.admin.domain.handler;

import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.domain.logic.IndexAddLogic;
import com.search.admin.domain.logic.IndexDeleteLogic;
import com.search.admin.domain.logic.IndexQueryLogic;
import com.search.admin.domain.logic.IndexUpdateLogic;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        if (exist){
            log.warn("indexName:{}",indexSettingBO.getIndexName());
            throw new SearchFrameworkException(BusinessExceptionEnum.index_name_exist.getCode(),BusinessExceptionEnum.index_name_exist.getDesc());
        }
        return indexAddLogic.addIndexSetting(indexSettingBO);
    }
}
