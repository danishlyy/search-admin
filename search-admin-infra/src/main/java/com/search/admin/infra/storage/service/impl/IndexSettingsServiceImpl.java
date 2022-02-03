package com.search.admin.infra.storage.service.impl;

import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.mapper.IndexSettingsMapper;
import com.search.admin.infra.storage.service.IIndexSettingsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 索引信息表 服务实现类
 * </p>
 *
 * @author liyongyong
 * @since 2022-02-03
 */
@Service
public class IndexSettingsServiceImpl extends ServiceImpl<IndexSettingsMapper, IndexSettings> implements IIndexSettingsService {

}
