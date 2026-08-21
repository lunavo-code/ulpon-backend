package com.ulpon.ai.domain.bo;

import com.ulpon.ai.domain.AiAgent;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 智能体业务对象 ai_agent
 *
 * @author Ulpon
 * @date 2026-08-21 12:08:12
 */
@Data
@AutoMapper(target = AiAgent.class, reverseConvertGenerate = false)
public class AiAgentBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @NotNull(message = "主键 ID不能为空", groups = { EditGroup.class })
    private Long agentId;

    /**
     * 智能体名称
     */
    @NotBlank(message = "智能体名称不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "关联大模型配置 ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long modelConfigId;

    /**
     * 启用知识库（0停用 1启用）
     */
    @NotNull(message = "启用知识库（0停用 1启用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Boolean kbEnabled;

    /**
     * 关联知识库 ID
     */
    private Long kbId;

    /**
     * 启用联网检索（0停用 1启用）
     */
    @NotNull(message = "启用联网检索（0停用 1启用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Boolean searchEnabled;

    /**
     * 启用聊天记忆（0停用 1启用）
     */
    @NotNull(message = "启用聊天记忆（0停用 1启用）不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotBlank(message = "可见范围类型（1个人 2组织 3公开）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String scopeType;

    /**
     * 启用状态（0正常 1停用）
     */
    @NotNull(message = "启用状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Boolean status;

    /**
     * 备注
     */
    private String remark;


}
