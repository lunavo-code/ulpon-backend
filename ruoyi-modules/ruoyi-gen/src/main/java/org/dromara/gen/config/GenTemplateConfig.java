package org.dromara.gen.config;

import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import cn.hutool.extra.template.engine.freemarker.FreemarkerEngine;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.gen.constant.GenConstants;
import org.dromara.gen.enums.TemplateCategoryEnum;
import org.dromara.gen.util.template.BaseTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class GenTemplateConfig {

    @Bean
    public TemplateEngine templateEngine() {
        // 模板引擎初始化
        TemplateConfig templateConfig = new TemplateConfig(StandardCharsets.UTF_8, StringUtils.EMPTY, TemplateConfig.ResourceMode.CLASSPATH);
        templateConfig.setCustomEngine(FreemarkerEngine.class);
        TemplateEngine templateEngine = TemplateUtil.createEngine(templateConfig);
        // 禁用数字千分位格式化 避免 Long 类型的 ID 渲染是被加上逗号
        if (templateEngine instanceof FreemarkerEngine freemarkerEngine) {
            freemarkerEngine.getConfiguration().setNumberFormat("computer");
        }
        return templateEngine;
    }

    @Bean
    public Map<TemplateCategoryEnum, List<BaseTemplate>> templateMapperCacheAll(TemplateEngine templateEngine) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        return Arrays.stream(TemplateCategoryEnum.values())
            .collect(Collectors.toMap(
                type -> type,
                type -> templateMap(templateEngine, resolver, type.toString())
            ));
    }

    public List<BaseTemplate> templateMap(TemplateEngine templateEngine, ResourcePatternResolver resolver, String d) {
        try {
            String templateRootPath = GenConstants.TEMPLATE_ROOT_PATH;
            String pathPreStr = templateRootPath + "/" + d;
            String locationPattern = "classpath*:/%s/%s/**/*.ftl".formatted(templateRootPath, d);
            Resource[] resources = resolver.getResources(locationPattern);
            return Arrays.stream(resources).map(resource -> {
                try {
                    String path = resource.getURL().toString();
                    int i = path.indexOf(templateRootPath);
                    String pathname = path.substring(i);
                    return i > 0 ? BaseTemplate.form(templateEngine, pathname, pathPreStr) : null;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
