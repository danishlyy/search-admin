package com.search.admin.app.service.impl;

import com.search.admin.app.convert.DTOConvert;
import com.search.admin.app.dto.ReIndexDTO;
import com.search.admin.app.service.DocumentService;
import com.search.admin.domain.bo.ReindexBO;
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
        ReindexBO reindexBO = DTOConvert.INSTANCE.convertReIndexDTO2ReIndexBO(reIndexDTO);
        return documentHandler.reIndexDoc(reindexBO);
    }
}
