package com.search.admin.app.service;


import com.search.admin.app.dto.AuditDTO;
import com.search.admin.app.dto.AuditInfoDTO;
import com.search.admin.app.dto.AuditInfoResultDTO;
import com.search.admin.app.dto.PageDTO;

public interface AuditService {



    boolean auditDeleteIndex(String indexName);

    PageDTO<AuditInfoResultDTO> auditInfoPageQuery(AuditInfoDTO auditInfoDTO);

    boolean auditInfo(AuditDTO auditDTO);
}
