package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeDTO {

    private String indexId;

    private String indexName;

    private String syncStatus;

    private String auditType;

    private String indexStatus;

    private String reIndexStatus;

    private String noticeTime;

    private String syncType;
}
