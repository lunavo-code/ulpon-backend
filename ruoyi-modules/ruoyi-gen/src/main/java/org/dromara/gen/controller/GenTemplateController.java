package org.dromara.gen.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.gen.domain.GenTemplate;
import org.dromara.gen.domain.vo.RenderParam;
import org.dromara.gen.enums.TemplateTypeEnum;
import org.dromara.gen.util.TemplateLoadUtil;
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
import org.dromara.common.excel.utils.ExcelBuilder;
import org.dromara.gen.domain.vo.GenTemplateVo;
import org.dromara.gen.domain.bo.GenTemplateBo;
import org.dromara.gen.service.IGenTemplateService;
import org.dromara.common.core.domain.PageResult;

/**
 * 代码生成模板
 *
 * @author Ulpon
 * @date 2026-08-28 14:02:26
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/gen/template")
public class GenTemplateController extends BaseController {

    private final IGenTemplateService genTemplateService;

    /**
     * 查询代码生成模板列表
     */
    @SaCheckPermission("gen:template:list")
    @GetMapping("/list")
    public R<PageResult<GenTemplateVo>> list(GenTemplateBo bo, PageQuery pageQuery) {
        return R.ok(genTemplateService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出代码生成模板列表
     */
    @SaCheckPermission("gen:template:export")
    @Log(title = "代码生成模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GenTemplateBo bo, HttpServletResponse response) {
        List<GenTemplateVo> list = genTemplateService.queryList(bo);
        ExcelBuilder.of(list, GenTemplateVo.class).sheetName("代码生成模板").toResponse(response);
    }

    /**
     * 获取代码生成模板详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("gen:template:query")
    @GetMapping("/{id}")
    public R<GenTemplateVo> getInfo(@NotNull(message = "主键不能为空")
                                    @PathVariable Long id) {
        return R.ok(genTemplateService.queryById(id));
    }

    /**
     * 新增代码生成模板
     */
    @SaCheckPermission("gen:template:add")
    @Log(title = "代码生成模板", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GenTemplateBo bo) {
        return toAjax(genTemplateService.insertByBo(bo));
    }

    /**
     * 修改代码生成模板
     */
    @SaCheckPermission("gen:template:edit")
    @Log(title = "代码生成模板", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GenTemplateBo bo) {
        return toAjax(genTemplateService.updateByBo(bo));
    }

    /**
     * 删除代码生成模板
     *
     * @param ids 主键串
     */
    @SaCheckPermission("gen:template:remove")
    @Log(title = "代码生成模板", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(genTemplateService.deleteWithValidByIds(List.of(ids), true));
    }

//    /**
//     * 解析模板
//     */
//    @PostMapping("render")
//    public R<String> render(@RequestBody RenderParam renderParam) {
//        GenTemplate genTemplate = new GenTemplate(100L, 100L, "name", TemplateTypeEnum.java, "1.0", "abcde", renderParam.getContent(), 0);
//        Map<String, Object> params = renderParam.getParams();
//        try {
//            TemplateLoadUtil.CodeInfo codeInfo = TemplateLoadUtil.loadTemplateMap(params, genTemplate);
//            return R.ok(R.SUCCESS_MESSAGE, codeInfo.getContent());
//        } catch (Exception e) {
//            String message = e.getMessage();
//            return R.ok(R.SUCCESS_MESSAGE, message);
//        }
//    }
}
