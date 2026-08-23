package com.ulpon.ai.domain.bo;

import com.ulpon.ai.domain.AiCapability;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * AI能力业务对象 ai_capability
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
@Data
@AutoMapper(target = AiCapability.class, reverseConvertGenerate = false)
public class AiCapabilityBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @NotNull(message = "主键 ID不能为空", groups = { EditGroup.class })
    private Long capabilityId;

    /**
     * 能力名称
     */
    @NotBlank(message = "能力名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String capabilityName;

    /**
     * 任务描述简介
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
     * 可见范围类型（1个人 2组织 3公开）
     */
    @NotBlank(message = "可见范围类型（1个人 2组织 3公开）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String scopeType;

    /**
     * 所属部门 ID
     */
    private Long deptId;

    /**
     * 创建人用户 ID
     */
    @NotNull(message = "创建人用户 ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

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
