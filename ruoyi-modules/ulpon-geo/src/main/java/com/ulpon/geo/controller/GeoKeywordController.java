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
import com.ulpon.geo.domain.vo.GeoKeywordVo;
import com.ulpon.geo.domain.bo.GeoKeywordBo;
import com.ulpon.geo.service.IGeoKeywordService;
import org.dromara.common.core.domain.PageResult;

/**
 * 核心词管理
 *
 * @author Ulpon
 * @date 2026-08-20 18:03:06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/geo/keyword")
public class GeoKeywordController extends BaseController {

    private final IGeoKeywordService geoKeywordService;

    /**
     * 查询核心词管理列表
     */
    @SaCheckPermission("geo:keyword:list")
    @GetMapping("/list")
    public R<PageResult<GeoKeywordVo>> list(GeoKeywordBo bo, PageQuery pageQuery) {
        return R.ok(geoKeywordService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出核心词管理列表
     */
    @SaCheckPermission("geo:keyword:export")
    @Log(title = "核心词管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GeoKeywordBo bo, HttpServletResponse response) {
        List<GeoKeywordVo> list = geoKeywordService.queryList(bo);
        ExcelBuilder.of(list, GeoKeywordVo.class).sheetName("核心词管理").toResponse(response);
    }

    /**
     * 获取核心词管理详细信息
     *
     * @param keywordId 主键
     */
    @SaCheckPermission("geo:keyword:query")
    @GetMapping("/{keywordId}")
    public R<GeoKeywordVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long keywordId) {
        return R.ok(geoKeywordService.queryById(keywordId));
    }

    /**
     * 新增核心词管理
     */
    @SaCheckPermission("geo:keyword:add")
    @Log(title = "核心词管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GeoKeywordBo bo) {
        return toAjax(geoKeywordService.insertByBo(bo));
    }

    /**
     * 修改核心词管理
     */
    @SaCheckPermission("geo:keyword:edit")
    @Log(title = "核心词管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GeoKeywordBo bo) {
        return toAjax(geoKeywordService.updateByBo(bo));
    }



    /**
     * 删除核心词管理
     *
     * @param keywordIds 主键串
     */
    @SaCheckPermission("geo:keyword:remove")
    @Log(title = "核心词管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{keywordIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] keywordIds) {
        return toAjax(geoKeywordService.deleteWithValidByIds(List.of(keywordIds), true));
    }
}
