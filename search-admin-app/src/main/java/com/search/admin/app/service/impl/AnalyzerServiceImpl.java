package com.search.admin.app.service.impl;

import com.search.admin.app.convert.DTOConvert;
import com.search.admin.app.dto.AnalyzerDTO;
import com.search.admin.app.service.AnalyzerService;
import com.search.admin.domain.handler.AnalyzerHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AnalyzerServiceImpl implements AnalyzerService {

    @Autowired
    private AnalyzerHandler analyzerHandler;

    @Override
    public String analyzer(AnalyzerDTO analyzerDTO) {
        return analyzerHandler.analyzer(DTOConvert.INSTANCE.convertAnalyzerDTO2AnalyzerBO(analyzerDTO));
    }
}
