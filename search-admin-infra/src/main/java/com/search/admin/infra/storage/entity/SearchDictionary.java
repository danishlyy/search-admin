package com.search.admin.infra.storage.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * <p>
 * 搜索字典表
 * </p>
 *
 * @author liyongyong
 * @since 2022-02-03
 */
@TableName("search_dictionary")
@Data
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

}
