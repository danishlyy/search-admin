package com.search.admin.adapter.convert;

import com.search.admin.adapter.request.DictionaryAddRequestVO;
import com.search.admin.adapter.request.DictionaryDeleteRequestVO;
import com.search.admin.adapter.request.DictionaryUpdateRequestVO;
import com.search.admin.adapter.response.DictionaryVO;
import com.search.admin.app.dto.DictionaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DictionaryConvert {

    DictionaryConvert INSTANCE = Mappers.getMapper(DictionaryConvert.class);

    List<DictionaryDTO> convertDictionaryAddRequestVOList2DictionaryDTOList(List<DictionaryAddRequestVO> request);

    List<DictionaryDTO> convertDictionaryUpdateRequestVOList2DictionaryDTOList(List<DictionaryUpdateRequestVO> request);

    List<DictionaryDTO> convertDictionaryDeleteRequestVOList2DictionaryDTOList(List<DictionaryDeleteRequestVO> request);

    DictionaryVO convertDictionaryDTO2DictionaryResponseVO(DictionaryDTO source);

    List<DictionaryVO> convertDictionaryDTOList2DictionaryResponseVOList(List<DictionaryDTO> source);
}
