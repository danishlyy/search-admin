package com.search.admin.app.service.impl;

import com.search.admin.app.service.AuditService;
import com.search.admin.domain.handler.AuditHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AuditServiceImpl implements AuditService {

    private AuditHandler auditHandler;
    @Override
    public boolean confirmIndexConfiguration(List<String> indexIds) {
        return auditHandler.confirmIndexConfiguration(indexIds);
    }
}
