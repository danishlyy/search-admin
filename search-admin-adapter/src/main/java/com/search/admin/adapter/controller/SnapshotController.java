package com.search.admin.adapter.controller;

import com.search.admin.app.service.SnapshotService;
import com.search.admin.infra.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SnapshotController {

    @Autowired
    private SnapshotService snapshotService;

    @GetMapping(value = "/v1/create/index/snapshot")
    public Result<String> createIndexSnapshot(){
        return Result.success(snapshotService.createIndexSnapshot());
    }
}
