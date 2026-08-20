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
 * 核心词管理视图对象 geo_keyword
 *
 * @author Ulpon
 * @date 2026-08-20 18:03:06
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = GeoKeyword.class)
public class GeoKeywordVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long keywordId;

    /**
     * 公司/品牌名称
     */
    @ExcelProperty(value = "公司/品牌名称")
    private String companyName;

    /**
     * 关键词分类
     */
    @ExcelProperty(value = "关键词分类", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "geo_keyword_type")
    private String keywordType;

    /**
     * 核心关键词
     */
    @ExcelProperty(value = "核心关键词")
    private String keyword;

    /**
     * 状态(0启用 1停用)
     */
    @ExcelProperty(value = "状态(0启用 1停用)", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
