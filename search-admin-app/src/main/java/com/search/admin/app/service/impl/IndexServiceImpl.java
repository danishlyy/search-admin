package com.search.admin.app.service.impl;

import com.search.admin.app.service.IndexService;
import com.search.admin.app.service.convert.DTOConvert;
import com.search.admin.app.service.dto.IndexAddDTO;
import com.search.admin.app.service.dto.IndexSettingDTO;
import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.domain.handler.IndexHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexHandler indexHandler;
    @Override
    public String createIndexMapping(IndexAddDTO indexAddDTO) {
        return null;
    }

    @Override
    public String createIndexSetting(IndexSettingDTO indexSettingDTO) {
        IndexSettingBO indexSettingBO = DTOConvert.INSTANCE.convertIndexSettingDTO2IndexSettingBO(indexSettingDTO);
        return indexHandler.createIndexSetting(indexSettingBO);
    }
}
