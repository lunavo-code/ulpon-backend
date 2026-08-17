<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
package ${v.base.packageName}.domain.bo;

import ${v.base.packageName}.domain.${v.base.classNameUpper};
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
<#if v.column.hasBetween>
import java.util.HashMap;
import java.util.Map;
</#if>
import lombok.Data;
import jakarta.validation.constraints.*;
<#list v.base.importList as import>
import ${import};
</#list>

/**
 * ${v.base.functionName}业务对象 ${v.base.tableName}
 *
 * @author ${v.base.author}
 * @date ${v.base.datetime}
 */
@Data
@AutoMapper(target = ${v.base.classNameUpper}.class, reverseConvertGenerate = false)
public class ${v.base.classNameUpper}Bo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

<#list v.column.columns as column>
<#if column.query || (!v.column.table.isSuperColumn(column.javaField) && (column.insert || column.edit))>
    /**
     * ${column.columnComment}
     */
<#if column.insert && column.edit>
<#assign Group = "AddGroup.class, EditGroup.class">
<#elseif column.insert>
<#assign Group = "AddGroup.class">
<#elseif column.edit>
<#assign Group = "EditGroup.class">
</#if>
<#if column.required && !v.column.table.isSuperColumn(column.javaField) && (column.insert || column.edit)>
<#if column.javaType == 'String'>
    @NotBlank(message = "${column.columnComment}不能为空", groups = { ${Group} })
<#else>
    @NotNull(message = "${column.columnComment}不能为空", groups = { ${Group} })
</#if>
</#if>
    private ${column.javaType} ${column.javaField};

</#if>
</#list>
<#if v.column.hasBetween>
    /**
     * 查询参数
     */
    private Map<String, Object> params = new HashMap<>();
</#if>

}
