package com.search.admin.domain.handler;

import com.search.admin.domain.logic.IndexSnapshotLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IndexSnapshotHandler {

    @Autowired
    private IndexSnapshotLogic indexSnapshotLogic;

    /**
     * 每天晚上12点跑备份
     * @return
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void backUpIndex(){
        indexSnapshotLogic.backUpIndex();
    }
}
