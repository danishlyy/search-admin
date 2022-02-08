package com.search.admin.domain.handler;

import com.search.admin.domain.bo.DictionaryBO;
import com.search.admin.domain.convert.BO2EntityConvert;
import com.search.admin.domain.logic.DictionaryLogic;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
@Slf4j
public class DictionaryHandler {

    @Autowired
    private DictionaryLogic dictionaryLogic;

    public boolean batchInsertDictionaries(List<DictionaryBO> list) {
        return dictionaryLogic.batchInsertDictionaries(BO2EntityConvert.INSTANCE.convertDictionaryBOList2DictionaryEntityList(list));
    }

    public boolean batchUpdateDictionaries(List<DictionaryBO> list) {
        return dictionaryLogic.batchUpdateDictionaries(BO2EntityConvert.INSTANCE.convertDictionaryBOList2DictionaryEntityList(list));
    }

    public boolean batchDeleteDictionaries(List<DictionaryBO> list) {
        return dictionaryLogic.batchDeleteDictionaries(BO2EntityConvert.INSTANCE.convertDictionaryBOList2DictionaryEntityList(list));
    }

    public List<DictionaryBO> queryDictionary(String dictionaryType) {
        if(StringUtils.isEmpty(dictionaryType)){
            throw new SearchFrameworkException(BusinessExceptionEnum.DICTIONARY_TYPE_IS_NULL.getCode(),BusinessExceptionEnum.DICTIONARY_TYPE_IS_NULL.getDesc());
        }
        return dictionaryLogic.queryDictionariesByDictionaryType(dictionaryType);
    }
}
