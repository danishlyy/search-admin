package com.search.admin.adapter.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class IndexSnapshotRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1961710724285656652L;

    @NotBlank(message = "快照名称不可为空")
    private String snapshotName;
}
