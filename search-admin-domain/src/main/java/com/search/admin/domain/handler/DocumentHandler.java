package com.search.admin.domain.handler;

import com.search.admin.domain.bo.ReindexBO;
import com.search.admin.domain.logic.DocumentLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DocumentHandler {

    @Autowired
    private DocumentLogic documentLogic;

    public boolean reIndexDoc(ReindexBO reindexBO) {
        return documentLogic.reIndexDoc(reindexBO);
    }
}
