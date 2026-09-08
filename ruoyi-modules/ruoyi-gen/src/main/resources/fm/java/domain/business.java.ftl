<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
package ${v.base.packageName}.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#list v.base.importList as import>
import ${import};
</#list>

import java.io.Serial;

/**
 * ${v.base.functionName}对象 ${v.base.tableName}
 *
 * @author ${v.base.author}
 * @date ${v.base.datetime}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("${v.base.tableName}")
public class ${v.base.classNameUpper} extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

<#list v.column.columns as column>
<#if !v.column.table.isSuperColumn(column.javaField)>
    /**
     * ${column.columnComment}
     */
<#if column.javaField=='delFlag'>
    @TableLogic
</#if>
<#if column.javaField=='version'>
    @Version
</#if>
<#if column.pk>
    @TableId(value = "${column.columnName}")
<#elseif column.needTableField>
    @TableField(value = "${column.columnName}")
</#if>
    private ${column.javaType} ${column.javaField};

</#if>
</#list>

}
