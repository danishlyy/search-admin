package com.search.admin.app.service;

import com.search.admin.app.dto.ClusterInfoDTO;

public interface ClusterService {
    ClusterInfoDTO getElasticSearchClusterInfo();
}
