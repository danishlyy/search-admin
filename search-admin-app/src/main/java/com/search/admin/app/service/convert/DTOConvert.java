package com.search.admin.app.service.convert;

import com.search.admin.app.service.dto.IndexSettingDTO;
import com.search.admin.domain.bo.IndexSettingBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOConvert {

    DTOConvert INSTANCE = Mappers.getMapper(DTOConvert.class);

    IndexSettingBO convertIndexSettingDTO2IndexSettingBO(IndexSettingDTO indexSettingDTO);
}
