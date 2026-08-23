package com.ulpon.ai.service.impl.chat;

import com.ulpon.ai.agent.DynamicAgent;
import com.ulpon.ai.common.AgentFactory;
import com.ulpon.ai.config.propertie.AiUlponProperties;
import com.ulpon.ai.domain.AiAgent;
import com.ulpon.ai.domain.AiChatSession;
import com.ulpon.ai.domain.bo.AiAgentChatBo;
import com.ulpon.ai.domain.vo.AiChatVo;
import com.ulpon.ai.enums.MsgTypeEnum;
import com.ulpon.ai.exceptions.AiException;
import com.ulpon.ai.service.IAiAgentService;
import com.ulpon.ai.service.IAiChatSessionService;
import com.ulpon.ai.util.AiResUtils;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.service.TokenStream;
import lombok.AllArgsConstructor;
import org.dromara.common.mybatis.utils.IdGeneratorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * 定义agent对话服务
 */
@Service
@AllArgsConstructor
public class AiAgentChatService {
    private final AgentFactory agentFactory;
    private final AiCapabilityChatService chatService;
    private final IAiChatSessionService sessionService;
    private final IAiAgentService agentService;
    private final AiUlponProperties properties;

    @Transactional
    public AiChatVo chat(AiAgentChatBo bo) {
        return AiResUtils.streamToObj(stream(bo));
    }

    public Flux<AiChatVo> stream(AiAgentChatBo bo) {
        Mono<AiChatVo> titleStream = Mono.empty();
        if (bo.getSessionId() == null) {
            AiChatSession chatSession = new AiChatSession(IdGeneratorUtil.nextLongId(), "标题生成中...", bo.getAgentId(), 1L, false);
            boolean save = sessionService.save(chatSession);
            if (save) bo.setSessionId(chatSession.getSessionId());
            else throw new AiException("会话创建失败");
            titleStream = Mono.fromCallable(() -> {
                String title = chatService.chat(properties.getGenerateTitleAiCapabilityId(), bo.getUserMsg());
                chatSession.setSessionName(title);
                sessionService.updateById(chatSession);
                return new AiChatVo(bo.getSessionId(), MsgTypeEnum.TITLE, title);
            }).subscribeOn(Schedulers.boundedElastic());
        }
        AiAgent agentConfig = agentService.getById(bo.getAgentId());
        DynamicAgent agent = agentFactory.getAgent(agentConfig, DynamicAgent.class);
        UserMessage userMessage = UserMessage.from(TextContent.from(bo.getUserMsg()));
        TokenStream stream = agent.stream(bo.getSessionId().toString(), userMessage);
        Flux<AiChatVo> chatStream = Flux.create(sink ->
            stream
                .onPartialThinking(t -> sink.next(new AiChatVo(bo.getSessionId(), MsgTypeEnum.THINK, t.text())))
                .onPartialResponse(t -> sink.next(new AiChatVo(bo.getSessionId(), MsgTypeEnum.CONTENT, t)))
                .onCompleteResponse(t -> sink.complete())
                .onError(sink::error)
                .start());
        return Flux.merge(chatStream, titleStream);
    }
}
