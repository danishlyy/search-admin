package com.search.admin.app.service.convert;

import com.search.admin.app.service.dto.IndexDTO;
import com.search.admin.app.service.dto.IndexSettingDTO;
import com.search.admin.app.service.dto.PageDTO;
import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.domain.bo.PageBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOConvert {

    DTOConvert INSTANCE = Mappers.getMapper(DTOConvert.class);

    IndexSettingBO convertIndexSettingDTO2IndexSettingBO(IndexSettingDTO indexSettingDTO);

    IndexSettingDTO convertIndexSettingBO2IndexSettingDTO(IndexSettingBO resultBO);

    PageDTO<IndexDTO> convertPageIndexSettingBO2PageIndexDTO(PageBO<IndexSettingBO> pageBO);
}
