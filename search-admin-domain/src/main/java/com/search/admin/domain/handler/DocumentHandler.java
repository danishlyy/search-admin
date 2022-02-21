package com.search.admin.domain.handler;

import com.search.admin.domain.logic.DocumentLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DocumentHandler {

    @Autowired
    private DocumentLogic documentLogic;

    public boolean reIndexDoc(List<String> sourceIndexName, String targetIndexName) {
        return documentLogic.reIndexDoc(sourceIndexName,targetIndexName);
    }
}
