package com.search.admin.adapter.controller;

import com.search.admin.adapter.base.PageResponseVO;
import com.search.admin.adapter.convert.AuditConvert;
import com.search.admin.adapter.request.AuditIndexDeleteRequestVO;
import com.search.admin.adapter.request.AuditIndexRequestVO;
import com.search.admin.adapter.request.AuditRequestVO;
import com.search.admin.adapter.response.AuditInfoResponseVO;
import com.search.admin.app.dto.AuditInfoDTO;
import com.search.admin.app.dto.AuditInfoResultDTO;
import com.search.admin.app.dto.PageDTO;
import com.search.admin.app.service.AuditService;
import com.search.admin.infra.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 审核画面
 */
@RestController
@Slf4j
public class AuditController {

    @Autowired
    private AuditService auditService;

    /**
     * 审核功能
     * @param requestVO
     * @return
     */
    @PostMapping(value = "/v1/audit/index")
    public Result<Boolean> auditInfo(@RequestBody AuditIndexRequestVO requestVO, @RequestHeader(value = "userCode") String userCode){
        requestVO.setModifier(userCode);
        return Result.success(auditService.auditInfo(AuditConvert.INSTANCE.convertAuditRequestVO2AuditDTO(requestVO)));
    }

    /**
     * 删除索引审核功能
     * @param requestVO
     * @return
     */
    @PostMapping(value = "/v1/audit/delete/index")
    public Result<Boolean> auditDeleteIndex(@RequestBody @Valid AuditIndexDeleteRequestVO requestVO){
        return Result.success(auditService.auditDeleteIndex(requestVO.getIndexName()));
    }

    /**
     * 审核信息分页查询
     * @param requestVO
     * @return
     */
    @PostMapping(value = "/v1/audit/query")
    public Result<PageResponseVO<AuditInfoResponseVO>> auditInfoPageQuery(@RequestBody AuditRequestVO requestVO){
        AuditInfoDTO auditInfoDTO = AuditConvert.INSTANCE.convertAuditResponseVO2AuditInfoDTO(requestVO);
        PageDTO<AuditInfoResultDTO> pages =  auditService.auditInfoPageQuery(auditInfoDTO);
        PageResponseVO<AuditInfoResponseVO> response = AuditConvert.INSTANCE.convertPageDTOAuditInfoResultDTO2PageResponseVOAuditInfoResponseVO(pages);
        return Result.success(response);
    }


}
