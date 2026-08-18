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
import com.ulpon.geo.domain.vo.GeoTitleVo;
import com.ulpon.geo.domain.bo.GeoTitleBo;
import com.ulpon.geo.service.IGeoTitleService;
import org.dromara.common.core.domain.PageResult;

/**
 * GEO标题生成
 *
 * @author Ulpon
 * @date 2026-08-09 17:39:11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/geo/geoTitle")
public class GeoTitleController extends BaseController {

    private final IGeoTitleService geoTitleService;

    /**
     * 查询GEO标题生成列表
     */
    @SaCheckPermission("geo:geoTitle:list")
    @GetMapping("/list")
    public R<PageResult<GeoTitleVo>> list(GeoTitleBo bo, PageQuery pageQuery) {
        return R.ok(geoTitleService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出GEO标题生成列表
     */
    @SaCheckPermission("geo:geoTitle:export")
    @Log(title = "GEO标题生成", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GeoTitleBo bo, HttpServletResponse response) {
        List<GeoTitleVo> list = geoTitleService.queryList(bo);
        ExcelBuilder.of(list, GeoTitleVo.class).sheetName("GEO标题生成").toResponse(response);
    }

    /**
     * 获取GEO标题生成详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("geo:geoTitle:query")
    @GetMapping("/{id}")
    public R<GeoTitleVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(geoTitleService.queryById(id));
    }

    /**
     * 新增GEO标题生成
     */
    @SaCheckPermission("geo:geoTitle:add")
    @Log(title = "GEO标题生成", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GeoTitleBo bo) {
        return toAjax(geoTitleService.insertByBo(bo));
    }

    /**
     * 修改GEO标题生成
     */
    @SaCheckPermission("geo:geoTitle:edit")
    @Log(title = "GEO标题生成", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GeoTitleBo bo) {
        return toAjax(geoTitleService.updateByBo(bo));
    }



    /**
     * 删除GEO标题生成
     *
     * @param ids 主键串
     */
    @SaCheckPermission("geo:geoTitle:remove")
    @Log(title = "GEO标题生成", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(geoTitleService.deleteWithValidByIds(List.of(ids), true));
    }
}
