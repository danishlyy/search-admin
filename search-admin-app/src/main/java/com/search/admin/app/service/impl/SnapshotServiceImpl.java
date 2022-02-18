package com.search.admin.app.service.impl;

import com.search.admin.app.service.SnapshotService;
import com.search.admin.domain.handler.IndexSnapshotHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SnapshotServiceImpl implements SnapshotService {

    @Autowired
    private IndexSnapshotHandler indexSnapshotHandler;

    @Override
    public String createIndexSnapshot() {
        indexSnapshotHandler.backUpIndex();
        return "OK";
    }
}
