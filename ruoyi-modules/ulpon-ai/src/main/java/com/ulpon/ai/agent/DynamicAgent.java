package com.ulpon.ai.agent;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface DynamicAgent {
    String chat(@MemoryId String memoryId, @UserMessage dev.langchain4j.data.message.UserMessage message);

    TokenStream stream(@MemoryId String memoryId, @UserMessage dev.langchain4j.data.message.UserMessage userMessage);
}
