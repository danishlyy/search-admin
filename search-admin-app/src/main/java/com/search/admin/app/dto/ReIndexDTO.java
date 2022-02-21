package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class ReIndexDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2349033262255689519L;

    private List<String> sourceIndexName;

    private String targetIndexName;
}
