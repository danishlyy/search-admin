package com.search.admin.infra.storage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.search.admin.infra.storage.entity.SearchDictionary;
import com.search.admin.infra.storage.mapper.SearchDictionaryMapper;
import com.search.admin.infra.storage.service.ISearchDictionaryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 搜索字典表 服务实现类
 * </p>
 *
 * @author liyongyong
 * @since 2022-02-03
 */
@Service
public class SearchDictionaryServiceImpl extends ServiceImpl<SearchDictionaryMapper, SearchDictionary> implements ISearchDictionaryService {

    @Override
    public List<SearchDictionary> listByValidateConditions(List<String> validateCondition) {
        return baseMapper.listByValidateConditions( validateCondition);
    }
}
