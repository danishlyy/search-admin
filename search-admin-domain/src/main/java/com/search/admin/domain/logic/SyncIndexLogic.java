package com.search.admin.domain.logic;

import com.search.admin.domain.bo.AuditInfoBO;
import com.search.admin.infra.enums.SyncTypeEnum;
import com.search.admin.infra.storage.entity.IndexSettings;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SyncIndexLogic {
    /**
     * 更新ES mapping 和副本数
     * @param index
     * @param client
     * @param auditInfoBO
     * @return
     */
    public boolean updateEsIndexInfo(IndexSettings index,RestHighLevelClient client, AuditInfoBO auditInfoBO) {
        if (SyncTypeEnum.SYNC_ALL.equals(auditInfoBO.getSyncType())){

        }
        if (SyncTypeEnum.SYNC_SETTING.equals(auditInfoBO.getSyncType())){

        }
        if (SyncTypeEnum.SYNC_MAPPING.equals(auditInfoBO.getSyncType())){

        }
        return false;
    }

    /**
     * 创建ES索引信息
     * @param index
     * @param client
     * @param auditInfoBO
     * @return
     */
    public boolean createEsIndexInfo(IndexSettings index, RestHighLevelClient client, AuditInfoBO auditInfoBO) {
        if (SyncTypeEnum.SYNC_ALL.equals(auditInfoBO.getSyncType())){

        }
        if (SyncTypeEnum.SYNC_SETTING.equals(auditInfoBO.getSyncType())){

        }
        if (SyncTypeEnum.SYNC_MAPPING.equals(auditInfoBO.getSyncType())){

        }

        return false;
    }
}
