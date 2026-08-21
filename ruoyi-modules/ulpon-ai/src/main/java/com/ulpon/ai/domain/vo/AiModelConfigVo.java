package com.ulpon.ai.domain.vo;

import com.ulpon.ai.constant.AiTranslationConstant;
import com.ulpon.ai.domain.AiModelConfig;
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
 * 大模型配置视图对象 ai_model_config
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AiModelConfig.class)
public class AiModelConfigVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @ExcelProperty(value = "主键 ID")
    private Long modelConfigId;

    /**
     * 提供商 ID
     */
    private Long providerId;

    @ExcelProperty(value = "提供商名称")
    @Translation(type = AiTranslationConstant.MODEL_PROVIDER_ID_2_NAME, mapper = "providerId")
    private Long providerName;

    /**
     * 配置别名/名称
     */
    @ExcelProperty(value = "配置别名/名称")
    private String configName;

    /**
     * 目标模型名称
     */
    @ExcelProperty(value = "目标模型名称")
    private String modelName;

    /**
     * API 接口覆盖地址
     */
    @ExcelProperty(value = "API 接口覆盖地址")
    private String baseUrl;

    /**
     * API 密钥 (Script Key)
     */
    @ExcelProperty(value = "API 密钥 (Script Key)")
    private String apiKey;

    /**
     * 最大生成 Token 数
     */
    @ExcelProperty(value = "最大生成 Token 数")
    private Integer maxTokens;

    /**
     * 温度参数 (0.0 ~ 2.0)
     */
    @ExcelProperty(value = "温度参数 (0.0 ~ 2.0)")
    private Double temperature;

    /**
     * 启用状态（0正常 1停用）
     */
    @ExcelProperty(value = "启用状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private Boolean status;

    /**
     * 是否为默认模型（Y是 N否）
     */
    @ExcelProperty(value = "是否为默认模型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private Boolean isDefault;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
