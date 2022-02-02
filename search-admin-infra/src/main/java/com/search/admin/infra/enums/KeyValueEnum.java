package com.search.admin.infra.enums;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public interface KeyValueEnum<CODE> extends Serializable {

    CODE getCode();

    String getDesc();
}
