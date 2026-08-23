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
import com.ulpon.ai.domain.vo.AiCapabilityTaskVo;
import com.ulpon.ai.domain.bo.AiCapabilityTaskBo;
import com.ulpon.ai.service.IAiCapabilityTaskService;
import org.dromara.common.core.domain.PageResult;

/**
 * AI能力任务
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ai/capabilityTask")
public class AiCapabilityTaskController extends BaseController {

    private final IAiCapabilityTaskService aiCapabilityTaskService;

    /**
     * 查询AI能力任务列表
     */
    @SaCheckPermission("ai:capabilityTask:list")
    @GetMapping("/list")
    public R<PageResult<AiCapabilityTaskVo>> list(AiCapabilityTaskBo bo, PageQuery pageQuery) {
        return R.ok(aiCapabilityTaskService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出AI能力任务列表
     */
    @SaCheckPermission("ai:capabilityTask:export")
    @Log(title = "AI能力任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AiCapabilityTaskBo bo, HttpServletResponse response) {
        List<AiCapabilityTaskVo> list = aiCapabilityTaskService.queryList(bo);
        ExcelBuilder.of(list, AiCapabilityTaskVo.class).sheetName("AI能力任务").toResponse(response);
    }

    /**
     * 获取AI能力任务详细信息
     *
     * @param taskId 主键
     */
    @SaCheckPermission("ai:capabilityTask:query")
    @GetMapping("/{taskId}")
    public R<AiCapabilityTaskVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long taskId) {
        return R.ok(aiCapabilityTaskService.queryById(taskId));
    }

    /**
     * 新增AI能力任务
     */
    @SaCheckPermission("ai:capabilityTask:add")
    @Log(title = "AI能力任务", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AiCapabilityTaskBo bo) {
        return toAjax(aiCapabilityTaskService.insertByBo(bo));
    }

    /**
     * 修改AI能力任务
     */
    @SaCheckPermission("ai:capabilityTask:edit")
    @Log(title = "AI能力任务", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiCapabilityTaskBo bo) {
        return toAjax(aiCapabilityTaskService.updateByBo(bo));
    }



    /**
     * 删除AI能力任务
     *
     * @param taskIds 主键串
     */
    @SaCheckPermission("ai:capabilityTask:remove")
    @Log(title = "AI能力任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{taskIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] taskIds) {
        return toAjax(aiCapabilityTaskService.deleteWithValidByIds(List.of(taskIds), true));
    }
}
