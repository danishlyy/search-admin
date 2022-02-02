package com.search.admin.infra.validate.impl;

import cn.hutool.core.util.StrUtil;
import com.search.admin.infra.util.Constant;
import com.search.admin.infra.validate.IndexNameLimitation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.StandardCharsets;

@Slf4j
public class IndexNameLimitationValidator implements ConstraintValidator<IndexNameLimitation,String> {

    private static final char[] symbolChar = {
            Constant.symbol_004,
            Constant.symbol_006,
            Constant.symbol_007,
            Constant.symbol_008,
            Constant.symbol_009,
            Constant.symbol_010,
            Constant.symbol_011,
            Constant.symbol_012,
            Constant.symbol_013,
            Constant.symbol_014,
            Constant.symbol_015,
            Constant.symbol_016,
            Constant.symbol_017
    };



    @Override
    public void initialize(IndexNameLimitation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * 必须是小写字母
     * 不可以包含\, /, *, ?, ", <, >, |, ` ` (space character), ,, #
     * 不可以以-、_、+开头
     * 不可以是.或者..
     * 不可以超过255byte
     * @param indexName
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String indexName, ConstraintValidatorContext context) {
        log.info("indexName:{}",indexName);
        if (StringUtils.isBlank(indexName)){
            return false;
        }
        if (indexName.startsWith(Constant.symbol_001) || indexName.startsWith(Constant.symbol_002) || indexName.startsWith(Constant.symbol_003)){
            return false;
        }
        if (StringUtils.containsAny(indexName,symbolChar)){
            return false;
        }
        if (!StrUtil.isLowerCase(indexName)){
            return false;
        }
        byte[] bytes = indexName.getBytes(StandardCharsets.UTF_8);
        if (bytes.length >= 255){
            return false;
        }
        return true;
    }
}
