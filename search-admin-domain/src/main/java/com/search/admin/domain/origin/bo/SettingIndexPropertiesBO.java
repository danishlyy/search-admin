package com.search.admin.domain.origin.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SettingIndexPropertiesBO {

    private String number_of_shards;
    private String number_of_replicas;
}
