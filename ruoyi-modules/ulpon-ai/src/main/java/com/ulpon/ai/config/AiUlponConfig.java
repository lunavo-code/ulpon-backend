package com.ulpon.ai.config;

import com.ulpon.ai.config.propertie.AiUlponProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties(AiUlponProperties.class)
public class AiUlponConfig {
    @PostConstruct
    public void init() {
        log.info("init chat model");
    }

}
