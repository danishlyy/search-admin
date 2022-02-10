package com.search.admin.adapter.convert;

import com.search.admin.adapter.request.AnalyzerRequestVO;
import com.search.admin.adapter.request.CustomAnalyzerRequestVO;
import com.search.admin.app.dto.AnalyzerDTO;
import com.search.admin.infra.util.DefaultValueUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(imports = {DefaultValueUtil.class})
public interface AnalyzerConvert {

    AnalyzerConvert INSTANCE = Mappers.getMapper(AnalyzerConvert.class);

    @Mapping(target = "analyzerType",expression = "java(DefaultValueUtil.setInnerAnalyzerType())")
    AnalyzerDTO convertAnalyzerRequestVO2AnalyzerDTO(AnalyzerRequestVO source);

    @Mapping(target = "analyzerType",expression = "java(DefaultValueUtil.setCustomAnalyzerType())")
    AnalyzerDTO convertCustomAnalyzerRequestVO2AnalyzerDTO(CustomAnalyzerRequestVO requestVO);
}
