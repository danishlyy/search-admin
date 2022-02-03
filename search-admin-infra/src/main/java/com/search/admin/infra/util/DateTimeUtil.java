package com.search.admin.infra.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateTimeUtil {

    public static final String PATTERN_0001 = "yyyyMMddHHmmss";

    public static String formatLocalDateTimeNow2String(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN_0001);
        String format = dateTimeFormatter.format(now);
        return format;
    }
}
