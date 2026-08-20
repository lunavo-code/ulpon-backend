package com.ulpon.geo.controller;

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
import com.ulpon.geo.domain.vo.GeoKeywordDerivedVo;
import com.ulpon.geo.domain.bo.GeoKeywordDerivedBo;
import com.ulpon.geo.service.IGeoKeywordDerivedService;
import org.dromara.common.core.domain.PageResult;

/**
 * 派生标题管理
 *
 * @author Ulpon
 * @date 2026-08-20 18:03:07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/geo/keywordDerived")
public class GeoKeywordDerivedController extends BaseController {

    private final IGeoKeywordDerivedService geoKeywordDerivedService;

    /**
     * 查询派生标题管理列表
     */
    @SaCheckPermission("geo:keywordDerived:list")
    @GetMapping("/list")
    public R<PageResult<GeoKeywordDerivedVo>> list(GeoKeywordDerivedBo bo, PageQuery pageQuery) {
        return R.ok(geoKeywordDerivedService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出派生标题管理列表
     */
    @SaCheckPermission("geo:keywordDerived:export")
    @Log(title = "派生标题管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GeoKeywordDerivedBo bo, HttpServletResponse response) {
        List<GeoKeywordDerivedVo> list = geoKeywordDerivedService.queryList(bo);
        ExcelBuilder.of(list, GeoKeywordDerivedVo.class).sheetName("派生标题管理").toResponse(response);
    }

    /**
     * 获取派生标题管理详细信息
     *
     * @param derivedId 主键
     */
    @SaCheckPermission("geo:keywordDerived:query")
    @GetMapping("/{derivedId}")
    public R<GeoKeywordDerivedVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long derivedId) {
        return R.ok(geoKeywordDerivedService.queryById(derivedId));
    }

    /**
     * 新增派生标题管理
     */
    @SaCheckPermission("geo:keywordDerived:add")
    @Log(title = "派生标题管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GeoKeywordDerivedBo bo) {
        return toAjax(geoKeywordDerivedService.insertByBo(bo));
    }

    /**
     * 修改派生标题管理
     */
    @SaCheckPermission("geo:keywordDerived:edit")
    @Log(title = "派生标题管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GeoKeywordDerivedBo bo) {
        return toAjax(geoKeywordDerivedService.updateByBo(bo));
    }



    /**
     * 删除派生标题管理
     *
     * @param derivedIds 主键串
     */
    @SaCheckPermission("geo:keywordDerived:remove")
    @Log(title = "派生标题管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{derivedIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] derivedIds) {
        return toAjax(geoKeywordDerivedService.deleteWithValidByIds(List.of(derivedIds), true));
    }
}
