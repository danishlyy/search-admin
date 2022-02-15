package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class AuditInfoResultDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7489425279979183823L;

    private String id;

    private String indexSettingsId;

    private String syncType;

    private String syncStatus;

    private String modifier;

    private String modifyTime;

    private String deleteFlag;

    private String indexName;

    private String auditType;

    private String indexStatus;

    private String reindexFlag;

    private String reindexStatus;

    private String noticeTime;
}
