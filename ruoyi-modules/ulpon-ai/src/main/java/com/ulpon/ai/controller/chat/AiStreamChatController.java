package com.ulpon.ai.controller.chat;

import cn.dev33.satoken.annotation.SaIgnore;
import com.ulpon.ai.config.propertie.AiUlponProperties;
import com.ulpon.ai.domain.bo.AiAgentChatBo;
import com.ulpon.ai.domain.bo.AiCapabilityChatBo;
import com.ulpon.ai.domain.vo.AiChatVo;
import com.ulpon.ai.service.impl.chat.AiAgentChatService;
import com.ulpon.ai.service.impl.chat.AiCapabilityChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * 流式会话接口定义
 */
@RestController
@RequestMapping(value = "ai/chat/s", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
@AllArgsConstructor
@SaIgnore
public class AiStreamChatController {
    private final AiAgentChatService agentChatService;
    private final AiCapabilityChatService capabilityChatService;
    private final AiUlponProperties properties;

    /**
     * agent对话
     *
     * @param bo _
     * @return _
     */
    @PostMapping("a")
    public Flux<AiChatVo> agentStream(@RequestBody AiAgentChatBo bo) {
        return agentChatService.stream(bo);
    }

    /**
     * 能力对话
     *
     * @param bo _
     * @return _
     */
    @PostMapping("c")
    public Flux<AiChatVo> capabilityStream(@RequestBody AiCapabilityChatBo bo) {
        return capabilityChatService.stream(bo);
    }

    @PostMapping("agent/meta")
    public Flux<AiChatVo> genAgentMeta(@RequestBody String userMsg) {
        return capabilityChatService.stream(properties.getGenerateAgentMetaAiCapabilityId(), userMsg);
    }

}
