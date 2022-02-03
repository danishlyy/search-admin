package com.search.admin.infra.storage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.search.admin.infra.storage.entity.SyncIndexInfoHistory;
import com.search.admin.infra.storage.mapper.SyncIndexInfoHistoryMapper;
import com.search.admin.infra.storage.service.ISyncIndexInfoHistoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 同步es索引信息历史表 服务实现类
 * </p>
 *
 * @author liyongyong
 * @since 2022-02-03
 */
@Service
public class SyncIndexInfoHistoryServiceImpl extends ServiceImpl<SyncIndexInfoHistoryMapper, SyncIndexInfoHistory> implements ISyncIndexInfoHistoryService {

}
