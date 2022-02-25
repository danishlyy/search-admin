package com.search.admin.domain.logic;

import com.search.admin.domain.bo.NoticeBO;
import com.search.admin.domain.convert.BO2EntityConvert;
import com.search.admin.infra.storage.entity.AuditIndexInfo;
import com.search.admin.infra.storage.service.IAuditIndexInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NoticeLogic {

    @Autowired
    private IAuditIndexInfoService iAuditIndexInfoService;
    @Autowired
    private AuditLogic auditLogic;

    public boolean noticeReview(NoticeBO noticeBO) {
        AuditIndexInfo auditIndexInfo = BO2EntityConvert.INSTANCE.convertNoticeBO2AuditIndexInfo(noticeBO);
        String syncStatus = auditLogic.queryIndexAuditInfoHistory(noticeBO.getIndexName());
        auditIndexInfo.setSyncStatus(syncStatus);
        return iAuditIndexInfoService.save(auditIndexInfo);
    }
}
