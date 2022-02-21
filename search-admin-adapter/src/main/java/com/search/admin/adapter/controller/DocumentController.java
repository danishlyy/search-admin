package com.search.admin.adapter.controller;

import com.search.admin.adapter.convert.DocumentConvert;
import com.search.admin.adapter.request.ReIndexRequestVO;
import com.search.admin.app.dto.ReIndexDTO;
import com.search.admin.app.service.DocumentService;
import com.search.admin.infra.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 文档操作
 */
@RestController
@Slf4j
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    /**
     * reIndex操作api
     * @param requestVO
     * @return
     */
    @PostMapping(value = "/v1/create/copy/index")
    public Result<Boolean> reIndexDoc(@RequestBody @Valid ReIndexRequestVO requestVO){
        ReIndexDTO reIndexDTO = DocumentConvert.INSTANCE.convertReIndexRequestVO2ReIndexDTO(requestVO);
        return Result.success(documentService.reIndexDoc(reIndexDTO));
    }
}
