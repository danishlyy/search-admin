package com.search.admin.domain.logic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.domain.bo.PageBO;
import com.search.admin.domain.convert.Entity2BOConvert;
import com.search.admin.infra.enums.YesNoEnum;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.service.IIndexSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class IndexQueryLogic {

    @Autowired
    private IIndexSettingsService iIndexSettingsService;

    public boolean findIndexByIndexName(String indexName) {
        LambdaQueryWrapper<IndexSettings> query = Wrappers.lambdaQuery();
        query.eq(IndexSettings::getIndexName, indexName);
        long count = iIndexSettingsService.count(query);
        return count > 0 ? true : false;
    }

    public IndexSettings findIndexByIndexId(String indexId) {
        return iIndexSettingsService.getById(indexId);
    }

    public PageBO<IndexSettingBO> pageQueryIndexes() {
        LambdaQueryWrapper<IndexSettings> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(IndexSettings::getDeleteFlag, YesNoEnum.YES.getCode());
        Page<IndexSettings> indexPage = new Page<>();
        IPage<IndexSettings> result = iIndexSettingsService.page(indexPage, queryWrapper);
        // 当前页数
        long current = result.getCurrent();
        // 总页数
        long pages = result.getPages();
        // 总记录数
        long total = result.getTotal();
        // 具体记录
        List<IndexSettings> records = result.getRecords();
        PageBO<IndexSettingBO> pageResult = new PageBO<>();
        pageResult.setCurrent(String.valueOf(current));
        pageResult.setPages(String.valueOf(pages));
        pageResult.setTotal(String.valueOf(total));
        pageResult.setRecords(Entity2BOConvert.INSTANCE.convertIndexEntityList2IndexBOList(records));
        return pageResult;

    }
}
