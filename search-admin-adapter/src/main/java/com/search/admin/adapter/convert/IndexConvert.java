package com.search.admin.adapter.convert;

import com.search.admin.adapter.base.PageResponseVO;
import com.search.admin.adapter.request.*;
import com.search.admin.adapter.response.IndexMappingResponseVO;
import com.search.admin.adapter.response.IndexResponseVO;
import com.search.admin.adapter.response.IndexSettingResponseVO;
import com.search.admin.app.dto.IndexAddDTO;
import com.search.admin.app.dto.IndexDTO;
import com.search.admin.app.dto.IndexSettingDTO;
import com.search.admin.app.dto.PageDTO;
import com.search.admin.infra.util.DefaultValueUtil;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(imports = {DefaultValueUtil.class})
public interface IndexConvert {

    IndexConvert INSTANCE = Mappers.getMapper(IndexConvert.class);

    IndexAddDTO convertIndexAddRequestVO2IndexAddDTO(IndexAddRequestVO source);

    IndexSettingDTO convertIndexSettingRequestVO2IndexSettingDTO(IndexSettingAddRequestVO source);

    IndexSettingDTO convertIndexSettingUpdateRequestVO2IndexSettingDTO(IndexSettingUpdateRequestVO source);

    IndexSettingResponseVO convertIndexSettingDTO2IndexSettingResponseVO(IndexSettingDTO source);

    PageResponseVO<IndexResponseVO> convertPageIndexDTO2PageIndexResponseVO(PageDTO<IndexDTO> source);

    List<IndexDTO> convertIndexDeleteRequestVOList2IndexDTOList(List<IndexDeleteRequestVO> source);

    IndexAddDTO convertIndexUpdateRequestVO2IndexAddDTO(IndexUpdateRequestVO source);

    IndexMappingResponseVO convertIndexDTO2IndexMappingResponseVO(IndexDTO source);
}
