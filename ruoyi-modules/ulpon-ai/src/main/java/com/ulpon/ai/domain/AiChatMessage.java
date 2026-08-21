package com.ulpon.ai.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 对话消息对象 ai_chat_message
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_chat_message")
@AllArgsConstructor
@NoArgsConstructor
public class AiChatMessage extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "message_id")
    private Long messageId;

    /**
     * 关联会话 ID
     */
    private Long sessionId;

    /**
     * 角色类型（system, user, assistant, tool）
     */
    private String role;

    /**
     * 消息内容
     */
    private String content;

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
