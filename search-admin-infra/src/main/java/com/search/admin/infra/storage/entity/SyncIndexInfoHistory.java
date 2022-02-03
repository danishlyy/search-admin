package com.search.admin.infra.storage.entity;

import com.baomidou.mybatisplus.annotation.*;

/**
 * <p>
 * 同步es索引信息历史表
 * </p>
 *
 * @author liyongyong
 * @since 2022-02-03
 */
@TableName("sync_index_info_history")
public class SyncIndexInfoHistory {


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
     * 同步类型：0 setting同步 1 mapping同步
     */
    private String syncType;

    /**
     * 同步状态1 已同步 0 未同步
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
    private String deleteFlag;

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

    @Override
    public String toString() {
        return "SyncIndexInfoHistory{" +
                "id=" + id +
                ", indexSettingsId=" + indexSettingsId +
                ", syncType=" + syncType +
                ", syncStatus=" + syncStatus +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", modifier=" + modifier +
                ", modifyTime=" + modifyTime +
                ", deleteFlag=" + deleteFlag +
                "}";
    }
}
