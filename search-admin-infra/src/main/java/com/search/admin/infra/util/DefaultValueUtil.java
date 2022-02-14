package com.search.admin.infra.util;

import com.search.admin.infra.enums.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class DefaultValueUtil {

    public static String setDefaultNumberOfShardsIfNull(String numberOfShards) {
        if (StringUtils.isBlank(numberOfShards)) {
            return "1";
        }
        return numberOfShards;
    }

    public static String setDefaultNumberOfReplicasIfNull(String numberOfReplicas) {
        if (StringUtils.isBlank(numberOfReplicas)) {
            return "1";
        }
        return numberOfReplicas;
    }

    public static String setInnerAnalyzerType(){
        return AnalyzerTypeEnum.INNER_ANALYZER_TYPE.getCode();
    }

    public static String setCustomAnalyzerType(){
        return AnalyzerTypeEnum.CUSTOM_ANALYZER_TYPE.getCode();
    }


    public static String setSyncStatusDefaultValue() {
        return SyncStatusEnum.WAIT_SYNC.getCode();
    }

    public static String setAuditTypeDefaultValue(){
        return AuditTypeEnum.WAIT_AUDIT.getCode();
    }

    public static String setNoticeTimeDefaultValue(){
        return DateTimeUtil.formatLocalDateTimeNow2String();
    }

    public static String transIndexStatus(String indexStatus){
        return IndexStatusEnum.INDEX_EFFECTIVE.getCode().equals(indexStatus)
                ? IndexStatusEnum.INDEX_EFFECTIVE.getDesc() : IndexStatusEnum.INDEX_INEFFECTIVE.getDesc();
    }
}
