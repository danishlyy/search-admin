package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class AuditDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6098782158300521506L;

    private String auditId;

    private String indexSettingsId;

    private String syncType;
}
