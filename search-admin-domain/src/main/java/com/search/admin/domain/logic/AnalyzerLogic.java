package com.search.admin.domain.logic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.AnalyzeRequest;
import co.elastic.clients.elasticsearch.indices.AnalyzeResponse;
import com.search.admin.domain.bo.AnalyzerBO;
import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class AnalyzerLogic {

    @Autowired
    private SearchAdminClient searchAdminConfig;

    public String analyzer(AnalyzerBO analyzerBO) {
        ElasticsearchClient client = searchAdminConfig.elasticsearchClient();
        AnalyzeRequest analyzeRequest = new AnalyzeRequest.Builder().analyzer(analyzerBO.getAnalyzer()).text(analyzerBO.getText()).build();
        AnalyzeResponse analyze = null;
        try {
            analyze = client.indices().analyze(analyzeRequest);
        } catch (IOException e) {
            log.error("analyze text failed",e);
        }
        return JacksonUtil.toJsonString(analyze.tokens());
    }
}
