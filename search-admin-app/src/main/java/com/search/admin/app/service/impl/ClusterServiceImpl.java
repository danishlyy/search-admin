package com.search.admin.app.service.impl;

import com.search.admin.app.convert.DTOConvert;
import com.search.admin.app.dto.ClusterInfoDTO;
import com.search.admin.app.service.ClusterService;
import com.search.admin.domain.bo.ClusterInfoBO;
import com.search.admin.domain.handler.ClusterInfoHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClusterServiceImpl implements ClusterService {

    @Autowired
    private ClusterInfoHandler clusterInfoHandler;

    @Override
    public ClusterInfoDTO getElasticSearchClusterInfo() {
        ClusterInfoBO clusterInfoBO = clusterInfoHandler.getElasticSearchClusterInfo();
        return DTOConvert.INSTANCE.convertClusterInfoBO2ClusterInfoDTO(clusterInfoBO);
    }
}
