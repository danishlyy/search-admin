package com.search.admin.app.service;


public interface AuditService {

    boolean auditIndexSetting(String indexId);

    boolean auditIndexMapping(String indexId);
}
