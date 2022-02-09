package com.search.admin.app.service;


import com.search.admin.app.dto.IndexAddDTO;
import com.search.admin.app.dto.IndexDTO;
import com.search.admin.app.dto.IndexSettingDTO;
import com.search.admin.app.dto.PageDTO;
import com.search.admin.infra.base.ValidateList;

import java.util.List;

public interface IndexService {

    String createIndexMapping(IndexAddDTO indexAddDTO);

    boolean createIndexSetting(IndexSettingDTO indexSettingDTO);

    boolean updateIndexSetting(IndexSettingDTO indexSettingDTO);

    IndexSettingDTO getIndexSetting(String indexId);

    PageDTO<IndexDTO> pageQueryIndexes();

    boolean deleteIndex(List<IndexDTO> indexIdList);
}
