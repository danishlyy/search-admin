package com.search.admin.adapter.convert;

import com.search.admin.adapter.base.PageResponseVO;
import com.search.admin.adapter.request.IndexAddRequestVO;
import com.search.admin.adapter.request.IndexDeleteRequestVO;
import com.search.admin.adapter.request.IndexSettingAddRequestVO;
import com.search.admin.adapter.request.IndexSettingUpdateRequestVO;
import com.search.admin.adapter.response.IndexResponseVO;
import com.search.admin.adapter.response.IndexSettingResponseVO;
import com.search.admin.app.dto.IndexAddDTO;
import com.search.admin.app.dto.IndexDTO;
import com.search.admin.app.dto.IndexSettingDTO;
import com.search.admin.app.dto.PageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IndexConvert {

    IndexConvert INSTANCE = Mappers.getMapper(IndexConvert.class);

    IndexAddDTO convertIndexAddRequestVO2IndexAddDTO(IndexAddRequestVO indexAddRequestVO);

    IndexSettingDTO convertIndexSettingRequestVO2IndexSettingDTO(IndexSettingAddRequestVO requestVO);

    IndexSettingDTO convertIndexSettingUpdateRequestVO2IndexSettingDTO(IndexSettingUpdateRequestVO requestVO);

    IndexSettingResponseVO convertIndexSettingDTO2IndexSettingResponseVO(IndexSettingDTO indexSetting);

    PageResponseVO<IndexResponseVO> convertPageIndexDTO2PageIndexResponseVO(PageDTO<IndexDTO> indexDTOPageDTO);

    List<IndexDTO> convertIndexDeleteRequestVOList2IndexDTOList(List<IndexDeleteRequestVO> request);
}
