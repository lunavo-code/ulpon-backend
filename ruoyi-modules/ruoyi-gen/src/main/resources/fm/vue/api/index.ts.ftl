<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
import type { ${v.base.businessNameUpper}Form, ${v.base.businessNameUpper}Query, ${v.base.businessNameUpper}VO } from '@/api/${v.base.moduleName}/${v.base.businessNameLower}/types';
<#if !v.column.table.tree>
import type { PageResult } from '@/api/types';
</#if>
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询${v.base.functionName}列表
 * @param query
 * @returns {*}
 */
export const list${v.base.businessNameUpper} = (query?: ${v.base.businessNameUpper}Query): AxiosPromise<<#if v.column.table.tree>${v.base.businessNameUpper}VO[]<#else>PageResult<${v.base.businessNameUpper}VO></#if>> => {
  return request({
    url: '/${v.base.moduleName}/${v.base.businessNameLower}/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询${v.base.functionName}详细
 * @param ${v.column.pkColumn.javaField}
 */
export const get${v.base.businessNameUpper} = (${v.column.pkColumn.javaField}: string | number): AxiosPromise<${v.base.businessNameUpper}VO> => {
  return request({
    url: '/${v.base.moduleName}/${v.base.businessNameLower}/' + ${v.column.pkColumn.javaField},
    method: 'get'
  });
};

/**
 * 新增${v.base.functionName}
 * @param data
 */
export const add${v.base.businessNameUpper} = (data: ${v.base.businessNameUpper}Form) => {
  return request({
    url: '/${v.base.moduleName}/${v.base.businessNameLower}',
    method: 'post',
    data: data
  });
};

/**
 * 修改${v.base.functionName}
 * @param data
 */
export const update${v.base.businessNameUpper} = (data: ${v.base.businessNameUpper}Form) => {
  return request({
    url: '/${v.base.moduleName}/${v.base.businessNameLower}',
    method: 'put',
    data: data
  });
};

<#if v.option.enableStatus>
/**
 * 修改${v.base.functionName}状态
 * @param ${v.column.pkColumn.javaField}
 * @param status
 */
export const change${v.base.businessNameUpper}Status = (${v.column.pkColumn.javaField}: string | number, status: <#if v.option.statusColumn.javaType == 'Boolean'>boolean<#elseif v.option.statusColumn.javaType == 'String'>string<#else> number</#if>) => {
  return request({
    url: '/${v.base.moduleName}/${v.base.businessNameLower}/changeStatus',
    method: 'put',
    data: {
      ${v.column.pkColumn.javaField},
      ${v.option.statusField}: status
    }
  });
};
</#if>

<#if v.option.enableSort>
/**
 * 调整${v.base.functionName}排序
 * @param ${v.column.pkColumn.javaField}
 * @param sortValue
 */
export const update${v.base.businessNameUpper}Sort = (${v.column.pkColumn.javaField}: string | number, sortValue: <#if v.option.sortColumn.javaType == 'String' || v.option.sortColumn.javaType == 'LocalDateTime'>string<#else> number</#if>) => {
  return request({
    url: '/${v.base.moduleName}/${v.base.businessNameLower}/updateSort',
    method: 'put',
    data: {
      ${v.column.pkColumn.javaField},
      ${v.option.sortField}: sortValue
    }
  });
};
</#if>

/**
 * 删除${v.base.functionName}
 * @param ${v.column.pkColumn.javaField}
 */
export const del${v.base.businessNameUpper} = (${v.column.pkColumn.javaField}: string | number | Array<string | number>) => {
  return request({
    url: '/${v.base.moduleName}/${v.base.businessNameLower}/' + ${v.column.pkColumn.javaField},
    method: 'delete'
  });
};
