package com.search.admin.adapter.convert;

import com.search.admin.adapter.request.AnalyzerRequestVO;
import com.search.admin.app.dto.AnalyzerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnalyzerConvert {

    AnalyzerConvert INSTANCE = Mappers.getMapper(AnalyzerConvert.class);

    AnalyzerDTO convertAnalyzerRequestVO2AnalyzerDTO(AnalyzerRequestVO requestVO);
}
