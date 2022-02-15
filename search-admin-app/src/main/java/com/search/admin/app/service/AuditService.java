package com.search.admin.app.service;


import com.search.admin.app.dto.AuditInfoDTO;
import com.search.admin.app.dto.AuditInfoResultDTO;
import com.search.admin.app.dto.PageDTO;

public interface AuditService {

    boolean auditIndexSetting(String indexId);

    boolean auditIndexMapping(String indexId);

    boolean auditDeleteIndex(String indexName);

    PageDTO<AuditInfoResultDTO> auditInfoPageQuery(AuditInfoDTO auditInfoDTO);

}
