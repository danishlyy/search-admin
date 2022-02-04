package com.search.admin.adapter.controller;

import com.search.admin.adapter.convert.AnalyzerConvert;
import com.search.admin.adapter.request.AnalyzerRequestVO;
import com.search.admin.app.dto.AnalyzerDTO;
import com.search.admin.app.service.AnalyzerService;
import com.search.admin.infra.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 分词
 */
@RestController
@Slf4j
public class AnalyzerController {

    @Autowired
    private AnalyzerService analyzerService;

    /**
     * 验证分词效果
     * @param requestVO
     * @return
     */
    @PostMapping(value = "/v1/analyze/text")
    public Result<String> analyzer(@RequestBody @Valid AnalyzerRequestVO requestVO){
        AnalyzerDTO analyzerDTO = AnalyzerConvert.INSTANCE.convertAnalyzerRequestVO2AnalyzerDTO(requestVO);
        return Result.success(analyzerService.analyzer(analyzerDTO));
    }
}
