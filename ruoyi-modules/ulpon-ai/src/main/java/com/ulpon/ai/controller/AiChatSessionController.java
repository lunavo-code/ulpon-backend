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
import com.ulpon.ai.domain.vo.AiChatSessionVo;
import com.ulpon.ai.domain.bo.AiChatSessionBo;
import com.ulpon.ai.service.IAiChatSessionService;
import org.dromara.common.core.domain.PageResult;

/**
 * 对话会话
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ai/chatSession")
public class AiChatSessionController extends BaseController {

    private final IAiChatSessionService aiChatSessionService;

    /**
     * 查询对话会话列表
     */
    @SaCheckPermission("ai:chatSession:list")
    @GetMapping("/list")
    public R<PageResult<AiChatSessionVo>> list(AiChatSessionBo bo, PageQuery pageQuery) {
        return R.ok(aiChatSessionService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出对话会话列表
     */
    @SaCheckPermission("ai:chatSession:export")
    @Log(title = "对话会话", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AiChatSessionBo bo, HttpServletResponse response) {
        List<AiChatSessionVo> list = aiChatSessionService.queryList(bo);
        ExcelBuilder.of(list, AiChatSessionVo.class).sheetName("对话会话").toResponse(response);
    }

    /**
     * 获取对话会话详细信息
     *
     * @param sessionId 主键
     */
    @SaCheckPermission("ai:chatSession:query")
    @GetMapping("/{sessionId}")
    public R<AiChatSessionVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long sessionId) {
        return R.ok(aiChatSessionService.queryById(sessionId));
    }

    /**
     * 新增对话会话
     */
    @SaCheckPermission("ai:chatSession:add")
    @Log(title = "对话会话", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AiChatSessionBo bo) {
        return toAjax(aiChatSessionService.insertByBo(bo));
    }

    /**
     * 修改对话会话
     */
    @SaCheckPermission("ai:chatSession:edit")
    @Log(title = "对话会话", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiChatSessionBo bo) {
        return toAjax(aiChatSessionService.updateByBo(bo));
    }



    /**
     * 删除对话会话
     *
     * @param sessionIds 主键串
     */
    @SaCheckPermission("ai:chatSession:remove")
    @Log(title = "对话会话", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sessionIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] sessionIds) {
        return toAjax(aiChatSessionService.deleteWithValidByIds(List.of(sessionIds), true));
    }
}
