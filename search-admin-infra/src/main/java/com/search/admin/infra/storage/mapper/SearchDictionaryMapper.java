package com.search.admin.infra.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.search.admin.infra.storage.entity.SearchDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 搜索字典表 Mapper 接口
 * </p>
 *
 * @author liyongyong
 * @since 2022-02-03
 */
public interface SearchDictionaryMapper extends BaseMapper<SearchDictionary> {

    List<SearchDictionary> listByValidateConditions(@Param("validateCondition") List<String> validateCondition);
}
