package com.search.admin.domain.logic;

import com.search.admin.infra.storage.entity.SearchDictionary;
import com.search.admin.infra.storage.service.ISearchDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DictionaryLogic {

    @Autowired
    private ISearchDictionaryService iSearchDictionaryService;

    public boolean batchInsertDictionaries(List<SearchDictionary> list) {
        return iSearchDictionaryService.saveBatch(list);
    }

    public boolean batchUpdateDictionaries(List<SearchDictionary> list) {
        return iSearchDictionaryService.saveOrUpdateBatch(list);
    }

    public boolean batchDeleteDictionaries(List<SearchDictionary> list) {
        return iSearchDictionaryService.removeBatchByIds(list.stream().map(SearchDictionary::getId).collect(Collectors.toList()));
    }
}
