package com.search.admin.infra.storage.entity;

import com.baomidou.mybatisplus.annotation.*;

/**
 * <p>
 * 搜索字典表
 * </p>
 *
 * @author liyongyong
 * @since 2022-02-03
 */
@TableName("search_dictionary")
public class SearchDictionary {


    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 字典代码
     */
    private String dictCode;

    /**
     * 字典值
     */
    private String dictValue;

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
     * 是否有效0有效1无效
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String deleteFlag;

    /**
     * 字典描述
     */
    private String dictDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
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

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    @Override
    public String toString() {
        return "SearchDictionary{" +
                "id=" + id +
                ", dictType=" + dictType +
                ", dictCode=" + dictCode +
                ", dictValue=" + dictValue +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", modifier=" + modifier +
                ", modifyTime=" + modifyTime +
                ", deleteFlag=" + deleteFlag +
                ", dictDesc=" + dictDesc +
                "}";
    }
}
