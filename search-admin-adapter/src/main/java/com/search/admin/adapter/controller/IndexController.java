package com.search.admin.adapter.controller;

import com.search.admin.adapter.base.PageResponseVO;
import com.search.admin.adapter.convert.IndexConvert;
import com.search.admin.adapter.request.IndexAddRequestVO;
import com.search.admin.adapter.request.IndexDeleteRequestVO;
import com.search.admin.adapter.request.IndexSettingAddRequestVO;
import com.search.admin.adapter.request.IndexSettingUpdateRequestVO;
import com.search.admin.adapter.response.IndexResponseVO;
import com.search.admin.adapter.response.IndexSettingResponseVO;
import com.search.admin.app.dto.IndexAddDTO;
import com.search.admin.app.dto.IndexDTO;
import com.search.admin.app.dto.IndexSettingDTO;
import com.search.admin.app.dto.PageDTO;
import com.search.admin.app.service.IndexService;
import com.search.admin.infra.base.Result;
import com.search.admin.infra.base.ValidateList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@Slf4j
public class IndexController {

    @Autowired
    private IndexService indexService;


    /**
     * 新增索引setting相关设置,设置索引名称、分片和副本数，保存在db中
     *
     * @return
     */
    @PostMapping(value = "/v1/create/index/setting")
    public Result<Boolean> createIndexSetting(@RequestBody @Valid IndexSettingAddRequestVO requestVO) {
        IndexSettingDTO indexSettingDTO = IndexConvert.INSTANCE.convertIndexSettingRequestVO2IndexSettingDTO(requestVO);
        return Result.success(indexService.createIndexSetting(indexSettingDTO));
    }

    /**
     * 更新索引setting相关设置，只可以更新副本数，不可以修改分片数
     *
     * @return
     */
    @PostMapping(value = "/v1/update/index/setting")
    public Result<Boolean> updateIndexSetting(@RequestBody @Valid IndexSettingUpdateRequestVO requestVO) {
        IndexSettingDTO indexSettingDTO = IndexConvert.INSTANCE.convertIndexSettingUpdateRequestVO2IndexSettingDTO(requestVO);
        return Result.success(indexService.updateIndexSetting(indexSettingDTO));
    }


    /**
     * 查询单个索引对应的setting信息
     *
     * @return
     */
    @GetMapping(value = "/v1/get/index/setting")
    public Result<IndexSettingResponseVO> getIndexSetting(@RequestParam(value = "indexId") @NotBlank String indexId) {
        IndexSettingDTO indexSetting = indexService.getIndexSetting(indexId);
        return Result.success(IndexConvert.INSTANCE.convertIndexSettingDTO2IndexSettingResponseVO(indexSetting));
    }

    /**
     * 新增索引映射信息
     *
     * @return
     */
    @PostMapping(value = "/v1/create/index/mapping")
    public Result<String> createIndexMapping(@RequestBody @Valid IndexAddRequestVO indexAddRequestVO) {
        IndexAddDTO indexAddDTO = IndexConvert.INSTANCE.convertIndexAddRequestVO2IndexAddDTO(indexAddRequestVO);
        return Result.success(indexService.createIndexMapping(indexAddDTO));
    }

    /**
     * 更新索引映射信息 指允许增加field，不可以修改原有field类型
     *
     * @return
     */
    @PostMapping(value = "/v1/update/index/mapping")
    public Result<String> updateIndexMapping() {
        return Result.success(null);
    }


    /**
     * 删除索引信息
     *
     * @return
     */
    @PostMapping(value = "/v1/delete/indexes")
    public Result<Boolean> deleteIndex(@RequestBody @Validated ValidateList<IndexDeleteRequestVO> request) {
        List<IndexDTO> list = IndexConvert.INSTANCE.convertIndexDeleteRequestVOList2IndexDTOList(request);
        return Result.success(indexService.deleteIndex(list));
    }

    /**
     * 查询所有索引，仅展示索引
     *
     * @return
     */
    @GetMapping(value = "/v1/page/query/indexes")
    public Result<PageResponseVO<IndexResponseVO>> getIndexes() {
        PageDTO<IndexDTO> indexDTOPageDTO = indexService.pageQueryIndexes();
        return Result.success(IndexConvert.INSTANCE.convertPageIndexDTO2PageIndexResponseVO(indexDTOPageDTO));
    }

    /**
     * 查看具体索引的映射关系
     *
     * @return
     */
    @GetMapping(value = "/v1/get/index/mapping")
    public Result<IndexResponseVO> getIndexMapping() {
        return Result.success(null);
    }



}
