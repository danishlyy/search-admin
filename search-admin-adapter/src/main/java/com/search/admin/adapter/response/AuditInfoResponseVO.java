package com.search.admin.adapter.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class AuditInfoResponseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -6628639804205406820L;

    /**
     * 主键
     */
    private String id;

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 索引状态0有效 1无效
     */
    private String indexStatus;


    /**
     * 审核类型 0待审核 1 审核通过  2 拒绝审核
     */
    private String auditType;

    /**
     * 同步结果1 已同步 0 未同步 2 同步失败
     */
    private String syncStatus;

    /**
     * 是否需要reindex 0 是 1 否
     */
    private String reindexFlag;

    /**
     * reindex成功标志0 是 1 否
     */
    private String reindexStatus;

    /**
     * 审核人
     */
    private String modifier;

    /**
     * 审核时间
     */
    private String modifyTime;

    /**
     * 通知审核时间
     */
    private String noticeTime;

    /**
     * 索引id
     */
    private String indexSettingsId;

    /**
     * 同步类型：0 setting同步 1 mapping同步 2 全部
     */
    private String syncType;

    private String syncTypeCode;

    /**
     * 审核副本分片按钮显示控制 0 显示 1不显示
     */
    private String auditSettingBtnFlag;

    /**
     * 审核映射按钮显示控制 0 显示 1不显示
     */
    private String auditMappingBtnFlag;

    /**
     * 审核删除索引按钮显示控制 0 显示 1不显示
     */
    private String auditDeleteIndexBtnFlag;

    /**
     * ReIndex按钮显示控制 0 显示 1不显示
     */
    private String auditReIndexBtnFlag;











}
