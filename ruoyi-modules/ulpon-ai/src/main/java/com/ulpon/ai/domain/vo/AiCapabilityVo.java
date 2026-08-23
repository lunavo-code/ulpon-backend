package com.ulpon.ai.domain.vo;

import com.ulpon.ai.constant.AiTranslationConstant;
import com.ulpon.ai.domain.AiCapability;
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
 * AI能力视图对象 ai_capability
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AiCapability.class)
public class AiCapabilityVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @ExcelProperty(value = "主键 ID")
    private Long capabilityId;

    /**
     * 能力名称
     */
    @ExcelProperty(value = "能力名称")
    private String capabilityName;

    /**
     * 任务描述简介
     */
    @ExcelProperty(value = "任务描述简介")
    private String description;

    /**
     * 系统提示词 (System Prompt)
     */
    @ExcelProperty(value = "系统提示词 (System Prompt)")
    private String systemPrompt;

    /**
     * 关联大模型配置 ID
     */
    private Long modelConfigId;

    @ExcelProperty(value = "关联大模型配置")
    @Translation(type = AiTranslationConstant.MODEL_CONFIG_ID_2_NAME, mapper = "modelConfigId")
    private String modelConfigName;

    /**
     * 可见范围类型（1个人 2组织 3公开）
     */
    @ExcelProperty(value = "可见范围类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ai_agent_visibility")
    private String scopeType;

    /**
     * 所属部门 ID
     */
    @ExcelProperty(value = "所属部门 ID")
    private Long deptId;

    /**
     * 创建人用户 ID
     */
    @ExcelProperty(value = "创建人用户 ID")
    private Long userId;

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
