package com.search.admin.infra.storage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.search.admin.infra.storage.entity.AuditIndexInfo;
import com.search.admin.infra.storage.mapper.AuditIndexInfoMapper;
import com.search.admin.infra.storage.service.IAuditIndexInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 同步es索引信息历史表 服务实现类
 * </p>
 *
 * @author liyongyong
 * @since 2022-02-11
 */
@Service
public class AuditIndexInfoServiceImpl extends ServiceImpl<AuditIndexInfoMapper, AuditIndexInfo> implements IAuditIndexInfoService {

}
