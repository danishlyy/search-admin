package com.search.admin.adapter.convert;

import com.search.admin.adapter.request.DictionaryAddRequestVO;
import com.search.admin.adapter.request.DictionaryDeleteRequestVO;
import com.search.admin.adapter.request.DictionaryUpdateRequestVO;
import com.search.admin.adapter.response.DictionaryVO;
import com.search.admin.app.dto.DictionaryDTO;
import com.search.admin.infra.util.DefaultValueUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(imports = {DefaultValueUtil.class})
public interface DictionaryConvert {

    DictionaryConvert INSTANCE = Mappers.getMapper(DictionaryConvert.class);

    List<DictionaryDTO> convertDictionaryAddRequestVOList2DictionaryDTOList(List<DictionaryAddRequestVO> source);

    List<DictionaryDTO> convertDictionaryUpdateRequestVOList2DictionaryDTOList(List<DictionaryUpdateRequestVO> source);

    List<DictionaryDTO> convertDictionaryDeleteRequestVOList2DictionaryDTOList(List<DictionaryDeleteRequestVO> source);

    @Mapping(target = "dicTypeDesc",expression = "java(DefaultValueUtil.transDictType(source.getDictType()))")
    DictionaryVO convertDictionaryDTO2DictionaryResponseVO(DictionaryDTO source);

    List<DictionaryVO> convertDictionaryDTOList2DictionaryResponseVOList(List<DictionaryDTO> source);
}
