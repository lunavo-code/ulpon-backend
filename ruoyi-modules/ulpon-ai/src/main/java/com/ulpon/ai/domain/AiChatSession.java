package com.ulpon.ai.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 对话会话对象 ai_chat_session
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_chat_session")
@AllArgsConstructor
@NoArgsConstructor
public class AiChatSession extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "session_id")
    private Long sessionId;

    /**
     * 会话名称
     */
    private String sessionName;

    /**
     * 关联智能体 ID
     */
    private Long agentId;

    /**
     * 会话所有者 ID
     */
    private Long userId;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private Boolean delFlag;


}
