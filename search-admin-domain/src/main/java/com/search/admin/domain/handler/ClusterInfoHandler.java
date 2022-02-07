package com.search.admin.domain.handler;

import com.search.admin.domain.bo.ClusterInfoBO;
import com.search.admin.domain.logic.ElasticClusterQueryLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClusterInfoHandler {

    @Autowired
    private ElasticClusterQueryLogic elasticClusterQueryLogic;

    public ClusterInfoBO getElasticSearchClusterInfo() {
        return elasticClusterQueryLogic.getElasticSearchClusterInfo();
    }
}
