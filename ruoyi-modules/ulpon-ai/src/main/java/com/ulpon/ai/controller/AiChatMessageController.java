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
import com.ulpon.ai.domain.vo.AiChatMessageVo;
import com.ulpon.ai.domain.bo.AiChatMessageBo;
import com.ulpon.ai.service.IAiChatMessageService;
import org.dromara.common.core.domain.PageResult;

/**
 * 对话消息
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ai/chatMessage")
public class AiChatMessageController extends BaseController {

    private final IAiChatMessageService aiChatMessageService;

    /**
     * 查询对话消息列表
     */
    @SaCheckPermission("ai:chatMessage:list")
    @GetMapping("/list")
    public R<PageResult<AiChatMessageVo>> list(AiChatMessageBo bo, PageQuery pageQuery) {
        return R.ok(aiChatMessageService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出对话消息列表
     */
    @SaCheckPermission("ai:chatMessage:export")
    @Log(title = "对话消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AiChatMessageBo bo, HttpServletResponse response) {
        List<AiChatMessageVo> list = aiChatMessageService.queryList(bo);
        ExcelBuilder.of(list, AiChatMessageVo.class).sheetName("对话消息").toResponse(response);
    }

    /**
     * 获取对话消息详细信息
     *
     * @param messageId 主键
     */
    @SaCheckPermission("ai:chatMessage:query")
    @GetMapping("/{messageId}")
    public R<AiChatMessageVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long messageId) {
        return R.ok(aiChatMessageService.queryById(messageId));
    }

    /**
     * 新增对话消息
     */
    @SaCheckPermission("ai:chatMessage:add")
    @Log(title = "对话消息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AiChatMessageBo bo) {
        return toAjax(aiChatMessageService.insertByBo(bo));
    }

    /**
     * 修改对话消息
     */
    @SaCheckPermission("ai:chatMessage:edit")
    @Log(title = "对话消息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiChatMessageBo bo) {
        return toAjax(aiChatMessageService.updateByBo(bo));
    }



    /**
     * 删除对话消息
     *
     * @param messageIds 主键串
     */
    @SaCheckPermission("ai:chatMessage:remove")
    @Log(title = "对话消息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{messageIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] messageIds) {
        return toAjax(aiChatMessageService.deleteWithValidByIds(List.of(messageIds), true));
    }
}
