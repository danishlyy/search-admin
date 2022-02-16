package com.search.admin.adapter.controller;

import com.search.admin.adapter.convert.IndexConvert;
import com.search.admin.adapter.request.NoticeRequestVO;
import com.search.admin.app.dto.NoticeDTO;
import com.search.admin.app.service.NoticeService;
import com.search.admin.infra.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 通知
 */
@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping(value = "/v1/add/notice/info")
    public Result<Boolean> noticeReview(@RequestBody @Valid NoticeRequestVO requestVO){
        NoticeDTO noticeDTO = IndexConvert.INSTANCE.convertNoticeRequestVO2NoticeDTO(requestVO);
        return Result.success(noticeService.noticeReview(noticeDTO));
    }
}
