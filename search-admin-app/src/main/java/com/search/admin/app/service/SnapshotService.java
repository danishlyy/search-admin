package com.search.admin.app.service;

public interface SnapshotService {
    String createIndexSnapshot();

    boolean restoreIndexSnapshot(String snapshotName);

    boolean deleteIndexSnapshot(String snapshotName);
}
