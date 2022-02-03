package com.search.admin.infra.enums;

import java.io.Serializable;

public interface KeyValueEnum<CODE> extends Serializable {

    CODE getCode();

    String getDesc();
}
