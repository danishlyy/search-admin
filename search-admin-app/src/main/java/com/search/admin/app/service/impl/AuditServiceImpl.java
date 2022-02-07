package com.search.admin.app.service.impl;

import com.search.admin.app.service.AuditService;
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
    public boolean confirmIndexConfiguration(String indexId) {
        return auditHandler.confirmIndexConfiguration(indexId);
    }
}
