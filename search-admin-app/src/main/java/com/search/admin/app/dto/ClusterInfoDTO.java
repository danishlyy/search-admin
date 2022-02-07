package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class ClusterInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3513451582196591456L;

    private String clusterName;

    private String clusterStatus;
}
