package com.search.admin.infra.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
    public static void main(String[] args) {
        String s = "search_product";
        boolean lowerCase = StrUtil.isLowerCase(s);
        log.info("结果:{}",lowerCase);

    }
}
