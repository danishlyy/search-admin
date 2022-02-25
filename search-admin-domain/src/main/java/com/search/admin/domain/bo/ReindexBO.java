package com.search.admin.domain.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReindexBO {

    private List<String> sourceIndexName;

    private String targetIndexName;

    private String id;

    private String modifier;
}
