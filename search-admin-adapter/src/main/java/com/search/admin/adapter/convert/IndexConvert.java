package com.search.admin.adapter.convert;

import com.search.admin.adapter.base.PageResponseVO;
import com.search.admin.adapter.request.IndexAddRequestVO;
import com.search.admin.adapter.request.IndexSettingAddRequestVO;
import com.search.admin.adapter.request.IndexSettingUpdateRequestVO;
import com.search.admin.adapter.response.IndexResponseVO;
import com.search.admin.adapter.response.IndexSettingResponseVO;
import com.search.admin.app.service.dto.IndexAddDTO;
import com.search.admin.app.service.dto.IndexDTO;
import com.search.admin.app.service.dto.IndexSettingDTO;
import com.search.admin.app.service.dto.PageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IndexConvert {

    IndexConvert INSTANCE = Mappers.getMapper(IndexConvert.class);

    IndexAddDTO convertIndexAddRequestVO2IndexAddDTO(IndexAddRequestVO indexAddRequestVO);

    IndexSettingDTO convertIndexSettingRequestVO2IndexSettingDTO(IndexSettingAddRequestVO requestVO);

    IndexSettingDTO convertIndexSettingUpdateRequestVO2IndexSettingDTO(IndexSettingUpdateRequestVO requestVO);

    IndexSettingResponseVO convertIndexSettingDTO2IndexSettingResponseVO(IndexSettingDTO indexSetting);

    PageResponseVO<IndexResponseVO> convertPageIndexDTO2PageIndexResponseVO(PageDTO<IndexDTO> indexDTOPageDTO);
}
