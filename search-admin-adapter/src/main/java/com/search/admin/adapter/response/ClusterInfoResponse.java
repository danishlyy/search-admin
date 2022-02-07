package com.search.admin.adapter.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class ClusterInfoResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 4761044253203566052L;

    private String clusterName;
    
    private String clusterStatus;

    
}
