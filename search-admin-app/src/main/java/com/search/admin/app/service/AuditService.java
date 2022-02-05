package com.search.admin.app.service;


import java.util.List;

public interface AuditService {
    boolean confirmIndexConfiguration(List<String> indexIds);
}
