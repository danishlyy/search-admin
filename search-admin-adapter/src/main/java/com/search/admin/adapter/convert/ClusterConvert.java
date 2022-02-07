package com.search.admin.adapter.convert;

import com.search.admin.adapter.response.ClusterInfoResponse;
import com.search.admin.app.dto.ClusterInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClusterConvert {

    ClusterConvert INSTANCE = Mappers.getMapper(ClusterConvert.class);


    ClusterInfoResponse convertClusterInfo2ClusterInfoResponse(ClusterInfoDTO elasticSearchClusterInfo);
}
