package com.search.admin.adapter.controller;

import com.search.admin.adapter.convert.IndexConvert;
import com.search.admin.adapter.request.IndexAddRequestVO;
import com.search.admin.adapter.request.IndexSettingRequestVO;
import com.search.admin.adapter.response.IndexResponseVO;
import com.search.admin.adapter.response.IndexSettingResponseVO;
import com.search.admin.app.service.IndexService;
import com.search.admin.app.service.dto.IndexAddDTO;
import com.search.admin.app.service.dto.IndexSettingDTO;
import com.search.admin.infra.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class IndexController {

    @Autowired
    private IndexService indexService;


    /**
     * 新增索引setting相关设置
     * @return
     */
    @PostMapping(value = "/v1/create/index/setting")
    public Result<Boolean> createIndexSetting(@RequestBody IndexSettingRequestVO requestVO){
        IndexSettingDTO indexSettingDTO = IndexConvert.INSTANCE.convertIndexSettingRequestVO2IndexSettingDTO(requestVO);
        return Result.success(indexService.createIndexSetting(indexSettingDTO));
    }

    /**
     * 更新索引setting相关设置
     * @return
     */
    @PostMapping(value = "/v1/update/index/setting")
    public Result<String> updateIndexSetting(){
        return Result.success(null);
    }


    /**
     * 查询单个索引对应的setting信息
     * @return
     */
    @GetMapping(value = "/v1/get/index/setting")
    public Result<IndexSettingResponseVO> getIndexSetting(){
        return Result.success(null);
    }

    /**
     * 新增索引映射信息
     * @return
     */
    @PostMapping(value = "/v1/create/index/mapping")
    public Result<String> createIndexMapping(@RequestBody @Valid IndexAddRequestVO indexAddRequestVO){
        IndexAddDTO indexAddDTO = IndexConvert.INSTANCE.convertIndexAddRequestVO2IndexAddDTO(indexAddRequestVO);
        return Result.success(indexService.createIndexMapping(indexAddDTO));
    }

    /**
     * 更新索引映射信息
     * @return
     */
    @PostMapping(value = "/v1/update/index/mapping")
    public Result<String> updateIndexMapping(){
        return Result.success(null);
    }


    /**
     * 删除索引信息
     * @return
     */
    @PostMapping(value = "/v1/delete/index")
    public Result<String> deleteIndex(){
        return Result.success(null);
    }

    /**
     * 查询所有索引，仅展示索引
     * @return
     */
    @GetMapping(value = "/v1/get/indexes")
    public Result<List<IndexResponseVO>> getIndexes(){
       return Result.success(null);
    }

    /**
     * 查看具体索引的映射关系
     * @return
     */
    @GetMapping(value = "/v1/get/index/mapping")
    public Result<IndexResponseVO> getIndexMapping(){
        return Result.success(null);
    }

}
