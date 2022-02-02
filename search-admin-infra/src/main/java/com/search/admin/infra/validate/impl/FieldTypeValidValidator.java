package com.search.admin.infra.validate.impl;

import com.search.admin.infra.enums.FieldTypeEnum;
import com.search.admin.infra.validate.FieldTypeValid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义 ES 字段类型校验注解
 */
@Slf4j
public class FieldTypeValidValidator implements ConstraintValidator<FieldTypeValid,String> {
    @Override
    public void initialize(FieldTypeValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String type, ConstraintValidatorContext context) {
        if (StringUtils.isNotBlank(type)){
            log.warn("param field type:{}",type);
            for(FieldTypeEnum e:FieldTypeEnum.values()){
                if (type.equals(e.getCode())){
                    return true;
                }
            }
        }
        return false;
    }
}
