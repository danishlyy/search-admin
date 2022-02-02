package com.search.admin.app.service.impl;

import com.search.admin.app.service.IndexService;
import com.search.admin.app.service.dto.IndexAddDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IndexServiceImpl implements IndexService {
    @Override
    public String createIndexMapping(IndexAddDTO indexAddDTO) {
        return null;
    }
}
