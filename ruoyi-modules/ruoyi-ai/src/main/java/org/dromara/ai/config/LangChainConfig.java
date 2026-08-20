package org.dromara.ai.config;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@AutoConfiguration
@ConditionalOnProperty(prefix = "lang-chain", name = "enabled", havingValue = "false")
@Slf4j
@AllArgsConstructor
public class LangChainConfig {



    @PostConstruct
    public void loadChatModel() {

    }

    public static void main(String[] args) {
        OpenAiChatModel build = OpenAiChatModel.builder()
            .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
            .apiKey("sk-fdf746a2900b48c38da6c78794d9e692")
            .modelName("qwen3.7-flash")
            .build();
//        build.chat(SystemMessage.from(""))
        ChatResponse res = build.chat(SystemMessage.from(""), UserMessage.from("你好，你是谁"));
        log.info(res.aiMessage().text());
    }
}
