package com.search.admin.domain.convert;

import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.infra.storage.entity.IndexSettings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface Entity2BOConvert {

    Entity2BOConvert INSTANCE = Mappers.getMapper(Entity2BOConvert.class);

    @Mapping(target = "indexId", source = "id")
    IndexSettingBO convertIndexSettings2IndexSettingBO(IndexSettings entity);

    List<IndexSettingBO> convertIndexEntityList2IndexBOList(List<IndexSettings> records);

    List<IndexSettingBO> convertIndexSettingsList2IndexSettingBOList(List<IndexSettings> list);
}
