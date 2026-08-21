package com.ulpon.ai.domain.vo;

import com.ulpon.ai.constant.AiTranslationConstant;
import com.ulpon.ai.domain.AiAgent;
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
 * 智能体视图对象 ai_agent
 *
 * @author Ulpon
 * @date 2026-08-21 12:08:12
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AiAgent.class)
public class AiAgentVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @ExcelProperty(value = "主键 ID")
    private Long agentId;

    /**
     * 智能体名称
     */
    @ExcelProperty(value = "智能体名称")
    private String agentName;

    /**
     * 智能体头像
     */
    @ExcelProperty(value = "智能体头像")
    private String avatar;

    /**
     * 智能体头像Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "avatar")
    private String avatarUrl;
    /**
     * 智能体描述简介
     */
    @ExcelProperty(value = "智能体描述简介")
    private String description;

    /**
     * 系统提示词 (System Prompt)
     */
    @ExcelProperty(value = "系统提示词 (System Prompt)")
    private String systemPrompt;

    /**
     * 关联大模型配置 ID
     */
//    @ExcelProperty(value = "关联大模型配置 ID")
    private Long modelConfigId;
    /**
     * 关联大模型配置 ID
     */
    @ExcelProperty(value = "关联大模型配置名称")
    @Translation(type = AiTranslationConstant.CHAT_MODEL_ID_2_NAME, mapper = "modelConfigId")
    private Long modelConfigName;

    /**
     * 启用知识库（0停用 1启用）
     */
    @ExcelProperty(value = "启用知识库", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private Boolean kbEnabled;

    /**
     * 关联知识库 ID
     */
    @ExcelProperty(value = "关联知识库 ID")
    private Long kbId;

    /**
     * 启用联网检索（0停用 1启用）
     */
    @ExcelProperty(value = "启用联网检索", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private Boolean searchEnabled;

    /**
     * 启用聊天记忆（0停用 1启用）
     */
    @ExcelProperty(value = "启用聊天记忆", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private Boolean memoryEnabled;

    /**
     * 记忆窗口长度
     */
    @ExcelProperty(value = "记忆窗口长度")
    private Integer memoryWindow;

    /**
     * 问候语
     */
    @ExcelProperty(value = "问候语")
    private String greeting;

    /**
     * 预设问题
     */
    @ExcelProperty(value = "预设问题")
    private String presetQuestions;

    /**
     * 可见范围类型（1个人 2组织 3公开）
     */
    @ExcelProperty(value = "可见范围类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ai_agent_visibility")
    private String scopeType;

    /**
     * 启用状态（0正常 1停用）
     */
    @ExcelProperty(value = "启用状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=正常,1=停用")
    private Boolean status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
