<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
package ${v.base.packageName}.mapper;

import ${v.base.packageName}.domain.${v.base.classNameUpper};
import ${v.base.packageName}.domain.vo.${v.base.classNameUpper}Vo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * ${v.base.functionName}Mapper接口
 *
 * @author ${v.base.author}
 * @date ${v.base.datetime}
 */
public interface ${v.base.classNameUpper}Mapper extends BaseMapperPlus<${v.base.classNameUpper}, ${v.base.classNameUpper}Vo> {

}
