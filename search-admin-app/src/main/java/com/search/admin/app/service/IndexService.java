package com.search.admin.app.service;


import com.search.admin.app.service.dto.IndexAddDTO;
import com.search.admin.app.service.dto.IndexDTO;
import com.search.admin.app.service.dto.IndexSettingDTO;
import com.search.admin.app.service.dto.PageDTO;

public interface IndexService {

    String createIndexMapping(IndexAddDTO indexAddDTO);

    boolean createIndexSetting(IndexSettingDTO indexSettingDTO);

    boolean updateIndexSetting(IndexSettingDTO indexSettingDTO);

    IndexSettingDTO getIndexSetting(String indexId);

    PageDTO<IndexDTO> pageQueryIndexes();
}
