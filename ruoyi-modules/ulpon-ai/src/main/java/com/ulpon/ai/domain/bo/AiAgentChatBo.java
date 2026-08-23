package com.ulpon.ai.domain.bo;

import lombok.Data;

@Data
public class AiAgentChatBo {
    private Long agentId;
    private Long sessionId;
    private String userMsg;
}
