package com.search.admin.infra.util;

import com.search.admin.infra.enums.AnalyzerTypeEnum;
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


}
