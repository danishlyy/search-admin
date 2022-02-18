package com.search.admin.domain.handler;

import com.search.admin.domain.logic.IndexSnapshotLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IndexSnapshotHandler {

    @Autowired
    private IndexSnapshotLogic indexSnapshotLogic;

    public void backUpIndex(){
        indexSnapshotLogic.backUpIndex();
    }
}
