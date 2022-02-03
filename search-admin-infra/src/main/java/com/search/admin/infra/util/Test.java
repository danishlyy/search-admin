package com.search.admin.infra.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class Test {
    public static void main(String[] args) {
        String s = "search_product";
        boolean lowerCase = StrUtil.isLowerCase(s);
        log.info("结果:{}", lowerCase);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String format = dateTimeFormatter.format(now);
        log.info("time:{}", format);


    }
}
