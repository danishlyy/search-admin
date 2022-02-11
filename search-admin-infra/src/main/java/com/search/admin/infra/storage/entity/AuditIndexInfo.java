package com.search.admin.infra.storage.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 同步es索引信息历史表
 * </p>
 *
 * @author liyongyong
 * @since 2022-02-11
 */
@TableName("audit_index_info")
public class AuditIndexInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 索引id
     */
    private String indexSettingsId;

    /**
     * 同步类型：0 setting同步 1 mapping同步
     */
    private String syncType;

    /**
     * 同步结果1 已同步 0 未同步
     */
    private String syncStatus;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 是否有效 0有效 1无效
     */
    private String deleteFlag;

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 审核类型1已审核0待审核
     */
    private String auditType;

    /**
     * 索引状态0有效 1无效
     */
    private String indexStatus;

    /**
     * reindex成功标志0成功1失败
     */
    private String reindexStatus;

    /**
     * 通知审核时间
     */
    private String noticeTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getIndexSettingsId() {
        return indexSettingsId;
    }

    public void setIndexSettingsId(String indexSettingsId) {
        this.indexSettingsId = indexSettingsId;
    }
    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }
    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }
    public String getIndexStatus() {
        return indexStatus;
    }

    public void setIndexStatus(String indexStatus) {
        this.indexStatus = indexStatus;
    }
    public String getReindexStatus() {
        return reindexStatus;
    }

    public void setReindexStatus(String reindexStatus) {
        this.reindexStatus = reindexStatus;
    }
    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }

    @Override
    public String toString() {
        return "AuditIndexInfo{" +
            "id=" + id +
            ", indexSettingsId=" + indexSettingsId +
            ", syncType=" + syncType +
            ", syncStatus=" + syncStatus +
            ", creator=" + creator +
            ", createTime=" + createTime +
            ", modifier=" + modifier +
            ", modifyTime=" + modifyTime +
            ", deleteFlag=" + deleteFlag +
            ", indexName=" + indexName +
            ", auditType=" + auditType +
            ", indexStatus=" + indexStatus +
            ", reindexStatus=" + reindexStatus +
            ", noticeTime=" + noticeTime +
        "}";
    }
}
