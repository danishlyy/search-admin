package com.search.admin.adapter.convert;

import com.search.admin.adapter.request.ReIndexRequestVO;
import com.search.admin.app.dto.ReIndexDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocumentConvert {

    DocumentConvert INSTANCE = Mappers.getMapper(DocumentConvert.class);

    ReIndexDTO convertReIndexRequestVO2ReIndexDTO(ReIndexRequestVO source);
}
