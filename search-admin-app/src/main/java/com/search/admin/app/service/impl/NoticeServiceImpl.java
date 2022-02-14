package com.search.admin.app.service.impl;

import com.search.admin.app.convert.DTOConvert;
import com.search.admin.app.dto.NoticeDTO;
import com.search.admin.app.service.NoticeService;
import com.search.admin.domain.bo.NoticeBO;
import com.search.admin.domain.handler.NoticeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeHandler noticeHandler;

    @Override
    public boolean noticeReview(NoticeDTO noticeDTO) {
        NoticeBO noticeBO = DTOConvert.INSTANCE.convertNoticeBO2NoticeDTO(noticeDTO);
        return noticeHandler.noticeReview(noticeBO);
    }
}
