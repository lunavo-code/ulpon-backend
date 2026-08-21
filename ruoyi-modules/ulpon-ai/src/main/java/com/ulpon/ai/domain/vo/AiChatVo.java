package com.ulpon.ai.domain.vo;

import dev.langchain4j.data.message.ChatMessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AiChatVo {
    private Long sId; // sessionId
    private String t; // 消息类型
    private String c; // 消息内容
}
