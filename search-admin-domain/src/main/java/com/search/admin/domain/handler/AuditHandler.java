package com.search.admin.domain.handler;

import com.search.admin.domain.bo.IndexSettingBO;
import com.search.admin.domain.logic.IndexQueryLogic;
import com.search.admin.infra.config.SearchAdminClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AuditHandler {

    @Autowired
    private IndexQueryLogic indexQueryLogic;

    @Autowired
    private SearchAdminClient searchAdminClient;


    public boolean confirmIndexConfiguration(List<String> indexIds) {
        List<IndexSettingBO> list = indexQueryLogic.findIndexByIds(indexIds);
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        return false;
    }
}
