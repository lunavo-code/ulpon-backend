package org.dromara.gen.service;

import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import cn.hutool.extra.template.engine.freemarker.FreemarkerEngine;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.gen.domain.GenTemplate;
import org.dromara.gen.mapper.GenTemplateMapper;
import org.dromara.gen.util.template.DBTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenTemplateServiceImpl extends ServiceImpl<GenTemplateMapper, GenTemplate> implements IGenTemplateService {

    private final GenTemplateMapper genTemplateMapper;

    @Override
    public List<DBTemplate> getTemplateList(String tplCategory, String dsName, String frontendType) {

        // 模板引擎初始化
        TemplateConfig templateConfig = new TemplateConfig(StandardCharsets.UTF_8, StringUtils.EMPTY, TemplateConfig.ResourceMode.CLASSPATH);
        templateConfig.setCustomEngine(FreemarkerEngine.class);
        TemplateEngine engine = TemplateUtil.createEngine(templateConfig);
        // 禁用数字千分位格式化 避免 Long 类型的 ID 渲染是被加上逗号
        if (engine instanceof FreemarkerEngine freemarkerEngine) {
            freemarkerEngine.getConfiguration().setNumberFormat("computer");
        }

        List<GenTemplate> templateList = genTemplateMapper.selectList();
        return templateList.stream().map(genTemplate -> {
            return new DBTemplate(genTemplate.getName(), engine, genTemplate.getContent())
        }).toList();
    }
}
