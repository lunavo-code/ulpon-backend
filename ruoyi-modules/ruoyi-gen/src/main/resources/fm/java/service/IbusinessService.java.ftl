<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
package ${v.base.packageName}.service;

import ${v.base.packageName}.domain.vo.${v.base.classNameUpper}Vo;
import ${v.base.packageName}.domain.bo.${v.base.classNameUpper}Bo;
<#if v.column.table.crud>
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;
</#if>

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ${v.base.functionName}Service接口
 *
 * @author ${v.base.author}
 * @date ${v.base.datetime}
 */
public interface I${v.base.classNameUpper}Service {

    /**
     * 查询${v.base.functionName}
     *
     * @param ${v.column.pkColumn.javaField} 主键
     * @return ${v.base.functionName}
     */
    ${v.base.classNameUpper}Vo queryById(${v.column.pkColumn.javaType} ${v.column.pkColumn.javaField});

    /**
     * 查询${v.base.functionName}列表
     *
     * @param ${v.column.pkColumn.javaField}List 主键列表
     * @return ${v.base.functionName}列表
     */
    List<${v.base.classNameUpper}Vo> queryByIds(Collection<${v.column.pkColumn.javaType}> ${v.column.pkColumn.javaField}List);

    /**
     * 查询核心词管理map
     *
     * @param ${v.column.pkColumn.javaField}List 主键列表
     * @return 核心词管理map
     */
    default Map<${v.column.pkColumn.javaType}, ${v.base.classNameUpper}Vo> queryMapByIds(Collection<${v.column.pkColumn.javaType}> ${v.column.pkColumn.javaField}List){
        return queryByIds(${v.column.pkColumn.javaField}List).stream().collect(Collectors.toMap(${v.base.classNameUpper}Vo::get${v.column.pkColumn.capJavaField}, v -> v));
    }

<#if v.column.table.crud>
    /**
     * 分页查询${v.base.functionName}列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return ${v.base.functionName}分页列表
     */
    PageResult<${v.base.classNameUpper}Vo> queryPageList(${v.base.classNameUpper}Bo bo, PageQuery pageQuery);
</#if>

    /**
     * 查询符合条件的${v.base.functionName}列表
     *
     * @param bo 查询条件
     * @return ${v.base.functionName}列表
     */
    List<${v.base.classNameUpper}Vo> queryList(${v.base.classNameUpper}Bo bo);

<#if v.option.enableUnique>
    /**
     * 校验${v.base.functionName}是否满足组合唯一约束
     *
     * @param bo ${v.base.functionName}
     * @return 是否唯一
     */
    boolean checkUnique(${v.base.classNameUpper}Bo bo);
</#if>

    /**
     * 新增${v.base.functionName}
     *
     * @param bo ${v.base.functionName}
     * @return 是否新增成功
     */
    Boolean insertByBo(${v.base.classNameUpper}Bo bo);

    /**
     * 修改${v.base.functionName}
     *
     * @param bo ${v.base.functionName}
     * @return 是否修改成功
     */
    Boolean updateByBo(${v.base.classNameUpper}Bo bo);

<#if v.option.enableStatus>
    /**
     * 修改${v.base.functionName}状态
     *
     * @param ${v.column.pkColumn.javaField} 主键
     * @param status 状态值
     * @return 是否修改成功
     */
    Boolean updateStatus(${v.column.pkColumn.javaType} ${v.column.pkColumn.javaField}, ${v.option.statusColumn.javaType} status);
</#if>

<#if v.option.enableSort>
    /**
     * 调整${v.base.functionName}排序
     *
     * @param ${v.column.pkColumn.javaField} 主键
     * @param sortValue 排序值
     * @return 是否修改成功
     */
    Boolean updateSort(${v.column.pkColumn.javaType} ${v.column.pkColumn.javaField}, ${v.option.sortColumn.javaType} sortValue);
</#if>

    /**
     * 校验并批量删除${v.base.functionName}信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<${v.column.pkColumn.javaType}> ids, Boolean isValid);

}
