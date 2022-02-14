package com.search.admin.app.convert;

import com.search.admin.app.dto.*;
import com.search.admin.domain.bo.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DTOConvert {

    DTOConvert INSTANCE = Mappers.getMapper(DTOConvert.class);

    IndexSettingBO convertIndexSettingDTO2IndexSettingBO(IndexSettingDTO source);

    IndexSettingDTO convertIndexSettingBO2IndexSettingDTO(IndexSettingBO source);

    PageDTO<IndexDTO> convertPageIndexSettingBO2PageIndexDTO(PageBO<IndexSettingBO> source);

    List<DictionaryBO> convertDictionaryDTOList2DictionaryBOList(List<DictionaryDTO> source);

    AnalyzerBO convertAnalyzerDTO2AnalyzerBO(AnalyzerDTO source);

    IndexBO convertIndexAddDTO2IndexBO(IndexAddDTO source);

    ClusterInfoDTO convertClusterInfoBO2ClusterInfoDTO(ClusterInfoBO source);

    List<DictionaryDTO> convertDictionaryBOList2DictionaryDTOList(List<DictionaryBO> source);

    List<IndexBO> convertIndexDTOList2IndexBOList(List<IndexDTO> source);


    IndexDTO convertIndexBO2IndexDTO(IndexBO source);

    NoticeBO convertNoticeBO2NoticeDTO(NoticeDTO source);

    IndexPageConditionBO convertIndexPageDTO2IndexPageBO(IndexPageDTO source);
}
