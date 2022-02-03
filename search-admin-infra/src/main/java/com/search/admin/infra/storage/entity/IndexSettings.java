package com.search.admin.infra.storage.entity;

import com.baomidou.mybatisplus.annotation.*;

/**
 * <p>
 * 索引信息表
 * </p>
 *
 * @author liyongyong
 * @since 2022-02-03
 */
@TableName("index_settings")
public class IndexSettings {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 索引的描述信息
     */
    private String indexDesc;

    /**
     * 索引的setting设置
     */
    private String settings;

    /**
     * 字段映射
     */
    private String indexMapping;

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
     * 修改者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifier;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifyTime;

    /**
     * 是否有效 0有效1无效
     */
    @TableLogic
    private String deleteFlag;

    public String getIndexDesc() {
        return indexDesc;
    }

    public void setIndexDesc(String indexDesc) {
        this.indexDesc = indexDesc;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexMapping() {
        return indexMapping;
    }

    public void setIndexMapping(String indexMapping) {
        this.indexMapping = indexMapping;
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
        return "IndexSettings{" +
                "id=" + id +
                ", indexName=" + indexName +
                ", settings=" + settings +
                ", indexMapping=" + indexMapping +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", modifier=" + modifier +
                ", modifyTime=" + modifyTime +
                ", deleteFlag=" + deleteFlag +
                "}";
    }
}
