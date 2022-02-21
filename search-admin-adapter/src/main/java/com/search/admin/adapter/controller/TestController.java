package com.search.admin.adapter.controller;

import com.search.admin.adapter.request.Customer;
import com.search.admin.adapter.request.IndexAddRequestVO;
import com.search.admin.adapter.request.IndexMappingPropertiesRequestVO;
import com.search.admin.adapter.request.Trade;
import com.search.admin.infra.base.Result;
import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.util.DateTimeUtil;
import com.search.admin.infra.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.cluster.repositories.get.GetRepositoriesRequest;
import org.elasticsearch.action.admin.cluster.repositories.get.GetRepositoriesResponse;
import org.elasticsearch.action.admin.cluster.repositories.put.PutRepositoryRequest;
import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotRequest;
import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotResponse;
import org.elasticsearch.action.admin.cluster.snapshots.get.GetSnapshotsRequest;
import org.elasticsearch.action.admin.cluster.snapshots.get.GetSnapshotsResponse;
import org.elasticsearch.action.admin.cluster.snapshots.status.*;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.client.indices.GetMappingsResponse;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.cluster.metadata.RepositoryMetadata;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.repositories.fs.FsRepository;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.snapshots.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private SearchAdminClient searchAdminClient;

    /**
     * simple field
     * @return
     */
    @PostMapping(value = "/v1/test/create/mapping")
    public Result<String> testPutMapping(){
        List<IndexAddRequestVO> list = new ArrayList<>();
        IndexAddRequestVO vo = new IndexAddRequestVO();
        List<IndexMappingPropertiesRequestVO> fields = new ArrayList<>();
        IndexMappingPropertiesRequestVO f1 = new IndexMappingPropertiesRequestVO();
        f1.setFieldName("productCode");
        f1.setFieldType("text");
        f1.setAnalyzeFlag("0");
        f1.setAnalyzeType("standard");
        fields.add(f1);
        IndexMappingPropertiesRequestVO f2 = new IndexMappingPropertiesRequestVO();
        f2.setFieldName("productName");
        f2.setFieldType("text");
        f2.setAnalyzeFlag("0");
//        f2.setAnalyzeType("standard");
        fields.add(f2);
        vo.setFields(fields);
        list.add(vo);
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("product_info");
        createIndexRequest.settings(Settings.builder()
                .put("index.number_of_shards","1")
                .put("index.number_of_replicas","1").build());
        List<IndexMappingPropertiesRequestVO> fields1 = list.get(0).getFields();
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                builder.startObject("properties");
                {
                    for (IndexMappingPropertiesRequestVO item:fields1){
                            builder.startObject(item.getFieldName());
                            {
                                builder.field("type", item.getFieldType());
                                if (StringUtils.isNotBlank(item.getAnalyzeType())){
                                    builder.field("analyzer",item.getAnalyzeType());
                                }
                            }
                            builder.endObject();

                    }
                }
                builder.endObject();
            }
            builder.endObject();
            createIndexRequest.mapping(builder);
            client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(null);
    }

    @PostMapping(value = "/v1/create/object/mapping")
    public Result<String> createObjectMapping(){
        Trade trade = new Trade();
        Customer customer = new Customer();
        trade.setCustomer(customer);
        trade.setOrderNo("1234");
        trade.setOrderDate("20220101");
        customer.setUserName("Mike");
        customer.setPhone("1234");
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("trade_info");
        createIndexRequest.settings(Settings.builder()
                .put("index.number_of_shards","1")
                .put("index.number_of_replicas","1").build());
        try {
            Map<String,Trade> m = new HashMap<>();
            m.put("properties",trade);
            createIndexRequest.mapping(JacksonUtil.toJsonString(m), XContentType.JSON);
            CreateIndexResponse indexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.success(null);
    }

    @GetMapping(value = "/v1/get/index/mappings")
    public Result<String> getIndexMapping() throws IOException {
        GetMappingsRequest getMappingsRequest = new GetMappingsRequest();
        getMappingsRequest.indices("twitter");
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        GetMappingsResponse mapping = client.indices().getMapping(getMappingsRequest, RequestOptions.DEFAULT);
        Map<String, MappingMetadata> mappings = mapping.mappings();
//        {"twitter":{"sourceAsMap":{"properties":{"productCode":{"type":"keyword"},"email":{"type":"keyword"}}}}}
        log.info(JacksonUtil.toJsonString(mappings));
        Set<Map.Entry<String, MappingMetadata>> entries = mappings.entrySet();
        for (Map.Entry<String, MappingMetadata> m:entries){
//            key:twitter,type:{},source:{}
            log.info("key:{},type:{},source:{}",m.getKey());
            Map<String, Object> sourceAsMap = m.getValue().getSourceAsMap();
            for (Map.Entry<String, Object> m1:sourceAsMap.entrySet()){
//                key:properties,value:{productCode={type=keyword}, email={type=keyword}}
                log.info("key:{},value:{}",m1.getKey(),m1.getValue());
                String s = JacksonUtil.toJsonString(m1.getValue());
                log.info("json结果：{}",s);
            }
        }
        return Result.success(null);
    }

    @GetMapping(value = "/v1/write/txt")
    public Result<String> writeFile(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("write text");
        String file = "file_channel.txt";
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<100000000;i++){
            builder.append("隋明铮");
        }
        try (RandomAccessFile writer = new RandomAccessFile(file, "rw");
             FileChannel channel = writer.getChannel()){
             ByteBuffer buff = ByteBuffer.wrap(builder.toString().getBytes(StandardCharsets.UTF_8));

            channel.write(buff);


        } catch (Exception e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        log.info("result:{}",stopWatch.prettyPrint());
        return Result.success(null);

    }


    @GetMapping(value = "/v1/create/snapshot/repository")
    public Result<String> createSnapShotRepository(){
        PutRepositoryRequest putRepositoryRequest = new PutRepositoryRequest();
        putRepositoryRequest.name("snapshot_es_back_up");
        putRepositoryRequest.type(FsRepository.TYPE);
        String locationKey = FsRepository.LOCATION_SETTING.getKey();
        String locationValue = "/backup/es_backup";
        String compressKey = FsRepository.COMPRESS_SETTING.getKey();
        boolean compressValue = true;
        putRepositoryRequest.settings(Settings.builder()
                        .put(locationKey,locationValue)
                        .put(compressKey,compressValue)
                .build());
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        client.snapshot().createRepositoryAsync(putRepositoryRequest, RequestOptions.DEFAULT, new ActionListener<AcknowledgedResponse>() {
            @Override
            public void onResponse(AcknowledgedResponse acknowledgedResponse) {
                log.info("create snapshot repository success:{}",acknowledgedResponse.isAcknowledged());
            }

            @Override
            public void onFailure(Exception e) {
                log.error("create snapshot repository failed",e);
            }
        });
        return Result.success(null);
    }

    @GetMapping(value = "/v1/get/snapshot/repositories")
    public Result<String> getSnapShotRepositories(){
        GetRepositoriesRequest request = new GetRepositoriesRequest();
        String repositoryName = "snapshot_es_back_up";
        String [] repositories = new String[] {repositoryName};
        request.repositories(repositories);
        request.local(true);
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        client.snapshot().getRepositoryAsync(request, RequestOptions.DEFAULT, new ActionListener<GetRepositoriesResponse>() {
            @Override
            public void onResponse(GetRepositoriesResponse getRepositoriesResponse) {
                List<RepositoryMetadata> list = getRepositoriesResponse.repositories();
                for (RepositoryMetadata rp:list){
                    String name = rp.name();
                    log.info("name:{}",name);
                }
            }

            @Override
            public void onFailure(Exception e) {
                log.error("get snapshot repositories failed",e);
            }
        });
        return Result.success(null);
    }

    /**
     * 如果索引的snapshotName已经存在，则再次会报错
     * @return
     */
    @GetMapping(value = "/v1/create/index/snapshots")
    public Result<String> createIndexSnapshot(){
        String repositoryName = "snapshot_es_back_up";
        String indexName = "march_log";
        String snapshotName = "snapshot_"+indexName+DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        CreateSnapshotRequest createSnapshotRequest = new CreateSnapshotRequest();
        try {
            createSnapshotRequest.indices(indexName);
            createSnapshotRequest.includeGlobalState(false);
            createSnapshotRequest.repository(repositoryName);
            createSnapshotRequest.snapshot(snapshotName);
            createSnapshotRequest.indicesOptions(IndicesOptions.fromOptions(false, false, true, true));
            createSnapshotRequest.partial(false);
            CreateSnapshotResponse createSnapshotResponse = client.snapshot().create(createSnapshotRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {

            log.error("create snapshot failed",e);
        }
        return Result.success(null);
    }

    /**
     * snapshotId:january_log/OCXvOeM1RN-90W9vJPJKiA
     * indices:[january_log]
     * includeGlobalState:false
     * dataStreams:[]
     * failedShards:0
     * reason:null
     * successfulShards:1
     * startTime:1645164158603
     * endTime:1645164158603
     * SnapshotState:1
     * status:200
     * totalShards:1
     * @return
     */
    @GetMapping(value = "/v1/get/index/snapshot")
    public Result<String> getIndexSnapshot(){
        String repositoryName = "snapshot_es_back_up";
        String indexName = "february_log";
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        GetSnapshotsRequest request = new GetSnapshotsRequest();
        try {
            request.repository(repositoryName);
            request.snapshots(new String[]{indexName});
            request.verbose(true);
            request.ignoreUnavailable(false);

            GetSnapshotsResponse getSnapshotsResponse = client.snapshot().get(request, RequestOptions.DEFAULT);
            List<SnapshotInfo> snapshots = getSnapshotsResponse.getSnapshots();
            for (SnapshotInfo snapshotInfo:snapshots){
                SnapshotInfo basic = snapshotInfo.basic();
                SnapshotId snapshotId = snapshotInfo.snapshotId();
                log.info("snapshotId:{}",snapshotId);
                List<String> indices = snapshotInfo.indices();
                log.info("indices:{}",indices);
                Boolean aBoolean = snapshotInfo.includeGlobalState();
                log.info("includeGlobalState:{}",aBoolean);
                List<String> strings = snapshotInfo.dataStreams();
                log.info("dataStreams:{}",strings);
                int i = snapshotInfo.failedShards();
                log.info("failedShards:{}",i);
                String reason = snapshotInfo.reason();
                log.info("reason:{}",reason);
                List<SnapshotShardFailure> snapshotShardFailures = snapshotInfo.shardFailures();
                snapshotShardFailures.forEach(p->{
                    log.info("nodeId:{},shardId:{}",p.nodeId(),p.shardId());
                });
                int successfulShards = snapshotInfo.successfulShards();
                log.info("successfulShards:{}",successfulShards);
                long startTime = snapshotInfo.startTime();
                log.info("startTime:{}",startTime);
                long endTime = snapshotInfo.endTime();
                log.info("endTime:{}",endTime);
                SnapshotState state = snapshotInfo.state();
                log.info("SnapshotState:{}",state.value());
                RestStatus status = snapshotInfo.status();
                log.info("status:{}",status.getStatus());
                int totalShards = snapshotInfo.totalShards();
                log.info("totalShards:{}",totalShards);
                Map<String, Object> stringObjectMap = snapshotInfo.userMetadata();
//                stringObjectMap.forEach((k,v)->{
//                    log.info("k:{},v:{}",k,v);
//                });
            }

        } catch (IOException e) {

            log.error("create snapshot failed",e);
        }
        return Result.success(null);
    }

    /**
     * snapshotId：january_log/OCXvOeM1RN-90W9vJPJKiA
     * repository：snapshot_es_back_up
     * key:january_log
     * index:january_log
     * TotalFileCount：7
     * IncrementalFileCount()：7
     * IncrementalSize()：8384
     * StartTime：1645164158603
     * ProcessedSize：0
     * ProcessedFileCount：0
     * Time：0
     * TotalSize：8384
     * DoneShards：1
     * FailedShards：0
     * StartedShards：0
     * TotalShards：1
     * FinalizingShards：0
     * InitializingShards：0
     * @return
     */
    @GetMapping(value = "/v1/get/repository/snapshot/status")
    public Result<String> getRepositorySnapshotStatus(){
        String repositoryName = "snapshot_es_back_up";
        String indexName = "february_log";
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        SnapshotsStatusRequest request = new SnapshotsStatusRequest();
        try {
            request.repository(repositoryName);
            request.snapshots(new String[]{indexName});
            request.ignoreUnavailable(true);
            SnapshotsStatusResponse statusResponse = client.snapshot().status(request, RequestOptions.DEFAULT);
            List<SnapshotStatus> status = statusResponse.getSnapshots();
            for (SnapshotStatus s:status){
                Snapshot snapshot = s.getSnapshot();
                SnapshotId snapshotId = snapshot.getSnapshotId();
                log.info("snapshotId：{}",snapshotId);
                String repository = snapshot.getRepository();
                log.info("repository：{}",repository);
                Map<String, SnapshotIndexStatus> indices = s.getIndices();
                indices.forEach((k,v)->{
                    log.info("key:{}",k);
                    String index = v.getIndex();
                    log.info("index:{}",index);
                    SnapshotStats stats = v.getStats();
                    log.info("TotalFileCount：{}",stats.getTotalFileCount());
                    log.info("IncrementalFileCount()：{}",stats.getIncrementalFileCount());
                    log.info("IncrementalSize()：{}",stats.getIncrementalSize());
                    log.info("StartTime：{}",stats.getStartTime());
                    log.info("ProcessedSize：{}",stats.getProcessedSize());
                    log.info("ProcessedFileCount：{}",stats.getProcessedFileCount());
                    log.info("Time：{}",stats.getTime());
                    log.info("TotalSize：{}",stats.getTotalSize());
                    Map<Integer, SnapshotIndexShardStatus> shards = v.getShards();
                    SnapshotShardsStats shardsStats = v.getShardsStats();
                    log.info("DoneShards：{}",shardsStats.getDoneShards());
                    log.info("FailedShards：{}",shardsStats.getFailedShards());
                    log.info("StartedShards：{}",shardsStats.getStartedShards());
                    log.info("TotalShards：{}",shardsStats.getTotalShards());
                    log.info("FinalizingShards：{}",shardsStats.getFinalizingShards());
                    log.info("InitializingShards：{}",shardsStats.getInitializingShards());
                });
            }


        } catch (IOException e) {

            log.error("create snapshot failed",e);
        }
        return Result.success(null);
    }





//    @Scheduled(cron = "0/2 * * * * ? ")
    public Result<String> testSchedule(){
        log.info("testSchedule,time:{}", DateTimeUtil.formatLocalDateTimeNow2String());
        return Result.success(null);
    }
}
