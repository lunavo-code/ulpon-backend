package com.ulpon.ai.config.propertie;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ulpon.ai")
public class AiUlponProperties {
    /**
     * 生成标题能力ID
     */
    private Long generateTitleAiCapabilityId;

    /**
     * 生成智能体元数据能力ID
     */
    private Long generateAgentMetaAiCapabilityId;

}
