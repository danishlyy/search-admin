package com.search.admin.app.service;

import com.search.admin.app.dto.DictionaryDTO;

import java.util.List;

public interface DictionaryService {

    boolean batchInsertDictionaries(List<DictionaryDTO> list);

    boolean batchUpdateDictionaries(List<DictionaryDTO> list);

    boolean batchDeleteDictionaries(List<DictionaryDTO> list);
}
