package com.search.admin.domain.convert;

import com.search.admin.domain.bo.DictionaryBO;
import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.entity.SearchDictionary;
import com.search.admin.infra.util.DefaultValueUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(imports = DefaultValueUtil.class)
public interface BO2EntityConvert {

    BO2EntityConvert INSTANCE = Mappers.getMapper(BO2EntityConvert.class);


    @Mapping(target = "numberOfShards",expression = "java(DefaultValueUtil.setDefaultNumberOfShardsIfNull(source.getNumberOfShards()))")
    @Mapping(target = "numberOfReplicas",expression = "java(DefaultValueUtil.setDefaultNumberOfReplicasIfNull(source.getNumberOfShards()))")
    IndexSettings convertIndexSettingBO2IndexSetting(IndexSettingBO source);

    List<SearchDictionary> convertDictionaryBOList2DictionaryEntityList(List<DictionaryBO> list);
}
