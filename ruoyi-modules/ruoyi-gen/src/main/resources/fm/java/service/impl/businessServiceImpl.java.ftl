<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
package ${v.base.packageName}.service.impl;

import cn.hutool.core.util.ObjectUtil;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
<#if v.column.table.crud>
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
</#if>
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
<#if v.option.enableUnique>
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
</#if>
import org.dromara.common.mybatis.core.query.QueryBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ${v.base.packageName}.domain.bo.${v.base.classNameUpper}Bo;
import ${v.base.packageName}.domain.vo.${v.base.classNameUpper}Vo;
import ${v.base.packageName}.domain.${v.base.classNameUpper};
import ${v.base.packageName}.mapper.${v.base.classNameUpper}Mapper;
import ${v.base.packageName}.service.I${v.base.classNameUpper}Service;
<#if v.column.table.tree>
import org.dromara.common.core.exception.ServiceException;
</#if>

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * ${v.base.functionName}Service业务层处理
 *
 * @author ${v.base.author}
 * @date ${v.base.datetime}
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ${v.base.classNameUpper}ServiceImpl implements I${v.base.classNameUpper}Service {

    private final ${v.base.classNameUpper}Mapper ${v.base.classNameLower}Mapper;

    /**
     * 查询${v.base.functionName}
     *
     * @param ${v.column.pkColumn.javaField} 主键
     * @return ${v.base.functionName}
     */
    @Override
    public ${v.base.classNameUpper}Vo queryById(${v.column.pkColumn.javaType} ${v.column.pkColumn.javaField}) {
        return ${v.base.classNameLower}Mapper.selectVoById(${v.column.pkColumn.javaField});
    }

<#if v.column.table.crud>
    /**
     * 分页查询${v.base.functionName}列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return ${v.base.functionName}分页列表
     */
    @Override
    public PageResult<${v.base.classNameUpper}Vo> queryPageList(${v.base.classNameUpper}Bo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<${v.base.classNameUpper}> lqw = buildQueryWrapper(bo);
        Page<${v.base.classNameUpper}Vo> result = ${v.base.classNameLower}Mapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }
</#if>

    /**
     * 查询符合条件的${v.base.functionName}列表
     *
     * @param bo 查询条件
     * @return ${v.base.functionName}列表
     */
    @Override
    public List<${v.base.classNameUpper}Vo> queryList(${v.base.classNameUpper}Bo bo) {
        LambdaQueryWrapper<${v.base.classNameUpper}> lqw = buildQueryWrapper(bo);
        return ${v.base.classNameLower}Mapper.selectVoList(lqw);
    }

<#if v.option.enableUnique>
    /**
     * 校验${v.base.functionName}是否满足组合唯一约束
     *
     * @param bo ${v.base.functionName}
     * @return 是否唯一
     */
    @Override
    public boolean checkUnique(${v.base.classNameUpper}Bo bo) {
        boolean hasUniqueValue = true;
<#list v.option.uniqueColumns as column>
<#if column.javaType == 'String'>
        hasUniqueValue = hasUniqueValue && StringUtils.isNotBlank(bo.get${column.capJavaField}());
<#else>
        hasUniqueValue = hasUniqueValue && bo.get${column.capJavaField}() != null;
</#if>
</#list>
        if (!hasUniqueValue) {
            return true;
        }
        LambdaQueryWrapper<${v.base.classNameUpper}> lqw = Wrappers.lambdaQuery();
<#list v.option.uniqueColumns as column>
        lqw.eq(${v.base.classNameUpper}::get${column.capJavaField}, bo.get${column.capJavaField}());
</#list>
        lqw.ne(bo.get${v.column.pkColumn.capJavaField}() != null, ${v.base.classNameUpper}::get${v.column.pkColumn.capJavaField}, bo.get${v.column.pkColumn.capJavaField}());
        return !${v.base.classNameLower}Mapper.exists(lqw);
    }
</#if>

    private LambdaQueryWrapper<${v.base.classNameUpper}> buildQueryWrapper(${v.base.classNameUpper}Bo bo) {
<#if v.column.hasBetween>
        Map<String, Object> params = bo.getParams();
</#if>
        return QueryBuilder.lambda(${v.base.classNameUpper}.class)
<#list v.column.columns as column>
<#if column.query>
<#assign queryType = column.queryType>
<#assign javaType = column.javaType>
<#assign AttrName = column.capJavaField>
<#assign mpMethod = column.queryType?lower_case>
<#if queryType != 'BETWEEN'>
<#if javaType == 'String'>
<#assign condition = 'StringUtils.isNotBlank(bo.get'+AttrName+'())'>
<#if queryType == 'LIKE'>
            .likeIfText(${v.base.classNameUpper}::get${column.capJavaField}, bo.get${column.capJavaField}())
<#elseif queryType == 'EQ'>
            .eqIfText(${v.base.classNameUpper}::get${column.capJavaField}, bo.get${column.capJavaField}())
<#elseif queryType == 'NE'>
            .neIfText(${v.base.classNameUpper}::get${column.capJavaField}, bo.get${column.capJavaField}())
<#else>
            .${mpMethod}(${condition}, ${v.base.classNameUpper}::get${column.capJavaField}, bo.get${column.capJavaField}())
</#if>
<#else>
<#assign condition = 'bo.get'+AttrName+'() != null'>
<#if queryType == 'EQ'>
            .eqIfPresent(${v.base.classNameUpper}::get${column.capJavaField}, bo.get${column.capJavaField}())
<#elseif queryType == 'NE'>
            .neIfPresent(${v.base.classNameUpper}::get${column.capJavaField}, bo.get${column.capJavaField}())
<#elseif queryType == 'GT'>
            .gtIfPresent(${v.base.classNameUpper}::get${column.capJavaField}, bo.get${column.capJavaField}())
<#elseif queryType == 'LT'>
            .ltIfPresent(${v.base.classNameUpper}::get${column.capJavaField}, bo.get${column.capJavaField}())
<#else>
            .${mpMethod}(${condition}, ${v.base.classNameUpper}::get${column.capJavaField}, bo.get${column.capJavaField}())
</#if>
</#if>
<#else>
            .betweenParams(${v.base.classNameUpper}::get${column.capJavaField}, params, "begin${column.capJavaField}", "end${column.capJavaField}")
</#if>
</#if>
</#list>
<#if v.column.table.tree && "" != v.tree.treeAncestorsField>
            .orderByAsc(${v.base.classNameUpper}::get${v.tree.treeAncestorsCap})
</#if>
<#if v.column.table.tree && "" != v.tree.treeParentCode>
            .orderByAsc(${v.base.classNameUpper}::get${v.tree.treeParentCap})
</#if>
<#if v.column.table.tree && "" != v.tree.treeOrderField>
            .orderByAsc(${v.base.classNameUpper}::get${v.tree.treeOrderCap})
<#elseif v.option.enableSort>
            .orderByAsc(${v.base.classNameUpper}::get${v.option.sortColumn.capJavaField})
</#if>
            .orderByAsc(${v.base.classNameUpper}::get${v.column.pkColumn.capJavaField})
            .build();
    }

    /**
     * 新增${v.base.functionName}
     *
     * @param bo ${v.base.functionName}
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(${v.base.classNameUpper}Bo bo) {
        ${v.base.classNameUpper} add = MapstructUtils.convert(bo, ${v.base.classNameUpper}.class);
<#if v.column.table.tree>
        fillTreeMetaBeforeSave(add, false);
</#if>
        validEntityBeforeSave(add);
        boolean flag = ${v.base.classNameLower}Mapper.insert(add) > 0;
        if (flag) {
            bo.set${v.column.pkColumn.capJavaField}(add.get${v.column.pkColumn.capJavaField}());
        }
        return flag;
    }

    /**
     * 修改${v.base.functionName}
     *
     * @param bo ${v.base.functionName}
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(${v.base.classNameUpper}Bo bo) {
        ${v.base.classNameUpper} update = MapstructUtils.convert(bo, ${v.base.classNameUpper}.class);
<#if v.column.table.tree>
        fillTreeMetaBeforeSave(update, true);
</#if>
        validEntityBeforeSave(update);
        return ${v.base.classNameLower}Mapper.updateById(update) > 0;
    }

<#if v.option.enableStatus>
    /**
     * 修改${v.base.functionName}状态
     *
     * @param ${v.column.pkColumn.javaField} 主键
     * @param status 状态值
     * @return 是否修改成功
     */
    @Override
    public Boolean updateStatus(${v.column.pkColumn.javaType} ${v.column.pkColumn.javaField}, ${v.option.statusColumn.javaType} status) {
        return ${v.base.classNameLower}Mapper.lambda()
            .set(${v.base.classNameUpper}::get${v.option.statusColumn.capJavaField}, status)
            .eq(${v.base.classNameUpper}::get${v.column.pkColumn.capJavaField}, ${v.column.pkColumn.javaField})
            .update();
    }
</#if>

<#if v.option.enableSort>
    /**
     * 调整${v.base.functionName}排序
     *
     * @param ${v.column.pkColumn.javaField} 主键
     * @param sortValue 排序值
     * @return 是否修改成功
     */
    @Override
    public Boolean updateSort(${v.column.pkColumn.javaType} ${v.column.pkColumn.javaField}, ${v.option.sortColumn.javaType} sortValue) {
        return ${v.base.classNameLower}Mapper.lambda()
            .set(${v.base.classNameUpper}::get${v.option.sortColumn.capJavaField}, sortValue)
            .eq(${v.base.classNameUpper}::get${v.column.pkColumn.capJavaField}, ${v.column.pkColumn.javaField})
            .update();
    }
</#if>

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(${v.base.classNameUpper} entity) {
        // 可在此扩展通用业务校验
    }

<#if v.column.table.tree>
    private void fillTreeMetaBeforeSave(${v.base.classNameUpper} entity, boolean updateMode) {
<#if "" != v.tree.treeParentCode>
        if (entity.get${v.tree.treeParentCap}() == null) {
            entity.set${v.tree.treeParentCap}(${v.tree.treeRootValueJavaLiteral});
        }
        if (ObjectUtil.equal(entity.get${v.column.pkColumn.capJavaField}(), entity.get${v.tree.treeParentCap}())) {
            throw new ServiceException("${v.base.functionName}父节点不能选择自身");
        }
<#if "" != v.tree.treeAncestorsField>
        ${v.base.classNameUpper} parent = null;
        if (!ObjectUtil.equal(entity.get${v.tree.treeParentCap}(), ${v.tree.treeRootValueJavaLiteral})) {
            parent = ${v.base.classNameLower}Mapper.selectById(entity.get${v.tree.treeParentCap}());
            if (ObjectUtil.isNull(parent)) {
                throw new ServiceException("${v.base.functionName}父节点不存在");
            }
        }
        if (updateMode && entity.get${v.column.pkColumn.capJavaField}() != null && ObjectUtil.isNotNull(parent)
            && containsAncestor(parent.get${v.tree.treeAncestorsCap}(), entity.get${v.column.pkColumn.capJavaField}())) {
            throw new ServiceException("不能选择当前节点或其子节点作为父节点");
        }
        String newAncestors = resolveAncestors(entity.get${v.tree.treeParentCap}(), parent);
        if (updateMode && entity.get${v.column.pkColumn.capJavaField}() != null) {
            ${v.base.classNameUpper} oldEntity = ${v.base.classNameLower}Mapper.selectById(entity.get${v.column.pkColumn.capJavaField}());
            if (ObjectUtil.isNull(oldEntity)) {
                throw new ServiceException("${v.base.functionName}不存在，无法修改");
            }
            String oldAncestors = oldEntity.get${v.tree.treeAncestorsCap}();
            entity.set${v.tree.treeAncestorsCap}(newAncestors);
            if (!StringUtils.equals(oldAncestors, newAncestors)) {
                updateChildrenAncestors(entity.get${v.column.pkColumn.capJavaField}(), newAncestors, oldAncestors);
            }
        } else {
            entity.set${v.tree.treeAncestorsCap}(newAncestors);
        }
</#if>
</#if>
    }
<#if "" != v.tree.treeAncestorsField>

    private String resolveAncestors(${v.tree.treeParentColumn.javaType} parentId, ${v.base.classNameUpper} parent) {
        if (ObjectUtil.equal(parentId, ${v.tree.treeRootValueJavaLiteral})) {
            return "${v.tree.treeRootValue}";
        }
        String parentAncestors = parent.get${v.tree.treeAncestorsCap}();
        if (StringUtils.isBlank(parentAncestors)) {
            return String.valueOf(parentId);
        }
        return parentAncestors + StringUtils.SEPARATOR + parentId;
    }

    private void updateChildrenAncestors(${v.column.pkColumn.javaType} currentId, String newAncestors, String oldAncestors) {
        List<${v.base.classNameUpper}> children = ${v.base.classNameLower}Mapper.lambda()
            .select(${v.base.classNameUpper}::get${v.column.pkColumn.capJavaField}, ${v.base.classNameUpper}::get${v.tree.treeAncestorsCap})
            .findInSet(currentId, ${v.base.classNameUpper}::get${v.tree.treeAncestorsCap})
            .list();
        List<${v.base.classNameUpper}> updateList = new ArrayList<>();
        for (${v.base.classNameUpper} child : children) {
            String ancestors = child.get${v.tree.treeAncestorsCap}();
            if (StringUtils.isBlank(ancestors)) {
                continue;
            }
            ${v.base.classNameUpper} update = new ${v.base.classNameUpper}();
            update.set${v.column.pkColumn.capJavaField}(child.get${v.column.pkColumn.capJavaField}());
            update.set${v.tree.treeAncestorsCap}(StringUtils.replaceOnce(ancestors, oldAncestors, newAncestors));
            updateList.add(update);
        }
        if (!updateList.isEmpty()) {
            ${v.base.classNameLower}Mapper.updateBatchById(updateList);
        }
    }

    private boolean containsAncestor(String ancestors, ${v.column.pkColumn.javaType} nodeId) {
        for (String item : StringUtils.splitList(ancestors)) {
            if (StringUtils.equals(item, String.valueOf(nodeId))) {
                return true;
            }
        }
        return false;
    }
</#if>
</#if>

    /**
     * 校验并批量删除${v.base.functionName}信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<${v.column.pkColumn.javaType}> ids, Boolean isValid) {
        if (isValid) {
            // 可在此扩展删除前业务校验
        }
        return ${v.base.classNameLower}Mapper.deleteByIds(ids) > 0;
    }

}
