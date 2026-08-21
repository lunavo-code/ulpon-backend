package com.ulpon.ai.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
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
import org.dromara.common.excel.utils.ExcelBuilder;
import com.ulpon.ai.domain.vo.AiModelConfigVo;
import com.ulpon.ai.domain.bo.AiModelConfigBo;
import com.ulpon.ai.service.IAiModelConfigService;
import org.dromara.common.core.domain.PageResult;

/**
 * 大模型配置
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ai/modelConfig")
public class AiModelConfigController extends BaseController {

    private final IAiModelConfigService aiModelConfigService;

    /**
     * 查询大模型配置列表
     */
    @SaCheckPermission("ai:modelConfig:list")
    @GetMapping("/list")
    public R<PageResult<AiModelConfigVo>> list(AiModelConfigBo bo, PageQuery pageQuery) {
        return R.ok(aiModelConfigService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出大模型配置列表
     */
    @SaCheckPermission("ai:modelConfig:export")
    @Log(title = "大模型配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AiModelConfigBo bo, HttpServletResponse response) {
        List<AiModelConfigVo> list = aiModelConfigService.queryList(bo);
        ExcelBuilder.of(list, AiModelConfigVo.class).sheetName("大模型配置").toResponse(response);
    }

    /**
     * 获取大模型配置详细信息
     *
     * @param modelConfigId 主键
     */
    @SaCheckPermission("ai:modelConfig:query")
    @GetMapping("/{modelConfigId}")
    public R<AiModelConfigVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long modelConfigId) {
        return R.ok(aiModelConfigService.queryById(modelConfigId));
    }

    /**
     * 新增大模型配置
     */
    @SaCheckPermission("ai:modelConfig:add")
    @Log(title = "大模型配置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AiModelConfigBo bo) {
        return toAjax(aiModelConfigService.insertByBo(bo));
    }

    /**
     * 修改大模型配置
     */
    @SaCheckPermission("ai:modelConfig:edit")
    @Log(title = "大模型配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiModelConfigBo bo) {
        return toAjax(aiModelConfigService.updateByBo(bo));
    }



    /**
     * 删除大模型配置
     *
     * @param modelConfigIds 主键串
     */
    @SaCheckPermission("ai:modelConfig:remove")
    @Log(title = "大模型配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{modelConfigIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] modelConfigIds) {
        return toAjax(aiModelConfigService.deleteWithValidByIds(List.of(modelConfigIds), true));
    }
}
