package com.ulpon.ai.domain.bo;

import com.ulpon.ai.domain.AiChatSession;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 对话会话业务对象 ai_chat_session
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Data
@AutoMapper(target = AiChatSession.class, reverseConvertGenerate = false)
public class AiChatSessionBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @NotNull(message = "主键 ID不能为空", groups = { EditGroup.class })
    private Long sessionId;

    /**
     * 会话名称
     */
    @NotBlank(message = "会话名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sessionName;

    /**
     * 关联智能体 ID
     */
    @NotNull(message = "关联智能体 ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long agentId;

    /**
     * 会话所有者 ID
     */
    @NotNull(message = "会话所有者 ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;


}
