package com.ulpon.ai.common;

import com.ulpon.ai.domain.AiAgent;
import com.ulpon.ai.exceptions.AiException;
import com.ulpon.ai.service.IAiAgentService;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class AgentFactory {
    private final ModelFactory modelFactory;
    private final IAiAgentService agentService;
    private final DbChatMemoryStore chatMemoryStore;

    private final Map<AgentKey, Object> agentMap = new ConcurrentHashMap<>();

    private record AgentKey(Long agentId, Class<?> type) {
    }

    public <T> T getAgent(AiAgent agent, Class<T> tClass) {
        if(agent == null) throw new AiException("agent不能为null");
        AgentKey key = new AgentKey(agent.getAgentId(), tClass);
        Object o = agentMap.computeIfAbsent(key, k -> createAgent(agent, tClass));
        return tClass.cast(o);
    }

    public <T> T getAgent(Long agentId, Class<T> tClass) {
        AiAgent agent = agentService.getById(agentId);
        if (agent == null) throw new AiException("Agent不存在: " + agentId);
        return getAgent(agent, tClass);
    }

    public void evict(Long agentId) {
        agentMap.keySet().removeIf(key -> key.agentId().equals(agentId));
    }

    private <T> T createAgent(AiAgent agent, Class<T> tClass) {
        ModelFactory.ChatModel chatModel = modelFactory.getChatModel(agent.getModelConfigId());
        AiServices<T> services = AiServices.builder(tClass)
            .chatModel(chatModel.chatModel())
            .streamingChatModel(chatModel.streamingChatModel());

        if (agent.getSystemPrompt() != null && !agent.getSystemPrompt().isBlank()) {
            services.systemMessage(agent.getSystemPrompt());
        }
        if (agent.getMemoryEnabled()) {
            int value;
            if (agent.getMemoryWindow() == null) {
                log.warn("未配置对话记忆窗口长度，使用默认长度：20");
                value = 20;
            } else {
                value = agent.getMemoryWindow();
            }
            int memoryWindow = Math.clamp(value, 1, 100);
            services
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.builder()
                    .id(memoryId)
                    .chatMemoryStore(chatMemoryStore)
                    .maxMessages(memoryWindow)
                    .build()
                );
        }
        return services.build();
    }
}
