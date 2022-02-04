package com.search.admin.domain.handler;

import com.search.admin.domain.bo.AnalyzerBO;
import com.search.admin.domain.logic.AnalyzerLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AnalyzerHandler {
    @Autowired
    private AnalyzerLogic analyzerLogic;

    public String analyzer(AnalyzerBO analyzerBO) {
        return analyzerLogic.analyzer(analyzerBO);
    }
}
