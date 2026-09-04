package org.dromara.gen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dromara.common.core.enums.common.BaseEnum;
import org.dromara.common.core.enums.common.EnumDefinition;
import org.dromara.common.core.enums.common.EnumName;

@AllArgsConstructor
@Getter
@EnumName(name = "模板类型", modelName = "gen")
public enum TemplateCategoryEnum implements EnumDefinition {
    java("java", "java", "java"),
    xml("xml", "xml", "xml"),
    sql("sql", "sql", "sql"),
    react("react", "react", "react"),
    react_tree("react_tree", "react_tree", "react_tree"),
    vue("vue", "vue", "vue"),
    vue_tree("vue_tree", "vue_tree", "vue_tree"),
    ;

    private final String code;
    private final String label;
    private final String desc;
}
