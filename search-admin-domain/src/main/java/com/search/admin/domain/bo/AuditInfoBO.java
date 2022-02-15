package com.search.admin.domain.bo;

import com.search.admin.infra.base.PageBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuditInfoBO extends PageBase {


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
