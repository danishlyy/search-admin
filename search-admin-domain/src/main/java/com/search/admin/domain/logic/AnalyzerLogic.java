package com.search.admin.domain.logic;

import com.search.admin.domain.bo.AnalyzerBO;
import com.search.admin.infra.config.SearchAdminClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AnalyzerLogic {

    @Autowired
    private SearchAdminClient searchAdminConfig;

    public String analyzer(AnalyzerBO analyzerBO) {
        return null;
    }
}
