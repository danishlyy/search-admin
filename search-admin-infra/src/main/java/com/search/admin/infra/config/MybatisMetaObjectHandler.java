package com.search.admin.infra.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.search.admin.infra.util.Constant;
import com.search.admin.infra.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * prepare to  set value on entity field
 * MetaObjectHandler default strategy : if field has value,do not override the value ; the field value is null,do not set null
 * the field must use annotation @TableField and its properties fill to select the strategy
 */
@Component
@Slf4j
public class MybatisMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start execute MybatisMetaObjectHandler insertFill ");
        this.strictInsertFill(metaObject,"createTime",()-> DateTimeUtil.formatLocalDateTimeNow2String(),String.class);
        this.strictInsertFill(metaObject,"creator",()-> Constant.ADMIN,String.class);
        this.strictInsertFill(metaObject,"modifyTime",()-> DateTimeUtil.formatLocalDateTimeNow2String(),String.class);
        this.strictInsertFill(metaObject,"modifier",()-> Constant.ADMIN,String.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start execute MybatisMetaObjectHandler updateFill ");
        this.strictInsertFill(metaObject,"modifyTime",()-> DateTimeUtil.formatLocalDateTimeNow2String(),String.class);
    }
}
