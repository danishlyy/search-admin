package com.search.admin.domain.logic;

import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.cluster.repositories.get.GetRepositoriesRequest;
import org.elasticsearch.action.admin.cluster.repositories.get.GetRepositoriesResponse;
import org.elasticsearch.action.admin.cluster.repositories.put.PutRepositoryRequest;
import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotRequest;
import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotResponse;
import org.elasticsearch.action.admin.cluster.snapshots.delete.DeleteSnapshotRequest;
import org.elasticsearch.action.admin.cluster.snapshots.restore.RestoreSnapshotRequest;
import org.elasticsearch.action.admin.cluster.snapshots.restore.RestoreSnapshotResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.Cancellable;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.metadata.RepositoryMetadata;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.repositories.fs.FsRepository;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.snapshots.RestoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class SnapshotRepositoryLogic {

    @Autowired
    private SearchAdminClient searchAdminClient;

    public boolean checkRepositoryExist(String repositoryName) {
        GetRepositoriesRequest request = new GetRepositoriesRequest();
        String [] repositories = new String[] {repositoryName};
        request.repositories(repositories);
        request.local(true);
        RestHighLevelClient client = null;
        GetRepositoriesResponse repository = null;
        try {
            client = searchAdminClient.elasticsearchClient();
            repository = client.snapshot().getRepository(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("check snapshot failed,repositoryName:{}",repositoryName);
            throw new SearchFrameworkException(BusinessExceptionEnum.CHECK_SNAPSHOT_REPOSITORY_FAILED.getCode(),
                    BusinessExceptionEnum.CHECK_SNAPSHOT_REPOSITORY_FAILED.getDesc());
        }
        if (ObjectUtils.isEmpty(repository)){
            log.warn("repository is null,repositoryName:{}",repositoryName);
            return false;
        }
        List<RepositoryMetadata> list = repository.repositories();
        if (CollectionUtils.isEmpty(list)){
            log.warn("List<RepositoryMetadata> is empty");
            return false;
        }
        long count = list.stream().filter(repo -> repositoryName.equals(repo.name())).count();
        return count > 0 ? true : false;
    }

    public boolean createSnapshotRepository(String repositoryName, String repositoryLocation) {
        PutRepositoryRequest putRepositoryRequest = new PutRepositoryRequest();
        putRepositoryRequest.name(repositoryName);
        putRepositoryRequest.type(FsRepository.TYPE);
        String locationKey = FsRepository.LOCATION_SETTING.getKey();
        String locationValue = repositoryLocation;
        String compressKey = FsRepository.COMPRESS_SETTING.getKey();
        boolean compressValue = true;
        putRepositoryRequest.settings(Settings.builder()
                .put(locationKey,locationValue)
                .put(compressKey,compressValue)
                .build());
        RestHighLevelClient client = null;
        AcknowledgedResponse repository = null;
        try {
            client = searchAdminClient.elasticsearchClient();
            repository = client.snapshot().createRepository(putRepositoryRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("create snapshot repository failed,repositoryName:{},repositoryLocation:{}",repositoryName,repositoryLocation);
            throw new SearchFrameworkException(BusinessExceptionEnum.CREATE_INDEX_FAILED.getCode(), BusinessExceptionEnum.NUMBER_OF_REPLICAS_ILLEGAL.getDesc());
        }
        return repository.isAcknowledged();
    }

    public void createIndexSnapshot(List<String> indexNameList, String repositoryName) {
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        for (String indexName:indexNameList){
            String snapshotName = "snapshot_"+indexName;
            CreateSnapshotRequest createSnapshotRequest = new CreateSnapshotRequest();
            createSnapshotRequest.indices(indexName);
            createSnapshotRequest.includeGlobalState(false);
            createSnapshotRequest.repository(repositoryName);
            createSnapshotRequest.snapshot(snapshotName);
            createSnapshotRequest.indicesOptions(IndicesOptions.fromOptions(false, false, true, true));
            createSnapshotRequest.partial(false);
            client.snapshot().createAsync(createSnapshotRequest, RequestOptions.DEFAULT, new ActionListener<CreateSnapshotResponse>() {
                @Override
                public void onResponse(CreateSnapshotResponse createSnapshotResponse) {
                    log.info("create index snapshot success");
                    return;
                }

                @Override
                public void onFailure(Exception e) {
                    log.error("create index snapshot failed,indexName:{}",indexName,e);
                    throw new SearchFrameworkException(BusinessExceptionEnum.CREATE_INDEX_SNAPSHOT_FAILED.getCode(),
                            BusinessExceptionEnum.CREATE_INDEX_SNAPSHOT_FAILED.getDesc());
                }
            });
        }
    }

    public boolean restoreIndexSnapshot(String repositoryName, String snapshotName) {
        try {
            RestoreSnapshotRequest request = new RestoreSnapshotRequest(repositoryName, snapshotName);
            RestHighLevelClient client = searchAdminClient.elasticsearchClient();
            client.snapshot().restoreAsync(request, RequestOptions.DEFAULT, new ActionListener<RestoreSnapshotResponse>() {
                @Override
                public void onResponse(RestoreSnapshotResponse restoreSnapshotResponse) {
                    RestStatus status = restoreSnapshotResponse.status();
                    RestoreInfo restoreInfo = restoreSnapshotResponse.getRestoreInfo();
                    log.info("status:{},restoreInfo:{}",status,restoreInfo);
                }

                @Override
                public void onFailure(Exception e) {
                    log.info("restore failed",e);
                }
            });
        }catch (Exception e){
            log.error("restoreIndexSnapshot failed,repositoryName:{},snapshotName:{}",repositoryName,snapshotName,e);
            throw new SearchFrameworkException(BusinessExceptionEnum.RECOVER_INDEX_SNAPSHOT_FAILED.getCode(),
                    BusinessExceptionEnum.RECOVER_INDEX_SNAPSHOT_FAILED.getDesc());
        }
        return true;
    }

    public boolean deleteIndexSnapshot(String snapshotName,String repositoryName) {
        try {
            RestHighLevelClient client = searchAdminClient.elasticsearchClient();
            DeleteSnapshotRequest request = new DeleteSnapshotRequest(repositoryName);
            request.snapshots(snapshotName);
            Cancellable response = client.snapshot().deleteAsync(request, RequestOptions.DEFAULT, new ActionListener<AcknowledgedResponse>() {
                @Override
                public void onResponse(AcknowledgedResponse acknowledgedResponse) {
                    log.info("delete index snapshot result:{}",acknowledgedResponse.isAcknowledged());
                }

                @Override
                public void onFailure(Exception e) {
                    log.error("delete index snapshot failed",e);
                }
            });
        }catch (Exception e){
            log.error("restoreIndexSnapshot failed,repositoryName:{},snapshotName:{}",repositoryName,snapshotName,e);
            throw new SearchFrameworkException(BusinessExceptionEnum.DELETE_INDEX_SNAPSHOT_FAILED.getCode(),
                    BusinessExceptionEnum.DELETE_INDEX_SNAPSHOT_FAILED.getDesc());
        }

        return true;
    }
}
