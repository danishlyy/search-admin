package com.search.admin.domain.handler;

import com.search.admin.domain.bo.AuditInfoBO;
import com.search.admin.domain.bo.PageBO;
import com.search.admin.domain.logic.AuditLogic;
import com.search.admin.domain.logic.AuditUpdateLogic;
import com.search.admin.domain.logic.IndexQueryLogic;
import com.search.admin.infra.storage.entity.IndexSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuditHandler {

    @Autowired
    private IndexQueryLogic indexQueryLogic;
    @Autowired
    private AuditLogic auditLogic;
    @Autowired
    private AuditUpdateLogic auditUpdateLogic;



    public boolean auditDeleteIndex(String indexName) {
        return auditLogic.auditDeleteIndex(indexName);
    }

    public PageBO<AuditInfoBO> auditInfoPageQuery(AuditInfoBO auditInfoBO) {
        return auditLogic.auditInfoPageQuery(auditInfoBO);
    }

    public boolean auditInfo(AuditInfoBO auditInfoBO) {
        return auditUpdateLogic.auditInfo(auditInfoBO);
    }
}
