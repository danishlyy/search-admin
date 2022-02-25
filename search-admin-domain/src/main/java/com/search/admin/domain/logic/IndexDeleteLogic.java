package com.search.admin.domain.logic;

import com.search.admin.domain.bo.IndexBO;
import com.search.admin.domain.convert.BO2EntityConvert;
import com.search.admin.infra.enums.YesNoEnum;
import com.search.admin.infra.storage.entity.IndexSettings;
import com.search.admin.infra.storage.service.IIndexSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class IndexDeleteLogic {

    @Autowired
    private IIndexSettingsService iIndexSettingsService;

    public boolean deleteIndex(List<IndexBO> list) {
        List<IndexSettings> indexList = BO2EntityConvert.INSTANCE.convertIndexBOList2IndexSettingList(list);
        indexList.forEach(index->{index.setDeleteFlag(YesNoEnum.NO.getCode());});
        return iIndexSettingsService.updateBatchById(indexList,indexList.size());
    }
}
