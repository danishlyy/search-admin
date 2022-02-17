package com.search.admin.domain.logic;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.search.admin.domain.bo.AuditInfoBO;
import com.search.admin.infra.enums.YesNoEnum;
import com.search.admin.infra.storage.entity.AuditIndexInfo;
import com.search.admin.infra.storage.service.IAuditIndexInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuditUpdateLogic {



    @Autowired
    private IAuditIndexInfoService iAuditIndexInfoService;



    public boolean updateAuditIndexInfo(AuditInfoBO auditInfoBO) {
        LambdaUpdateWrapper<AuditIndexInfo> updateWrapper = Wrappers.lambdaUpdate();
        if (StringUtils.isNotBlank(auditInfoBO.getAuditType())){
            updateWrapper.set(AuditIndexInfo::getAuditType,auditInfoBO.getAuditType());
        }
        if (StringUtils.isNotBlank(auditInfoBO.getModifier())){
            updateWrapper.set(AuditIndexInfo::getModifier,auditInfoBO.getModifier());
        }
        if (StringUtils.isNotBlank(auditInfoBO.getSyncStatus())){
            updateWrapper.set(AuditIndexInfo::getSyncStatus,auditInfoBO.getSyncStatus());
        }
        if (StringUtils.isNotBlank(auditInfoBO.getReindexStatus())){
            updateWrapper.set(AuditIndexInfo::getReindexStatus,auditInfoBO.getReindexStatus());
        }
        updateWrapper.eq(AuditIndexInfo::getId,auditInfoBO.getId())
                .eq(AuditIndexInfo::getDeleteFlag, YesNoEnum.YES.getCode());
        return iAuditIndexInfoService.update(null,updateWrapper);
    }
}
