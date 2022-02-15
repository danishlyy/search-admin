package com.search.admin.app.dto;

import com.search.admin.infra.base.PageBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class AuditInfoDTO extends PageBase implements Serializable {
    @Serial
    private static final long serialVersionUID = 8385506552027382802L;

    private String indexName;
    private String auditStatus;
}
