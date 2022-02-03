package com.search.admin.app.convert;

import com.search.admin.app.dto.DictionaryDTO;
import com.search.admin.app.dto.IndexDTO;
import com.search.admin.app.dto.IndexSettingDTO;
import com.search.admin.app.dto.PageDTO;
import com.search.admin.domain.bo.DictionaryBO;
import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.domain.bo.PageBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DTOConvert {

    DTOConvert INSTANCE = Mappers.getMapper(DTOConvert.class);

    IndexSettingBO convertIndexSettingDTO2IndexSettingBO(IndexSettingDTO indexSettingDTO);

    IndexSettingDTO convertIndexSettingBO2IndexSettingDTO(IndexSettingBO resultBO);

    PageDTO<IndexDTO> convertPageIndexSettingBO2PageIndexDTO(PageBO<IndexSettingBO> pageBO);

    List<DictionaryBO> convertDictionaryDTOList2DictionaryBOList(List<DictionaryDTO> list);
}
