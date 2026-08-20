package com.ulpon.geo.domain.vo;

import com.ulpon.geo.constant.GeoTranslationConstant;
import com.ulpon.geo.domain.GeoKeywordDerived;
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
 * 派生标题管理视图对象 geo_keyword_derived
 *
 * @author Ulpon
 * @date 2026-08-20 18:03:07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = GeoKeywordDerived.class)
public class GeoKeywordDerivedVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long derivedId;

    /**
     * 关联核心词ID
     */
    @ExcelProperty(value = "关联核心词ID")
    private Long keywordId;

    @Translation(type = GeoTranslationConstant.KEYWORD_ID_2_KEYWORD, mapper = "keywordId")
    private String keyword;

    /**
     * 标题类型
     */
    @ExcelProperty(value = "标题类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "geo_title_type")
    private String titleType;

    /**
     * 生成的文章标题/用户提问内容
     */
    @ExcelProperty(value = "生成的文章标题/用户提问内容")
    private String derivedQuestion;

    /**
     * 状态(0待创作 1已创作 2已禁用)
     */
    @ExcelProperty(value = "状态(0待创作 1已创作 2已禁用)", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "geo_derived_status")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
