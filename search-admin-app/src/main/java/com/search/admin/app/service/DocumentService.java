package com.search.admin.app.service;

import com.search.admin.app.dto.ReIndexDTO;

public interface DocumentService {
    boolean reIndexDoc(ReIndexDTO reIndexDTO);
}
