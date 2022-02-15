package com.search.admin.adapter.request;

import com.search.admin.adapter.base.PageRequestVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class AuditRequestVO extends PageRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2523467493858376337L;

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 审核状态
     */
    private String auditStatus;
}
