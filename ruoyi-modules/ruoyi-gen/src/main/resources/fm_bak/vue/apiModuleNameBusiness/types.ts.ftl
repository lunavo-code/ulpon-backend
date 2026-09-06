<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
export interface ${v.base.businessNameUpper}VO {
<#list v.column.columns as column>
<#if column.list>
  /**
   * ${column.columnComment}
   */
  ${column.javaField}: ${column.tsType};
<#if column.htmlType == "imageUpload">
  /**
   * ${column.columnComment}Url
   */
  ${column.javaField}Url: string;
</#if>
</#if>
</#list>
<#if v.column.table.tree>
  /**
   * 子对象
   */
  children: ${v.base.businessNameUpper}VO[];
</#if>
}

export interface ${v.base.businessNameUpper}Form extends BaseEntity {
<#list v.column.columns as column>
<#if column.insert || column.edit>
  /**
   * ${column.columnComment}
   */
  ${column.javaField}?: ${column.tsType};
</#if>
</#list>
}

export interface ${v.base.businessNameUpper}Query<#if !v.column.table.tree> extends PageQuery</#if> {
<#list v.column.columns as column>
<#if column.query>
  /**
   * ${column.columnComment}
   */
  ${column.javaField}?: ${column.tsType};
</#if>
</#list>
  /**
   * 日期范围参数
   */
  params?: any;
}
