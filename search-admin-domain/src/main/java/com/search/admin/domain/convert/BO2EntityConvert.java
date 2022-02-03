package com.search.admin.domain.convert;

import com.search.admin.domain.bo.DictionaryBO;
import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.entity.SearchDictionary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BO2EntityConvert {

    BO2EntityConvert INSTANCE = Mappers.getMapper(BO2EntityConvert.class);


    IndexSettings convertIndexSettingBO2IndexSetting(IndexSettingBO indexSettingBO);

    List<SearchDictionary> convertDictionaryBOList2DictionaryEntityList(List<DictionaryBO> list);
}
