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
import com.ulpon.ai.domain.vo.AiCapabilityVo;
import com.ulpon.ai.domain.bo.AiCapabilityBo;
import com.ulpon.ai.service.IAiCapabilityService;
import org.dromara.common.core.domain.PageResult;

/**
 * AI能力
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ai/capability")
public class AiCapabilityController extends BaseController {

    private final IAiCapabilityService aiCapabilityService;

    /**
     * 查询AI能力列表
     */
    @SaCheckPermission("ai:capability:list")
    @GetMapping("/list")
    public R<PageResult<AiCapabilityVo>> list(AiCapabilityBo bo, PageQuery pageQuery) {
        return R.ok(aiCapabilityService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出AI能力列表
     */
    @SaCheckPermission("ai:capability:export")
    @Log(title = "AI能力", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AiCapabilityBo bo, HttpServletResponse response) {
        List<AiCapabilityVo> list = aiCapabilityService.queryList(bo);
        ExcelBuilder.of(list, AiCapabilityVo.class).sheetName("AI能力").toResponse(response);
    }

    /**
     * 获取AI能力详细信息
     *
     * @param capabilityId 主键
     */
    @SaCheckPermission("ai:capability:query")
    @GetMapping("/{capabilityId}")
    public R<AiCapabilityVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long capabilityId) {
        return R.ok(aiCapabilityService.queryById(capabilityId));
    }

    /**
     * 新增AI能力
     */
    @SaCheckPermission("ai:capability:add")
    @Log(title = "AI能力", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AiCapabilityBo bo) {
        return toAjax(aiCapabilityService.insertByBo(bo));
    }

    /**
     * 修改AI能力
     */
    @SaCheckPermission("ai:capability:edit")
    @Log(title = "AI能力", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiCapabilityBo bo) {
        return toAjax(aiCapabilityService.updateByBo(bo));
    }



    /**
     * 删除AI能力
     *
     * @param capabilityIds 主键串
     */
    @SaCheckPermission("ai:capability:remove")
    @Log(title = "AI能力", businessType = BusinessType.DELETE)
    @DeleteMapping("/{capabilityIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] capabilityIds) {
        return toAjax(aiCapabilityService.deleteWithValidByIds(List.of(capabilityIds), true));
    }
}
