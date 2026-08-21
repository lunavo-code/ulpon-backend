package com.ulpon.ai.domain.bo;

import com.ulpon.ai.domain.AiChatMessage;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 对话消息业务对象 ai_chat_message
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Data
@AutoMapper(target = AiChatMessage.class, reverseConvertGenerate = false)
public class AiChatMessageBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @NotNull(message = "主键 ID不能为空", groups = { EditGroup.class })
    private Long messageId;

    /**
     * 关联会话 ID
     */
    @NotNull(message = "关联会话 ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sessionId;

    /**
     * 角色类型（system, user, assistant, tool）
     */
    @NotBlank(message = "角色类型（system, user, assistant, tool）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String role;

    /**
     * 消息内容
     */
    @NotBlank(message = "消息内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;

    /**
     * Token 消耗数
     */
    private Integer tokenCount;


}
