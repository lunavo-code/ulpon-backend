package com.ulpon.ai.domain.vo;

import com.ulpon.ai.domain.AiModelProvider;
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
 * 大模型提供商视图对象 ai_model_provider
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AiModelProvider.class)
public class AiModelProviderVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @ExcelProperty(value = "主键 ID")
    private Long providerId;

    /**
     * 提供商名称
     */
    @ExcelProperty(value = "提供商名称")
    private String providerName;

    /**
     * 提供商唯一标识键
     */
    @ExcelProperty(value = "提供商唯一标识键")
    private String providerCode;

    /**
     * 默认 API 接口地址
     */
    @ExcelProperty(value = "默认 API 接口地址")
    private String defaultBaseUrl;

    /**
     * 启用状态（0正常 1停用）
     */
    @ExcelProperty(value = "启用状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private Boolean status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
