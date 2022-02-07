package com.search.admin.adapter.controller;

import com.search.admin.adapter.convert.DictionaryConvert;
import com.search.admin.adapter.request.DictionaryAddRequestVO;
import com.search.admin.adapter.request.DictionaryDeleteRequestVO;
import com.search.admin.adapter.request.DictionaryUpdateRequestVO;
import com.search.admin.adapter.response.DictionaryResponseVO;
import com.search.admin.app.service.DictionaryService;
import com.search.admin.infra.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;


    /**
     * 新增字典
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/v1/batch/insert/dictionaries")
    public Result<Boolean> batchInsertDictionaries(@RequestBody @Valid List<DictionaryAddRequestVO> request) {
        return Result.success(dictionaryService.batchInsertDictionaries(
                DictionaryConvert.INSTANCE.convertDictionaryAddRequestVOList2DictionaryDTOList(request)));
    }


    /**
     * 批量更新字典
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/v1/batch/update/dictionaries")
    public Result<Boolean> batchUpdateDictionaries(@RequestBody @Valid List<DictionaryUpdateRequestVO> request) {
        return Result.success(dictionaryService.batchUpdateDictionaries(
                DictionaryConvert.INSTANCE.convertDictionaryUpdateRequestVOList2DictionaryDTOList(request)));
    }

    /**
     * 批量删除字典
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/v1/batch/delete/dictionaries")
    public Result<Boolean> batchDeleteDictionaries(@RequestBody @Valid List<DictionaryDeleteRequestVO> request) {
        return Result.success(dictionaryService.batchDeleteDictionaries(
                DictionaryConvert.INSTANCE.convertDictionaryDeleteRequestVOList2DictionaryDTOList(request)));
    }

    /**
     * 分页查询字典列表
     *  {@link  com.search.admin.infra.enums.DictionaryTypeEnum}
     * @return
     */
    @PostMapping(value = "/v1/query/dictionary")
    public Result<List<DictionaryResponseVO>> queryDictionary(@RequestParam(value = "dictionaryType") @NotNull String dictionaryType) {
        return Result.success(DictionaryConvert.INSTANCE.convertDictionaryDTOList2DictionaryResponseVOList(dictionaryService.queryDictionary(dictionaryType)));
    }
}
