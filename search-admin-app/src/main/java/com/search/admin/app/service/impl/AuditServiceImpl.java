package com.search.admin.app.service.impl;

import com.search.admin.app.convert.DTOConvert;
import com.search.admin.app.dto.AuditDTO;
import com.search.admin.app.dto.AuditInfoDTO;
import com.search.admin.app.dto.AuditInfoResultDTO;
import com.search.admin.app.dto.PageDTO;
import com.search.admin.app.service.AuditService;
import com.search.admin.domain.bo.AuditInfoBO;
import com.search.admin.domain.bo.PageBO;
import com.search.admin.domain.handler.AuditHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditHandler auditHandler;



    @Override
    public boolean auditDeleteIndex(String indexName) {
        return auditHandler.auditDeleteIndex(indexName);
    }

    @Override
    public PageDTO<AuditInfoResultDTO> auditInfoPageQuery(AuditInfoDTO auditInfoDTO) {
        PageBO<AuditInfoBO> pages = auditHandler.auditInfoPageQuery(DTOConvert.INSTANCE.convertAuditInfoDTO2AuditInfoBO(auditInfoDTO));
        return DTOConvert.INSTANCE.convertPageAuditInfoBO2PageAuditInfoResultDTO(pages);
    }

    @Override
    public boolean auditInfo(AuditDTO auditDTO) {
        AuditInfoBO auditInfoBO =  DTOConvert.INSTANCE.convertAuditDTO2AuditInfoBO(auditDTO);
        return  auditHandler.auditInfo(auditInfoBO);
    }
}
