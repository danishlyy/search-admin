package com.search.admin.adapter.convert;

import com.search.admin.adapter.base.PageResponseVO;
import com.search.admin.adapter.request.AuditIndexRequestVO;
import com.search.admin.adapter.request.AuditRequestVO;
import com.search.admin.adapter.response.AuditInfoResponseVO;
import com.search.admin.app.dto.AuditDTO;
import com.search.admin.app.dto.AuditInfoDTO;
import com.search.admin.app.dto.AuditInfoResultDTO;
import com.search.admin.app.dto.PageDTO;
import com.search.admin.infra.util.DateTimeUtil;
import com.search.admin.infra.util.DefaultValueUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(imports = {DefaultValueUtil.class, DateTimeUtil.class})
public interface AuditConvert {

    AuditConvert INSTANCE = Mappers.getMapper(AuditConvert.class);


    AuditInfoDTO convertAuditResponseVO2AuditInfoDTO(AuditRequestVO source);


    @Mapping(target = "showAuditBtnFlag",expression = "java(DefaultValueUtil.showAuditBtnFlag(source.getAuditType(),source.getSyncStatus(),source.getIndexStatus()))")
    @Mapping(target = "auditDeleteIndexBtnFlag",expression = "java(DefaultValueUtil.showAuditDeleteIndexBtnFlag(source.getIndexStatus(),source.getSyncStatus()))")
    @Mapping(target = "auditReIndexBtnFlag",expression = "java(DefaultValueUtil.showAuditReIndexBtnFlag(source.getIndexStatus(),source.getAuditType(),source.getSyncStatus(),source.getReindexFlag()))")
    @Mapping(target = "syncType",expression = "java(DefaultValueUtil.transSyncType(source.getSyncType()))")
    @Mapping(target = "syncStatus",expression = "java(DefaultValueUtil.transSyncStatus(source.getSyncStatus()))")
    @Mapping(target = "indexStatus",expression = "java(DefaultValueUtil.transIndexStatus(source.getIndexStatus()))")
    @Mapping(target = "auditType",expression = "java(DefaultValueUtil.transAuditType(source.getAuditType()))")
    @Mapping(target = "reindexFlag",expression = "java(DefaultValueUtil.transReindexFlag(source.getReindexFlag()))")
    @Mapping(target = "reindexStatus",expression = "java(DefaultValueUtil.transReindexStatus(source.getReindexStatus()))")
    @Mapping(target = "modifyTime",expression = "java(DateTimeUtil.formatDateTime2Pattern0002(source.getModifyTime()))")
    @Mapping(target = "noticeTime",expression = "java(DateTimeUtil.formatDateTime2Pattern0002(source.getNoticeTime()))")
    @Mapping(target = "syncTypeCode",source = "syncType")
    AuditInfoResponseVO convertAuditInfoResultDTO2AuditInfoResponseVO(AuditInfoResultDTO source);

    PageResponseVO<AuditInfoResponseVO> convertPageDTOAuditInfoResultDTO2PageResponseVOAuditInfoResponseVO(PageDTO<AuditInfoResultDTO> source);

    AuditDTO convertAuditRequestVO2AuditDTO(AuditIndexRequestVO requestVO);
}
