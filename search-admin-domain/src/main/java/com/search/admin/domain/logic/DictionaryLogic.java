package com.search.admin.domain.logic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.search.admin.domain.bo.DictionaryBO;
import com.search.admin.domain.convert.Entity2BOConvert;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import com.search.admin.infra.storage.entity.SearchDictionary;
import com.search.admin.infra.storage.service.ISearchDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DictionaryLogic {

    @Autowired
    private ISearchDictionaryService iSearchDictionaryService;

    public boolean batchInsertDictionaries(List<SearchDictionary> list) {
        List<String> validateCondition = new ArrayList<>(list.size());
        for (SearchDictionary item:list){
            validateCondition.add(new StringBuilder().append(item.getDictType()).append(item.getDictCode()).toString());
        }

        List<SearchDictionary> validateResult = iSearchDictionaryService.listByValidateConditions(validateCondition);
        List<String> msg = new ArrayList<>();
        String validateMsg = null;
        if (CollectionUtils.isNotEmpty(validateResult)){
            for (SearchDictionary item:validateResult){
                msg.add(new StringBuilder().append(item.getDictType()).append("_").append(item.getDictCode()).toString()) ;
            }
            validateMsg = msg.stream().collect(Collectors.joining(";"));
            throw new SearchFrameworkException(BusinessExceptionEnum.DICTIONARY_CODE_REPEATABLE.getCode(),String.format(BusinessExceptionEnum.DICTIONARY_CODE_REPEATABLE.getDesc(),validateMsg));
        }
        log.debug("result:{}",validateResult);
        return iSearchDictionaryService.saveBatch(list);
    }

    public boolean batchUpdateDictionaries(List<SearchDictionary> list) {
        return iSearchDictionaryService.saveOrUpdateBatch(list);
    }

    public boolean batchDeleteDictionaries(List<SearchDictionary> list) {
        return iSearchDictionaryService.removeBatchByIds(list.stream().map(SearchDictionary::getId).collect(Collectors.toList()));
    }

    public List<DictionaryBO> queryDictionariesByDictionaryType(String dictionaryType) {
        LambdaQueryWrapper<SearchDictionary> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SearchDictionary::getDictType,dictionaryType);
        return Entity2BOConvert.INSTANCE.convertDictionaryEntityList2DictionaryBOList(iSearchDictionaryService.list(queryWrapper));
    }
}
