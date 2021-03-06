package com.search.admin.domain.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.search.admin.domain.bo.*;
import com.search.admin.domain.helper.IndexMappingHelper;
import com.search.admin.infra.storage.entity.AuditIndexInfo;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.entity.SearchDictionary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(imports = {IndexMappingHelper.class})
public interface Entity2BOConvert {

    Entity2BOConvert INSTANCE = Mappers.getMapper(Entity2BOConvert.class);

    @Mapping(target = "indexId", source = "id")
    @Mapping(target = "indexStatus",source = "deleteFlag")
    IndexSettingBO convertIndexSettings2IndexSettingBO(IndexSettings source);

    List<IndexSettingBO> convertIndexEntityList2IndexBOList(List<IndexSettings> source);

    List<IndexSettingBO> convertIndexSettingsList2IndexSettingBOList(List<IndexSettings> source);

    List<DictionaryBO> convertDictionaryEntityList2DictionaryBOList(List<SearchDictionary> source);

    @Mapping(source = "id",target = "indexId")
    @Mapping(target = "fields",expression = "java(IndexMappingHelper.convertFieldStr2List(source.getIndexMapping()))")
    IndexBO convertIndexMapping2IndexBO(IndexSettings source);

    PageBO<AuditInfoBO> convertPageAuditIndexInfo2PageAuditInfoBO(Page<AuditIndexInfo> source);

    AuditInfoBO convertAuditIndexInfo2AuditIndexInfoBO(AuditIndexInfo auditIndexInfo);
}
