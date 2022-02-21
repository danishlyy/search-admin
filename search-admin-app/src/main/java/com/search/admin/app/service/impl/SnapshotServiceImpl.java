package com.search.admin.app.service.impl;

import com.search.admin.app.service.SnapshotService;
import com.search.admin.domain.handler.ManuallyTriggeredHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SnapshotServiceImpl implements SnapshotService {

    @Autowired
    private ManuallyTriggeredHandler manuallyTriggeredHandler;

    @Override
    public String createIndexSnapshot() {
        manuallyTriggeredHandler.backUpIndex();
        return "OK";
    }

    @Override
    public boolean restoreIndexSnapshot(String snapshotName) {
        return manuallyTriggeredHandler.restoreIndexSnapshot(snapshotName);
    }

    @Override
    public boolean deleteIndexSnapshot(String snapshotName) {
        return manuallyTriggeredHandler.deleteIndexSnapshot(snapshotName);
    }
}
