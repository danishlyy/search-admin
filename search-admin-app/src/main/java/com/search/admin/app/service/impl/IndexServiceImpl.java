package com.search.admin.app.service.impl;

import com.search.admin.app.convert.DTOConvert;
import com.search.admin.app.dto.IndexAddDTO;
import com.search.admin.app.dto.IndexDTO;
import com.search.admin.app.dto.IndexSettingDTO;
import com.search.admin.app.dto.PageDTO;
import com.search.admin.app.service.IndexService;
import com.search.admin.domain.bo.IndexBO;
import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.domain.bo.PageBO;
import com.search.admin.domain.handler.IndexHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexHandler indexHandler;

    @Override
    public boolean createIndexMapping(IndexAddDTO indexAddDTO) {
        IndexBO indexBO = DTOConvert.INSTANCE.convertIndexAddDTO2IndexBO(indexAddDTO);
        return indexHandler.createIndexMapping(indexBO);
    }

    @Override
    public boolean createIndexSetting(IndexSettingDTO indexSettingDTO) {
        IndexSettingBO indexSettingBO = DTOConvert.INSTANCE.convertIndexSettingDTO2IndexSettingBO(indexSettingDTO);
        return indexHandler.createIndexSetting(indexSettingBO);
    }

    @Override
    public boolean updateIndexSetting(IndexSettingDTO indexSettingDTO) {
        IndexSettingBO indexSettingBO = DTOConvert.INSTANCE.convertIndexSettingDTO2IndexSettingBO(indexSettingDTO);
        return indexHandler.updateIndexSetting(indexSettingBO);
    }

    @Override
    public IndexSettingDTO getIndexSetting(String indexId) {
        IndexSettingBO indexSettingBO = new IndexSettingBO();
        indexSettingBO.setIndexId(indexId);
        IndexSettingBO resultBO = indexHandler.getIndexSetting(indexSettingBO);
        return DTOConvert.INSTANCE.convertIndexSettingBO2IndexSettingDTO(resultBO);
    }

    @Override
    public PageDTO<IndexDTO> pageQueryIndexes() {
        PageBO<IndexSettingBO> pageBO = indexHandler.pageQueryIndexes();
        return DTOConvert.INSTANCE.convertPageIndexSettingBO2PageIndexDTO(pageBO);
    }

    @Override
    public boolean deleteIndex(List<IndexDTO> indexIdList) {
        List<IndexBO> list = DTOConvert.INSTANCE.convertIndexDTOList2IndexBOList(indexIdList);
        return indexHandler.deleteIndex(list);
    }

    @Override
    public boolean updateIndexMapping(IndexAddDTO indexAddDTO) {
        IndexBO indexBO = DTOConvert.INSTANCE.convertIndexAddDTO2IndexBO(indexAddDTO);
        return indexHandler.updateIndexMapping(indexBO);
    }
}
