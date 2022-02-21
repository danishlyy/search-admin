package com.search.admin.app.service.impl;

import com.search.admin.app.dto.ReIndexDTO;
import com.search.admin.app.service.DocumentService;
import com.search.admin.domain.handler.DocumentHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentHandler documentHandler;
    @Override
    public boolean reIndexDoc(ReIndexDTO reIndexDTO) {
        return documentHandler.reIndexDoc(reIndexDTO.getSourceIndexName(),reIndexDTO.getTargetIndexName());
    }
}
