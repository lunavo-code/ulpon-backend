package com.ulpon.ai.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 智能体对象 ai_agent
 *
 * @author Ulpon
 * @date 2026-08-21 12:08:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_agent")
public class AiAgent extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "agent_id")
    private Long agentId;

    /**
     * 智能体名称
     */
    private String agentName;

    /**
     * 智能体头像
     */
    private String avatar;

    /**
     * 智能体描述简介
     */
    private String description;

    /**
     * 系统提示词 (System Prompt)
     */
    private String systemPrompt;

    /**
     * 关联大模型配置 ID
     */
    private Long modelConfigId;

    /**
     * 启用知识库（0停用 1启用）
     */
    private Boolean kbEnabled;

    /**
     * 关联知识库 ID
     */
    private Long kbId;

    /**
     * 启用联网检索（0停用 1启用）
     */
    private Boolean searchEnabled;

    /**
     * 启用聊天记忆（0停用 1启用）
     */
    private Boolean memoryEnabled;

    /**
     * 记忆窗口长度
     */
    private Integer memoryWindow;

    /**
     * 问候语
     */
    private String greeting;

    /**
     * 预设问题
     */
    private String presetQuestions;

    /**
     * 可见范围类型（1个人 2组织 3公开）
     */
    private String scopeType;

    /**
     * 启用状态（0正常 1停用）
     */
    private Boolean status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private Boolean delFlag;

    /**
     * 备注
     */
    private String remark;


}
