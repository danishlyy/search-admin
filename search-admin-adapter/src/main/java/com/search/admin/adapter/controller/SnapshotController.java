package com.search.admin.adapter.controller;

import com.search.admin.adapter.request.IndexSnapshotRequestVO;
import com.search.admin.app.service.SnapshotService;
import com.search.admin.infra.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 索引快照
 */
@RestController
@Slf4j
public class SnapshotController {

    @Autowired
    private SnapshotService snapshotService;

    /**
     * 手工创建索引快照备份
     * @return
     */
    @GetMapping(value = "/v1/create/index/snapshot")
    public Result<String> createIndexSnapshot(){
        return Result.success(snapshotService.createIndexSnapshot());
    }


    /**
     * 按照索引快照名字进行快照恢复
     * @param requestVO
     * @return
     */
    @GetMapping(value = "/v1/restore/index/snapshot")
    public Result<Boolean> restoreIndexSnapshot(@RequestBody @Valid IndexSnapshotRequestVO requestVO){
        return Result.success(snapshotService.restoreIndexSnapshot(requestVO.getSnapshotName()));
    }

    /**
     * 按照索引快照名字删除快照
     * @param requestVO
     * @return
     */
    @GetMapping(value = "/v1/delete/index/snapshot")
    public Result<Boolean> deleteIndexSnapshot(@RequestBody @Valid IndexSnapshotRequestVO requestVO){
        return Result.success(snapshotService.deleteIndexSnapshot(requestVO.getSnapshotName()));
    }
}
