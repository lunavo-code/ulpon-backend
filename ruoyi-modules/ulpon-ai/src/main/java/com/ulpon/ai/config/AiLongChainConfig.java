package com.ulpon.ai.config;

import com.ulpon.ai.common.ModelFactory;
import com.ulpon.ai.domain.AiModelConfig;
import com.ulpon.ai.service.IAiModelConfigService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AiLongChainConfig {
    private final IAiModelConfigService modelConfigService;

    @PostConstruct
    public void init() {
        log.info("init chat model");

    }

}
