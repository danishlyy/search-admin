package com.search.admin.infra.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateTimeUtil {

    public static final String PATTERN_0001 = "yyyyMMddHHmmss";
    public static final String PATTERN_0002 = "yyyy-MM-dd HH:mm:ss";

    public static String formatLocalDateTimeNow2String() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN_0001);
        String format = dateTimeFormatter.format(now);
        return format;
    }

    public static String formatDateTime2Pattern0002(String dateStr){
        if (StringUtils.isBlank(dateStr)){
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN_0001);
        LocalDateTime parse = LocalDateTime.parse(dateStr, dateTimeFormatter);
        String format = parse.format(DateTimeFormatter.ofPattern(PATTERN_0002));
        return format;
    }

    public static void main(String[] args) {


    }
}
