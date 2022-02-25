package com.search.admin.domain.logic;

import com.search.admin.domain.bo.AuditInfoBO;
import com.search.admin.domain.bo.ReindexBO;
import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.enums.YesOrNoEnum;
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
    @Autowired
    private AuditUpdateLogic auditUpdateLogic;


    public boolean reIndexDoc(ReindexBO reindexBO) {

        List<String> sourceIndexName = reindexBO.getSourceIndexName();
        String id = reindexBO.getId();
        String targetIndexName = reindexBO.getTargetIndexName();

        String[] sourIndexNameArray = sourceIndexName.toArray(new String[sourceIndexName.size()]);
        RestHighLevelClient client = null;
        CountRequest countRequest = new CountRequest();
        countRequest.indices(sourIndexNameArray);
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        countRequest.query(queryBuilder);
        CountResponse countResponse = null;
        AuditInfoBO auditInfoBO = new AuditInfoBO();
        auditInfoBO.setId(id);
        auditInfoBO.setModifier(reindexBO.getModifier());
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
                    auditInfoBO.setReindexStatus(YesOrNoEnum.TYPE_YES.getCode());
                    auditUpdateLogic.updateAuditIndexInfo(auditInfoBO);
                }

                @Override
                public void onFailure(Exception e) {
                    log.error("reindex failed,sourceIndexName:{},targetIndexName:{}",sourceIndexName,targetIndexName,e);
                    auditInfoBO.setReindexStatus(YesOrNoEnum.TYPE_NO.getCode());
                    auditUpdateLogic.updateAuditIndexInfo(auditInfoBO);
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
