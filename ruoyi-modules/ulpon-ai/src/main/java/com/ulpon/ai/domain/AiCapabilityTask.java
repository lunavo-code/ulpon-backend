package com.ulpon.ai.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * AI能力任务对象 ai_capability_task
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("ai_capability_task")
public class AiCapabilityTask extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "task_id")
    private Long taskId;

    /**
     * 能力 ID
     */
    private Long capabilityId;

    /**
     * 用户消息
     */
    private String userContent;

    /**
     * 反馈消息
     */
    private String resContent;

    /**
     * Token 消耗数
     */
    private Integer tokenCount;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private Boolean delFlag;


}
