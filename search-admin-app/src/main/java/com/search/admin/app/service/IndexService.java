package com.search.admin.app.service;


import com.search.admin.app.dto.*;

import java.util.List;

public interface IndexService {

    boolean createIndexMapping(IndexAddDTO indexAddDTO);

    boolean createIndexSetting(IndexSettingDTO indexSettingDTO);

    boolean updateIndexSetting(IndexSettingDTO indexSettingDTO);

    IndexSettingDTO getIndexSetting(String indexId);

    PageDTO<IndexDTO> pageQueryIndexes(IndexPageDTO indexPageDTO);

    boolean deleteIndex(List<IndexDTO> indexIdList);

    boolean updateIndexMapping(IndexAddDTO indexAddDTO);

    IndexDTO getIndexMapping(String indexId);
}
