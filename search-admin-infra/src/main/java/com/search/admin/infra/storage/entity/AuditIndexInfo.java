package com.search.admin.infra.storage.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * <p>
 * 同步es索引信息历史表
 * </p>
 *
 * @author liyongyong
 * @since 2022-02-11
 */
@TableName("audit_index_info")
@Data
public class AuditIndexInfo {


    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 索引id
     */
    private String indexSettingsId;

    /**
     * 同步类型：0 setting同步 1 mapping同步 2 全部
     */
    private String syncType;

    /**
     * 同步结果1 已同步 0 未同步 2 同步失败
     */
    private String syncStatus;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String creator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifier;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifyTime;

    /**
     * 是否有效 0有效 1无效
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String deleteFlag;

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 审核类型 0待审核 1 审核通过  2 拒绝审核
     */
    private String auditType;

    /**
     * 索引状态0有效 1无效
     */
    private String indexStatus;

    private String reindexFlag;

    /**
     * reindex成功标志0成功1失败
     */
    private String reindexStatus;

    /**
     * 通知审核时间
     */
    private String noticeTime;


}
