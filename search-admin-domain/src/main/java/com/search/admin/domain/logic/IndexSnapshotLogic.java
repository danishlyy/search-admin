package com.search.admin.domain.logic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class IndexSnapshotLogic {

    @Autowired
    private SnapshotRepositoryLogic snapshotRepositoryLogic;
    @Autowired
    private IndexQueryLogic indexQueryLogic;
    @Value("${es.index.snapshot.repositoryName:es_snapshot_repo}")
    private String repositoryName;
    @Value("${es.index.snapshot.repositoryLocation:/backup/es_snapshot}")
    private String repositoryLocation;

    public void backUpIndex() {
        List<String> indexNameList = indexQueryLogic.findEffectiveIndex();
        boolean repositoryFlag =  snapshotRepositoryLogic.checkRepositoryExist(repositoryName);
        if (!repositoryFlag){
            snapshotRepositoryLogic.createSnapshotRepository(repositoryName,repositoryLocation);
        }
        snapshotRepositoryLogic.createIndexSnapshot(indexNameList,repositoryName);

    }

    public boolean restoreIndexSnapshot(String snapshotName) {
        return snapshotRepositoryLogic.restoreIndexSnapshot(repositoryName,snapshotName);
    }

    public boolean deleteIndexSnapshot(String snapshotName) {
        return snapshotRepositoryLogic.deleteIndexSnapshot(snapshotName,repositoryName);

    }
}
