<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
package ${v.base.packageName}.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
<#if v.option.enableExport>
import jakarta.servlet.http.HttpServletResponse;
</#if>
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.redis.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
<#if v.option.enableExport>
import org.dromara.common.excel.utils.ExcelBuilder;
</#if>
import ${v.base.packageName}.domain.vo.${v.base.classNameUpper}Vo;
import ${v.base.packageName}.domain.bo.${v.base.classNameUpper}Bo;
import ${v.base.packageName}.service.I${v.base.classNameUpper}Service;
<#if v.column.table.crud>
import org.dromara.common.core.domain.PageResult;
<#elseif v.column.table.tree>
</#if>

/**
 * ${v.base.functionName}
 *
 * @author ${v.base.author}
 * @date ${v.base.datetime}
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/${v.base.moduleName}/${v.base.businessNameLower}")
public class ${v.base.classNameUpper}Controller extends BaseController {

    private final I${v.base.classNameUpper}Service ${v.base.classNameLower}Service;

    /**
     * 查询${v.base.functionName}列表
     */
    @SaCheckPermission("${v.base.permissionPrefix}:list")
    @GetMapping("/list")
<#if v.column.table.crud>
    public R<PageResult<${v.base.classNameUpper}Vo>> list(${v.base.classNameUpper}Bo bo, PageQuery pageQuery) {
        return R.ok(${v.base.classNameLower}Service.queryPageList(bo, pageQuery));
    }
<#elseif v.column.table.tree>
    public R<List<${v.base.classNameUpper}Vo>> list(${v.base.classNameUpper}Bo bo) {
        List<${v.base.classNameUpper}Vo> list = ${v.base.classNameLower}Service.queryList(bo);
        return R.ok(list);
    }
</#if>

<#if v.option.enableExport>
    /**
     * 导出${v.base.functionName}列表
     */
    @SaCheckPermission("${v.base.permissionPrefix}:export")
    @Log(title = "${v.base.functionName}", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(${v.base.classNameUpper}Bo bo, HttpServletResponse response) {
        List<${v.base.classNameUpper}Vo> list = ${v.base.classNameLower}Service.queryList(bo);
        ExcelBuilder.of(list, ${v.base.classNameUpper}Vo.class).sheetName("${v.base.functionName}").toResponse(response);
    }
</#if>

    /**
     * 获取${v.base.functionName}详细信息
     *
     * @param ${v.column.pkColumn.javaField} 主键
     */
    @SaCheckPermission("${v.base.permissionPrefix}:query")
    @GetMapping("/{${v.column.pkColumn.javaField}}")
    public R<${v.base.classNameUpper}Vo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable ${v.column.pkColumn.javaType} ${v.column.pkColumn.javaField}) {
        return R.ok(${v.base.classNameLower}Service.queryById(${v.column.pkColumn.javaField}));
    }

    /**
     * 新增${v.base.functionName}
     */
    @SaCheckPermission("${v.base.permissionPrefix}:add")
    @Log(title = "${v.base.functionName}", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ${v.base.classNameUpper}Bo bo) {
<#if v.option.enableUnique>
        if (!${v.base.classNameLower}Service.checkUnique(bo)) {
            return R.fail("新增${v.base.functionName}失败，组合唯一字段已存在");
        }
</#if>
        return toAjax(${v.base.classNameLower}Service.insertByBo(bo));
    }

    /**
     * 修改${v.base.functionName}
     */
    @SaCheckPermission("${v.base.permissionPrefix}:edit")
    @Log(title = "${v.base.functionName}", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ${v.base.classNameUpper}Bo bo) {
<#if v.option.enableUnique>
        if (!${v.base.classNameLower}Service.checkUnique(bo)) {
            return R.fail("修改${v.base.functionName}失败，组合唯一字段已存在");
        }
</#if>
        return toAjax(${v.base.classNameLower}Service.updateByBo(bo));
    }

<#if v.option.enableStatus>
    /**
     * 修改${v.base.functionName}状态
     */
    @SaCheckPermission("${v.base.permissionPrefix}:edit")
    @Log(title = "${v.base.functionName}", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody ${v.base.classNameUpper}Bo bo) {
        return toAjax(${v.base.classNameLower}Service.updateStatus(bo.get${v.column.pkColumn.capJavaField}(), bo.get${v.option.statusColumn.capJavaField}()));
    }
</#if>

<#if v.option.enableSort>
    /**
     * 调整${v.base.functionName}排序
     */
    @SaCheckPermission("${v.base.permissionPrefix}:edit")
    @Log(title = "${v.base.functionName}", businessType = BusinessType.UPDATE)
    @PutMapping("/updateSort")
    public R<Void> updateSort(@RequestBody ${v.base.classNameUpper}Bo bo) {
        return toAjax(${v.base.classNameLower}Service.updateSort(bo.get${v.column.pkColumn.capJavaField}(), bo.get${v.option.sortColumn.capJavaField}()));
    }
</#if>

    /**
     * 删除${v.base.functionName}
     *
     * @param ${v.column.pkColumn.javaField}s 主键串
     */
    @SaCheckPermission("${v.base.permissionPrefix}:remove")
    @Log(title = "${v.base.functionName}", businessType = BusinessType.DELETE)
    @DeleteMapping("/{${v.column.pkColumn.javaField}s}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable ${v.column.pkColumn.javaType}[] ${v.column.pkColumn.javaField}s) {
        return toAjax(${v.base.classNameLower}Service.deleteWithValidByIds(List.of(${v.column.pkColumn.javaField}s), true));
    }
}
