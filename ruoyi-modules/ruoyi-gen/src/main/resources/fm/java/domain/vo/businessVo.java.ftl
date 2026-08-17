<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
package ${v.base.packageName}.domain.vo;

<#list v.base.importList as import>
import ${import};
</#list>
import ${v.base.packageName}.domain.${v.base.classNameUpper};
import org.apache.fesod.sheet.annotation.ExcelIgnoreUnannotated;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ${v.base.functionName}视图对象 ${v.base.tableName}
 *
 * @author ${v.base.author}
 * @date ${v.base.datetime}
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ${v.base.classNameUpper}.class)
public class ${v.base.classNameUpper}Vo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

<#list v.column.columns as column>
<#if column.list>
    /**
     * ${column.columnComment}
     */
<#assign parentheseIndex = column.columnComment?index_of("（")>
<#if column.dictType?has_content>
    @ExcelProperty(value = "${column.columnLabel}", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "${column.dictType}")
<#elseif parentheseIndex != -1>
    @ExcelProperty(value = "${column.columnLabel}", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "${column.readConverterExp()}")
<#else>
    @ExcelProperty(value = "${column.columnLabel}")
</#if>
    private ${column.javaType} ${column.javaField};

<#if column.htmlType == "imageUpload">
    /**
     * ${column.columnComment}Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "${column.javaField}")
    private String ${column.javaField}Url;
</#if>
</#if>
</#list>

}
