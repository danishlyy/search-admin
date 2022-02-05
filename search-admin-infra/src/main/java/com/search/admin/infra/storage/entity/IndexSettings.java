package com.search.admin.infra.storage.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * <p>
 * 索引信息表
 * </p>
 *
 * @author liyongyong
 * @since 2022-02-03
 */
@TableName("index_settings")
@Data
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
    private String numberOfShards;

    /**
     * 索引的setting设置
     */
    private String numberOfReplicas;

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


}
