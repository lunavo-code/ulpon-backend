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
import com.ulpon.ai.domain.vo.AiModelProviderVo;
import com.ulpon.ai.domain.bo.AiModelProviderBo;
import com.ulpon.ai.service.IAiModelProviderService;
import org.dromara.common.core.domain.PageResult;

/**
 * 大模型提供商
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ai/modelProvider")
public class AiModelProviderController extends BaseController {

    private final IAiModelProviderService aiModelProviderService;

    /**
     * 查询大模型提供商列表
     */
    @SaCheckPermission("ai:modelProvider:list")
    @GetMapping("/list")
    public R<PageResult<AiModelProviderVo>> list(AiModelProviderBo bo, PageQuery pageQuery) {
        return R.ok(aiModelProviderService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出大模型提供商列表
     */
    @SaCheckPermission("ai:modelProvider:export")
    @Log(title = "大模型提供商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AiModelProviderBo bo, HttpServletResponse response) {
        List<AiModelProviderVo> list = aiModelProviderService.queryList(bo);
        ExcelBuilder.of(list, AiModelProviderVo.class).sheetName("大模型提供商").toResponse(response);
    }

    /**
     * 获取大模型提供商详细信息
     *
     * @param providerId 主键
     */
    @SaCheckPermission("ai:modelProvider:query")
    @GetMapping("/{providerId}")
    public R<AiModelProviderVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long providerId) {
        return R.ok(aiModelProviderService.queryById(providerId));
    }

    /**
     * 新增大模型提供商
     */
    @SaCheckPermission("ai:modelProvider:add")
    @Log(title = "大模型提供商", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AiModelProviderBo bo) {
        return toAjax(aiModelProviderService.insertByBo(bo));
    }

    /**
     * 修改大模型提供商
     */
    @SaCheckPermission("ai:modelProvider:edit")
    @Log(title = "大模型提供商", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiModelProviderBo bo) {
        return toAjax(aiModelProviderService.updateByBo(bo));
    }



    /**
     * 删除大模型提供商
     *
     * @param providerIds 主键串
     */
    @SaCheckPermission("ai:modelProvider:remove")
    @Log(title = "大模型提供商", businessType = BusinessType.DELETE)
    @DeleteMapping("/{providerIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] providerIds) {
        return toAjax(aiModelProviderService.deleteWithValidByIds(List.of(providerIds), true));
    }
}
