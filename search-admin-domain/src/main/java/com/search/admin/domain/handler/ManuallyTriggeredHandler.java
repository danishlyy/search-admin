package com.search.admin.domain.handler;

import com.search.admin.domain.logic.IndexSnapshotLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ManuallyTriggeredHandler {

    @Autowired
    private IndexSnapshotLogic indexSnapshotLogic;

    public void backUpIndex() {
        indexSnapshotLogic.backUpIndex();
    }

    public boolean restoreIndexSnapshot(String snapshotName) {
        return indexSnapshotLogic.restoreIndexSnapshot(snapshotName);
    }

    public boolean deleteIndexSnapshot(String snapshotName) {
        return indexSnapshotLogic.deleteIndexSnapshot(snapshotName);

    }
}
