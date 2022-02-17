package com.search.admin.domain.handler;

import com.search.admin.domain.bo.AuditInfoBO;
import com.search.admin.domain.bo.PageBO;
import com.search.admin.domain.logic.*;
import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.enums.AuditTypeEnum;
import com.search.admin.infra.storage.entity.IndexSettings;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
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
    @Autowired
    private SyncIndexLogic syncIndexLogic;
    @Autowired
    private SearchAdminClient searchAdminClient;
    @Autowired
    private FieldLogic fieldLogic;




    public boolean auditDeleteIndex(String indexName) {
        return auditLogic.auditDeleteIndex(indexName);
    }

    public PageBO<AuditInfoBO> auditInfoPageQuery(AuditInfoBO auditInfoBO) {
        return auditLogic.auditInfoPageQuery(auditInfoBO);
    }

    public boolean auditInfo(AuditInfoBO auditInfoBO) {
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        // 审核不通过 不需要同步到es
        if (AuditTypeEnum.AUDIT_REJECT.getCode().equals(auditInfoBO)){
            return auditUpdateLogic.updateAuditIndexInfo(auditInfoBO);
        }
        // 审核通过
        if (AuditTypeEnum.AUDIT_PASS.getCode().equals(auditInfoBO.getAuditType())){
            // 更新审核状态，
            IndexSettings index = indexQueryLogic.findIndexByIndexId(auditInfoBO.getIndexSettingsId());
            boolean exists = syncIndexLogic.checkIndexExists(index.getIndexName(),client);
            fieldLogic.validateFields(index,client);
            return   exists ? syncIndexLogic.updateEsIndexInfo(index,client,auditInfoBO) : syncIndexLogic.createEsIndexInfo(index,client,auditInfoBO) ;
        }
        return false;
    }
}
