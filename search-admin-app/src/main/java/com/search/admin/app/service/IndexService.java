package com.search.admin.app.service;


import com.search.admin.app.service.dto.IndexAddDTO;
import com.search.admin.app.service.dto.IndexSettingDTO;

public interface IndexService {

     String createIndexMapping(IndexAddDTO indexAddDTO);

     boolean createIndexSetting(IndexSettingDTO indexSettingDTO);
}
