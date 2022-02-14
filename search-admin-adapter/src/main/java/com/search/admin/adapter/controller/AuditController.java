package com.search.admin.adapter.controller;

import com.search.admin.adapter.request.AuditIndexDeleteRequestVO;
import com.search.admin.adapter.request.AuditIndexRequestVO;
import com.search.admin.app.service.AuditService;
import com.search.admin.infra.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class AuditController {

    @Autowired
    private AuditService auditService;

    @PostMapping(value = "/v1/audit/index/setting")
    public Result<Boolean> auditIndexSetting(@RequestBody @Valid AuditIndexRequestVO requestVO){
        return Result.success(auditService.auditIndexSetting(requestVO.getIndexId()));
    }

    @PostMapping(value = "/v1/audit/index/mapping")
    public Result<Boolean> auditIndexMapping(@RequestBody @Valid AuditIndexRequestVO requestVO){
        return Result.success(auditService.auditIndexMapping(requestVO.getIndexId()));
    }

    @PostMapping(value = "/v1/audit/delete/index")
    public Result<Boolean> auditDeleteIndex(@RequestBody @Valid AuditIndexDeleteRequestVO requestVO){
        return Result.success(auditService.auditDeleteIndex(requestVO.getIndexName()));
    }
}
