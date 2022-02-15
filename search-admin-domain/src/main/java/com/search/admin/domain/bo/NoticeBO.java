package com.search.admin.domain.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeBO {

    private String indexId;

    private String indexName;

    private String syncStatus;

    private String auditType;

    private String indexStatus;

    private String reindexFlag;

    private String reIndexStatus;

    private String noticeTime;

    private String syncType;
}
