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

    private String functionName;

    private GenTableColumn pkColumn;

    private List<GenTableColumn> columns;

    private Boolean needAddDateRange;

    private Boolean enableStatus;
    private String statusField;
    private GenTableColumn statusColumn;

    private Boolean enableSort;
    private String sortField;
    private GenTableColumn sortColumn;
}
