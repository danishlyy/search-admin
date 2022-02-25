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
        for (IndexStatusEnum indexStatusEnum:IndexStatusEnum.values()){
            if (indexStatusEnum.getCode().equals(indexStatus)){
                return indexStatusEnum.getDesc();
            }
        }
        return null;
    }

    public static String transSyncType(String syncTypeCode){
        for (SyncTypeEnum syncTypeEnum:SyncTypeEnum.values()){
            if (syncTypeEnum.getCode().equals(syncTypeCode)){
                return syncTypeEnum.getDesc();
            }
        }
        return null;
    }

    public static String transAuditType(String auditTypeCode){
        for (AuditTypeEnum auditTypeEnum:AuditTypeEnum.values()){
            if (auditTypeEnum.getCode().equals(auditTypeCode)){
                return auditTypeEnum.getDesc();
            }
        }
        return null;
    }

    public static String transSyncStatus(String syncStatusCode){
        for (SyncStatusEnum syncStatusEnum:SyncStatusEnum.values()){
            if (syncStatusEnum.getCode().equals(syncStatusCode)){
                return syncStatusEnum.getDesc();
            }
        }
        return null;
    }

    public static String transReindexFlag(String reindexFlagCode){
        for (YesOrNoEnum yesOrNoEnum:YesOrNoEnum.values()){
            if (yesOrNoEnum.getCode().equals(reindexFlagCode)){
                return yesOrNoEnum.getDesc();
            }
        }
        return null;
    }

    public static String transReindexStatus(String reindexStatusCode){
        for (YesOrNoEnum yesOrNoEnum:YesOrNoEnum.values()){
            if (yesOrNoEnum.getCode().equals(reindexStatusCode)){
                return yesOrNoEnum.getDesc();
            }
        }
        return null;
    }

    /**
     * 当索引状态为无效的时候，才显示删除索引button
     * @param indexStatusCode
     * @return
     */
    public static String showAuditDeleteIndexBtnFlag(String indexStatusCode,String syncStatus){
        if (IndexStatusEnum.INDEX_INEFFECTIVE.getCode().equals(indexStatusCode) || SyncStatusEnum.SYNC_SUCCESS.getCode().equals(syncStatus)){
            return YesNoEnum.YES.getCode();
        }
        return YesNoEnum.NO.getCode();
    }
    /**
     * 审核状态为待审核、同步状态为未同步、索引有效 显示审核分片副本按钮
     *
     * @param auditTpe
     * @param syncStatus
     * @param indexStatus
     * @return
     */
    public static String showAuditBtnFlag(String auditTpe,String indexStatus){
        if (AuditTypeEnum.WAIT_AUDIT.getCode().equals(auditTpe) && YesNoEnum.YES.getCode().equals(indexStatus)){
            return YesNoEnum.YES.getCode();
        }
        return YesNoEnum.NO.getCode();
    }



    public static String showAuditReIndexBtnFlag(String indexStatus,String auditTpe,String syncStatus,String reIndexFlag){
        if (IndexStatusEnum.INDEX_EFFECTIVE.getCode().equals(indexStatus)
                && AuditTypeEnum.AUDIT_PASS.getCode().equals(auditTpe)
                && SyncStatusEnum.SYNC_SUCCESS.getCode().equals(syncStatus)
                && (YesNoEnum.YES.getCode().equals(reIndexFlag))){
            return YesNoEnum.YES.getCode();
        }
        return YesNoEnum.NO.getCode();
    }

    public static String transDictType(String dictType){
        DictionaryTypeEnum[] values = DictionaryTypeEnum.values();
        for (DictionaryTypeEnum dict:values){
            if (dictType.equals(dict.getCode())){
                return dict.getDesc();
            }
        }
        return null;
    }
}
