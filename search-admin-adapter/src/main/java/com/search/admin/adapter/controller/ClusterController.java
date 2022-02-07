package com.search.admin.adapter.controller;

import com.search.admin.adapter.convert.ClusterConvert;
import com.search.admin.adapter.response.ClusterInfoResponse;
import com.search.admin.app.dto.ClusterInfoDTO;
import com.search.admin.app.service.ClusterService;
import com.search.admin.infra.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ClusterController {

    @Autowired
    private ClusterService clusterService;


    /**
     * 查看集群健康状态
     * @return
     */
    @GetMapping(value = "/v1/get/cluster/health")
    public Result<ClusterInfoResponse> getElasticSearchClusterInfo(){
        ClusterInfoDTO elasticSearchClusterInfo = clusterService.getElasticSearchClusterInfo();
        return Result.success(ClusterConvert.INSTANCE.convertClusterInfo2ClusterInfoResponse(elasticSearchClusterInfo));
    }
}
