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
 * 核心关键词
 *
 * @author Ulpon
 * @date 2026-08-09 12:53:08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/geo/geoKeyword")
public class GeoKeywordController extends BaseController {

    private final IGeoKeywordService geoKeywordService;

    /**
     * 查询核心关键词列表
     */
    @SaCheckPermission("geo:geoKeyword:list")
    @GetMapping("/list")
    public R<PageResult<GeoKeywordVo>> list(GeoKeywordBo bo, PageQuery pageQuery) {
        return R.ok(geoKeywordService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出核心关键词列表
     */
    @SaCheckPermission("geo:geoKeyword:export")
    @Log(title = "核心关键词", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GeoKeywordBo bo, HttpServletResponse response) {
        List<GeoKeywordVo> list = geoKeywordService.queryList(bo);
        ExcelBuilder.of(list, GeoKeywordVo.class).sheetName("核心关键词").toResponse(response);
    }

    /**
     * 获取核心关键词详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("geo:geoKeyword:query")
    @GetMapping("/{id}")
    public R<GeoKeywordVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(geoKeywordService.queryById(id));
    }

    /**
     * 新增核心关键词
     */
    @SaCheckPermission("geo:geoKeyword:add")
    @Log(title = "核心关键词", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GeoKeywordBo bo) {
        return toAjax(geoKeywordService.insertByBo(bo));
    }

    /**
     * 修改核心关键词
     */
    @SaCheckPermission("geo:geoKeyword:edit")
    @Log(title = "核心关键词", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GeoKeywordBo bo) {
        return toAjax(geoKeywordService.updateByBo(bo));
    }



    /**
     * 删除核心关键词
     *
     * @param ids 主键串
     */
    @SaCheckPermission("geo:geoKeyword:remove")
    @Log(title = "核心关键词", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(geoKeywordService.deleteWithValidByIds(List.of(ids), true));
    }
}
