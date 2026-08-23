package com.ulpon.ai.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * AI能力对象 ai_capability
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_capability")
public class AiCapability extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "capability_id")
    private Long capabilityId;

    /**
     * 能力名称
     */
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
    private Long modelConfigId;

    /**
     * 可见范围类型（1个人 2组织 3公开）
     */
    private String scopeType;

    /**
     * 所属部门 ID
     */
    private Long deptId;

    /**
     * 创建人用户 ID
     */
    private Long userId;

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
