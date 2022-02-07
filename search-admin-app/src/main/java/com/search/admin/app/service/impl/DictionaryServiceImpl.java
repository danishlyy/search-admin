package com.search.admin.app.service.impl;

import com.search.admin.app.convert.DTOConvert;
import com.search.admin.app.dto.DictionaryDTO;
import com.search.admin.app.service.DictionaryService;
import com.search.admin.domain.handler.DictionaryHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryHandler dictionaryHandler;

    @Override
    public boolean batchInsertDictionaries(List<DictionaryDTO> list) {
        return dictionaryHandler.batchInsertDictionaries(DTOConvert.INSTANCE.convertDictionaryDTOList2DictionaryBOList(list));
    }

    @Override
    public boolean batchUpdateDictionaries(List<DictionaryDTO> list) {
        return dictionaryHandler.batchUpdateDictionaries(DTOConvert.INSTANCE.convertDictionaryDTOList2DictionaryBOList(list));
    }

    @Override
    public boolean batchDeleteDictionaries(List<DictionaryDTO> list) {
        return dictionaryHandler.batchDeleteDictionaries(DTOConvert.INSTANCE.convertDictionaryDTOList2DictionaryBOList(list));
    }

    @Override
    public List<DictionaryDTO> queryDictionary(String dictionaryType) {
        return DTOConvert.INSTANCE.convertDictionaryBOList2DictionaryDTOList(dictionaryHandler.queryDictionary(dictionaryType));
    }
}
