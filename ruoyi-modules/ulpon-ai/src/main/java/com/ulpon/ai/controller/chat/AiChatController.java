package com.ulpon.ai.controller.chat;

import cn.dev33.satoken.annotation.SaIgnore;
import com.ulpon.ai.domain.bo.AiAgentChatBo;
import com.ulpon.ai.domain.bo.AiCapabilityChatBo;
import com.ulpon.ai.domain.vo.AiChatVo;
import com.ulpon.ai.service.impl.chat.AiAgentChatService;
import com.ulpon.ai.service.impl.chat.AiCapabilityChatService;
import lombok.AllArgsConstructor;
import org.dromara.common.core.domain.R;
import org.springframework.web.bind.annotation.*;

/**
 * 同步对话接口定义
 */
@RestController
@RequestMapping("ai/chat/d")
@AllArgsConstructor
@SaIgnore
public class AiChatController {
    private final AiAgentChatService agentChatService;
    private final AiCapabilityChatService capabilityChatService;

    /**
     * agent对话
     *
     * @param bo _
     * @return _
     */
    @PostMapping("a")
    public R<AiChatVo> chat(@RequestBody AiAgentChatBo bo) {
        return R.ok(agentChatService.chat(bo));
    }

    @PostMapping("c")
    public R<AiChatVo> chat(AiCapabilityChatBo bo) {
        return R.ok(capabilityChatService.chat(bo));
    }
}
