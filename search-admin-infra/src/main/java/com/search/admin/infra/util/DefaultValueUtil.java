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
    public static String showAuditDeleteIndexBtnFlag(String indexStatusCode){
        if (StringUtils.isBlank(indexStatusCode)){
            return null;
        }
        return IndexStatusEnum.INDEX_INEFFECTIVE.getCode().equals(indexStatusCode)
                ? YesNoEnum.YES.getCode() : YesNoEnum.NO.getCode();
    }

    /**
     * 审核状态为待审核、同步状态为未同步、同步类型是 setting或者全部 显示审核分片副本按钮
     *
     * @param auditTpe
     * @param syncStatus
     * @param syncType
     * @return
     */
    public static String showAuditSettingBtnFlag(String auditTpe,String syncStatus,String syncType){
       if (AuditTypeEnum.WAIT_AUDIT.getCode().equals(auditTpe)
               && SyncStatusEnum.WAIT_SYNC.getCode().equals(syncStatus)
               && (SyncTypeEnum.SYNC_ALL.getCode().equals(syncType) || SyncTypeEnum.SYNC_SETTING.equals(syncType))){
           return YesNoEnum.YES.getCode();
       }
       return YesNoEnum.NO.getCode();
    }

    /**
     * 审核状态为待审核、同步状态为未同步、同步类型是 mapping或者全部 显示审核分片副本按钮
     *
     * @param auditTpe
     * @param syncStatus
     * @param syncType
     * @return
     */
    public static String showAuditMappingBtnFlag(String auditTpe,String syncStatus,String syncType){
        if (AuditTypeEnum.WAIT_AUDIT.getCode().equals(auditTpe)
                && SyncStatusEnum.WAIT_SYNC.getCode().equals(syncStatus)
                && (SyncTypeEnum.SYNC_ALL.getCode().equals(syncType) || SyncTypeEnum.SYNC_SETTING.equals(syncType))){
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
