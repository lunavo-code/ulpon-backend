package com.ulpon.geo.domain.vo;

import com.ulpon.geo.domain.GeoTitle;
import org.apache.fesod.sheet.annotation.ExcelIgnoreUnannotated;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * GEO标题生成视图对象 geo_title
 *
 * @author Ulpon
 * @date 2026-08-09 17:39:11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = GeoTitle.class)
public class GeoTitleVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 关键词id
     */
    @ExcelProperty(value = "关键词id")
    private Long keywordId;

    /**
     * 标题
     */
    @ExcelProperty(value = "标题")
    private String title;

    /**
     * 收录状态
     */
    @ExcelProperty(value = "收录状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "geo_included")
    private String included;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private Boolean status;


}
