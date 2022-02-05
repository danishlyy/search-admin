package com.search.admin.adapter.controller;

import com.search.admin.adapter.request.ConfirmIndexRequestVO;
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

    @PostMapping(value = "/v1/confirm/indexes")
    public Result<Boolean> confirmIndexConfiguration(@RequestBody @Valid ConfirmIndexRequestVO requestVO){
        return Result.success(auditService.confirmIndexConfiguration(requestVO.getIndexIds()));
    }
}
