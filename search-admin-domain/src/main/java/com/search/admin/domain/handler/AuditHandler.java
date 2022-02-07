package com.search.admin.domain.handler;

import com.search.admin.domain.logic.AuditLogic;
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




    public boolean confirmIndexConfiguration(String indexId) {
        IndexSettings indexSettings = indexQueryLogic.findIndexByIndexId(indexId);
        return auditLogic.syncIndexConfiguration(indexSettings);
    }
}
