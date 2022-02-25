package com.search.admin.domain.logic;

import com.search.admin.domain.bo.ClusterInfoBO;
import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class ElasticClusterQueryLogic {

    @Autowired
    private SearchAdminClient searchAdminClient;


    public ClusterInfoBO getElasticSearchClusterInfo() {
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        ClusterHealthRequest healthRequest = new ClusterHealthRequest();
        ClusterHealthResponse health = null;
        try {
            health = client.cluster().health(healthRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("cluster status failed",e);
            throw new SearchFrameworkException(BusinessExceptionEnum.CLUSTER_HEALTH_FAILED.getCode(),BusinessExceptionEnum.CLUSTER_HEALTH_FAILED.getDesc());
        }
        String clusterName = health.getClusterName();
        ClusterHealthStatus status = health.getStatus();
        String name = status.name();
        ClusterInfoBO clusterInfoBO = new ClusterInfoBO();
        clusterInfoBO.setClusterName(clusterName);
        clusterInfoBO.setClusterStatus(name);
        return clusterInfoBO;
    }
}
