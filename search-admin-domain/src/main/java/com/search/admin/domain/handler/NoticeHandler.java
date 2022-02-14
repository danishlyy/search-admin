package com.search.admin.domain.handler;

import com.search.admin.domain.bo.NoticeBO;
import com.search.admin.domain.logic.NoticeLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NoticeHandler {

    @Autowired
    private NoticeLogic noticeLogic;

    public boolean noticeReview(NoticeBO noticeBO) {
        return noticeLogic.noticeReview(noticeBO);
    }
}
