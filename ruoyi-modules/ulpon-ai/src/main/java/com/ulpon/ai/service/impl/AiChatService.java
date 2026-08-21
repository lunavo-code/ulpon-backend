package com.ulpon.ai.service.impl;

import com.ulpon.ai.agent.DynamicAgent;
import com.ulpon.ai.common.AgentFactory;
import com.ulpon.ai.domain.AiChatSession;
import com.ulpon.ai.domain.bo.AiChatBo;
import com.ulpon.ai.domain.vo.AiChatVo;
import com.ulpon.ai.exceptions.AiException;
import com.ulpon.ai.service.IAiChatSessionService;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.service.TokenStream;
import lombok.AllArgsConstructor;
import org.dromara.common.mybatis.utils.IdGeneratorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class AiChatService {
    private final AgentFactory agentFactory;
    private final IAiChatSessionService sessionService;

    @Transactional
    public AiChatVo chat(AiChatBo bo) {
        initSession(bo);
        DynamicAgent agent = agentFactory.getAgent(bo.getAId(), DynamicAgent.class);
        UserMessage userMessage = UserMessage.from(
            TextContent.from(bo.getMsg())
        );
        String string = agent.chat(bo.getSId().toString(), userMessage);
        return new AiChatVo(bo.getSId(), "text", string);
    }

    public Flux<AiChatVo> stream(AiChatBo bo) {
        initSession(bo);
        DynamicAgent agent = agentFactory.getAgent(bo.getAId(), DynamicAgent.class);
        UserMessage userMessage = UserMessage.from(
            TextContent.from(bo.getMsg())
        );
        TokenStream stream = agent.stream(bo.getSId().toString(), userMessage);
        return Flux.create(sink -> stream
            .onPartialThinking(t -> sink.next(new AiChatVo(bo.getSId(), "t", t.text())))
            .onPartialResponse(t -> sink.next(new AiChatVo(bo.getSId(), "r", t)))
            .onCompleteResponse(t -> {
                sink.next(new AiChatVo(bo.getSId(), "c", t.id()));
                sink.complete(); // 关键：告知 WebFlux 流已结束，主动关闭 HTTP 连接
            })
            .onError(t -> {
                sink.next(new AiChatVo(bo.getSId(), "e", t.getMessage()));
                sink.complete(); // 或者 sink.error(t)，以终止流
            })
            .start()
        );
    }

    private void initSession(AiChatBo bo) {
        Long sessionId = bo.getSId();
        if (sessionId == null) {
            AiChatSession chatSession = new AiChatSession(IdGeneratorUtil.nextLongId(), "标题生成中...", bo.getAId(), 1L, false);
            boolean save = sessionService.save(chatSession);
            if (save) bo.setSId(chatSession.getSessionId());
            else throw new AiException("会话创建失败");
        }
    }
}
