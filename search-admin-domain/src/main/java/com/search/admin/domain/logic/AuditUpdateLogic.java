package com.search.admin.domain.logic;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.search.admin.domain.bo.AuditInfoBO;
import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.enums.SyncStatusEnum;
import com.search.admin.infra.enums.SyncTypeEnum;
import com.search.admin.infra.enums.YesNoEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import com.search.admin.infra.storage.entity.AuditIndexInfo;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.service.IAuditIndexInfoService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class AuditUpdateLogic {

    @Autowired
    private IndexQueryLogic indexQueryLogic;
    @Autowired
    private SearchAdminClient searchAdminClient;
    @Autowired
    private SyncIndexLogic syncIndexLogic;
    @Autowired
    private IAuditIndexInfoService iAuditIndexInfoService;

    public boolean auditInfo(AuditInfoBO auditInfoBO) {
        IndexSettings index = indexQueryLogic.findIndexByIndexId(auditInfoBO.getIndexSettingsId());

        RestHighLevelClient client = searchAdminClient.elasticsearchClient();

        GetIndexRequest getIndexRequest = new GetIndexRequest(index.getIndexName());
        boolean exists = false;
        try {
             exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("check elasticsearch exist index:{}",index.getIndexName(),e);
            throw new SearchFrameworkException(BusinessExceptionEnum.SYSTEM_ERROR.getCode(), BusinessExceptionEnum.SYSTEM_ERROR.getDesc());
        }
        // 索引存在
        if (exists){
            LambdaUpdateWrapper<AuditIndexInfo> updateWrapper = Wrappers.lambdaUpdate();

            boolean updateFlag = syncIndexLogic.updateEsIndexInfo(index, client, auditInfoBO);
            if (updateFlag){
                updateWrapper.set(AuditIndexInfo::getAuditType,auditInfoBO.getAuditType())
                        .set(AuditIndexInfo::getSyncStatus, SyncStatusEnum.SYNC_SUCCESS.getCode())
                        .eq(AuditIndexInfo::getDeleteFlag, YesNoEnum.YES.getCode())
                        .eq(AuditIndexInfo::getId,auditInfoBO.getId());
                return iAuditIndexInfoService.update(updateWrapper);
            }
            return false;
        }




       return syncIndexLogic.createEsIndexInfo(index,client,auditInfoBO);
    }
}
