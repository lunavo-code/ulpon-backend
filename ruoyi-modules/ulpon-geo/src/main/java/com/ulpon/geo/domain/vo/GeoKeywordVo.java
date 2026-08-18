package com.ulpon.geo.domain.vo;

import com.ulpon.geo.domain.GeoKeyword;
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
 * 核心关键词视图对象 geo_keyword
 *
 * @author Ulpon
 * @date 2026-08-09 12:53:08
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = GeoKeyword.class)
public class GeoKeywordVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 关键词
     */
    @ExcelProperty(value = "关键词")
    private String keyword;

    /**
     * 达标命中
     */
    @ExcelProperty(value = "达标命中")
    private String hit;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private Boolean status;


}
