package org.dromara.gen.domain;

import lombok.Data;

import java.util.List;

@Data
public class GenVariable {
    private String moduleName;
    private String classNameUpper;
    private String classNameLower;
    private String businessNameUpper;
    private String businessNameLower;

    private List<GenTableColumn> columns;
}
