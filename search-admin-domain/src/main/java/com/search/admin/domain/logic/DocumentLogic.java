package com.search.admin.domain.logic;

import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.ReindexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class DocumentLogic {

    @Autowired
    private SearchAdminClient searchAdminClient;


    public boolean reIndexDoc(List<String> sourceIndexName, String targetIndexName) {

        String[] sourIndexNameArray = sourceIndexName.toArray(new String[sourceIndexName.size()]);
        RestHighLevelClient client = null;searchAdminClient.elasticsearchClient();
        CountRequest countRequest = new CountRequest();
        countRequest.indices(sourIndexNameArray);
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        countRequest.query(queryBuilder);
        CountResponse countResponse = null;
        long count = 0L;
        try {
            client = searchAdminClient.elasticsearchClient();
            countResponse = client.count(countRequest, RequestOptions.DEFAULT);
            count = countResponse.getCount();
            ReindexRequest reindexRequest = new ReindexRequest();
            reindexRequest.setSourceIndices(sourIndexNameArray);
            reindexRequest.setDestIndex(targetIndexName);
            reindexRequest.setDestVersionType(VersionType.EXTERNAL);
            reindexRequest.setDestOpType("index");
            reindexRequest.setConflicts("proceed");
            reindexRequest.setMaxDocs((int) count);

            client.reindexAsync(reindexRequest, RequestOptions.DEFAULT, new ActionListener<BulkByScrollResponse>() {
                @Override
                public void onResponse(BulkByScrollResponse bulkByScrollResponse) {

                }

                @Override
                public void onFailure(Exception e) {
                    log.error("reindex failed,sourceIndexName:{},targetIndexName:{}",sourceIndexName,targetIndexName,e);
                }
            });
        } catch (IOException e) {
            log.error("reindex failed,sourceIndexName:{},targetIndexName:{}",sourceIndexName,targetIndexName,e);
            throw new SearchFrameworkException(BusinessExceptionEnum.REINDEX_FAILED.getCode(),
                    BusinessExceptionEnum.REINDEX_FAILED.getDesc());
        }

        return true;
    }
}
