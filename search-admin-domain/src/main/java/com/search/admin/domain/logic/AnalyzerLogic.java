package com.search.admin.domain.logic;

import com.search.admin.domain.bo.AnalyzerBO;
import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.enums.AnalyzerTypeEnum;
import com.search.admin.infra.enums.BusinessExceptionEnum;
import com.search.admin.infra.ex.SearchFrameworkException;
import com.search.admin.infra.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.AnalyzeRequest;
import org.elasticsearch.client.indices.AnalyzeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AnalyzerLogic {

    @Autowired
    private SearchAdminClient searchAdminConfig;

    public String analyzer(AnalyzerBO analyzerBO) {
        try {
            RestHighLevelClient client = searchAdminConfig.elasticsearchClient();
            AnalyzeRequest analyzeRequest = null;
            if (AnalyzerTypeEnum.INNER_ANALYZER_TYPE.getCode().equals(analyzerBO.getAnalyzerType())){
                analyzeRequest = AnalyzeRequest.withGlobalAnalyzer(analyzerBO.getAnalyzer(), analyzerBO.getText());
            }
            if (AnalyzerTypeEnum.CUSTOM_ANALYZER_TYPE.getCode().equals(analyzerBO.getAnalyzerType())){
                analyzeRequest = AnalyzeRequest.withField(analyzerBO.getIndexName(),analyzerBO.getAnalyzer(),analyzerBO.getText());
            }

            AnalyzeResponse analyze = client.indices().analyze(analyzeRequest, RequestOptions.DEFAULT);
            log.debug("分词结果：{}",JacksonUtil.toJsonString(analyze));
            List<AnalyzeResponse.AnalyzeToken> tokens = analyze.getTokens();
            return JacksonUtil.toJsonString(tokens);
        }catch (Exception e){
            log.error("analyze text failed",e)     ;
            throw new SearchFrameworkException(BusinessExceptionEnum.ANALYZE_TEXT_FAILED.getCode(), BusinessExceptionEnum.ANALYZE_TEXT_FAILED.getDesc());
        }
    }
}
