package com.search.admin.domain.logic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.search.admin.domain.bo.IndexBO;
import com.search.admin.domain.bo.IndexPageConditionBO;
import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.domain.bo.PageBO;
import com.search.admin.domain.convert.Entity2BOConvert;
import com.search.admin.infra.enums.YesNoEnum;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.service.IIndexSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public PageBO<IndexSettingBO> pageQueryIndexes(IndexPageConditionBO pageConditionBO) {
        LambdaQueryWrapper<IndexSettings> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(pageConditionBO.getIndexStatus())){
            queryWrapper.eq(IndexSettings::getDeleteFlag,pageConditionBO.getIndexStatus());
        }
        if (StringUtils.isNotBlank(pageConditionBO.getIndexName())){
            queryWrapper.like(IndexSettings::getIndexName,pageConditionBO.getIndexName());
        }

        Page<IndexSettings> indexPage = new Page<>();
        indexPage.setCurrent(Long.parseLong(pageConditionBO.getPageNumber()));
        indexPage.setSize(Long.parseLong(pageConditionBO.getPageSize()));
        IPage<IndexSettings> result = iIndexSettingsService.page(indexPage, queryWrapper);
        // ????????????
        long current = result.getCurrent();
        // ?????????
        long pages = result.getPages();
        // ????????????
        long total = result.getTotal();
        // ????????????
        List<IndexSettings> records = result.getRecords();
        PageBO<IndexSettingBO> pageResult = new PageBO<>();
        pageResult.setCurrent(String.valueOf(current));
        pageResult.setPages(String.valueOf(pages));
        pageResult.setTotal(String.valueOf(total));
        pageResult.setRecords(Entity2BOConvert.INSTANCE.convertIndexEntityList2IndexBOList(records));
        return pageResult;

    }

    public List<IndexSettingBO> findIndexByIds(List<String> indexIds) {
        List<IndexSettings> list = iIndexSettingsService.listByIds(indexIds);
        return Entity2BOConvert.INSTANCE.convertIndexSettingsList2IndexSettingBOList(list);
    }

    public IndexBO findIndexMappingByIndexId(String indexId) {
        LambdaQueryWrapper<IndexSettings> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.select(IndexSettings::getId)
                .select(IndexSettings::getIndexName)
                .select(IndexSettings::getIndexMapping)
                .eq(IndexSettings::getId,indexId);
        IndexSettings indexSettings = iIndexSettingsService.getById(indexId);
        return Entity2BOConvert.INSTANCE.convertIndexMapping2IndexBO(indexSettings);
    }

    public List<String> findEffectiveIndex() {
        LambdaQueryWrapper<IndexSettings> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.select(IndexSettings::getIndexName);
        queryWrapper.eq(IndexSettings::getDeleteFlag, YesNoEnum.YES.getCode());
        List<IndexSettings> list = iIndexSettingsService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)){
            log.warn(" there is no effective index exist");
            return Collections.emptyList();
        }
        return list.stream().map(IndexSettings::getIndexName).collect(Collectors.toList());
    }
}
