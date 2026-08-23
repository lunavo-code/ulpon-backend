package com.ulpon.ai.common;

import com.ulpon.ai.domain.AiModelConfig;
import com.ulpon.ai.exceptions.AiException;
import com.ulpon.ai.service.IAiModelConfigService;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class ModelFactory {
    private final IAiModelConfigService modelConfigService;

    private static final Map<Long, ChatModel> chatModelMap = new HashMap<>();
    private ChatModel defaultChatModel = null;

    public record ChatModel(OpenAiChatModel chatModel, OpenAiStreamingChatModel streamingChatModel) {
    }

    @PostConstruct
    private void init() {
        List<AiModelConfig> list = modelConfigService.list();
        list.forEach(config -> {
            ChatModel chatModel = buildOpenAiChatModel(config);
            chatModelMap.put(config.getModelConfigId(), chatModel);
            if(config.getIsDefault()) defaultChatModel = chatModel;
        });
    }

    public void addChatModel(AiModelConfig config) {
        chatModelMap.put(config.getModelConfigId(), buildOpenAiChatModel(config));
    }

    public ChatModel getChatModel(Long modelId) {
        return chatModelMap.get(modelId);
    }

    public ChatModel getChatModel() {
        if(defaultChatModel == null) throw new AiException("未指定默认模型，请先指定");
        return defaultChatModel;
    }


    private ChatModel buildOpenAiChatModel(AiModelConfig config) {
        Map<String, Object> customParameters = Map.of(
            "thinking", Map.of("type", "disabled")
        );
        return new ChatModel(
            OpenAiChatModel.builder()
                .modelName(config.getModelName())
                .baseUrl(config.getBaseUrl())
                .apiKey(config.getApiKey())
                .maxTokens(config.getMaxTokens())
                .temperature(config.getTemperature())
                .customParameters(customParameters)
                .build(),
            OpenAiStreamingChatModel.builder()
                .modelName(config.getModelName())
                .baseUrl(config.getBaseUrl())
                .apiKey(config.getApiKey())
                .maxTokens(config.getMaxTokens())
                .temperature(config.getTemperature())
                .customParameters(customParameters)
                .build()
        );
    }
}
