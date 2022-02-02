package com.search.admin.adapter.convert;

import com.search.admin.adapter.request.IndexAddRequestVO;
import com.search.admin.app.service.dto.IndexAddDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IndexConvert {

    IndexConvert INSTANCE = Mappers.getMapper(IndexConvert.class);

    IndexAddDTO convertIndexAddRequestVO2IndexAddDTO(IndexAddRequestVO indexAddRequestVO);
}
