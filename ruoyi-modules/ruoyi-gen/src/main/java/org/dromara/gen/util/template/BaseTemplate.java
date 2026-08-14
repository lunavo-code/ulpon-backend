package org.dromara.gen.util.template;

import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateEngine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class BaseTemplate implements Template {
    @Getter
    private String pathName;
    private Template delegate;

    public static BaseTemplate form(String pathName, Template delegate) {
        return new BaseTemplate(pathName, delegate);
    }

    public static BaseTemplate form(TemplateEngine templateEngine, String pathName) {
        return new BaseTemplate(pathName, templateEngine.getTemplate(pathName));
    }

    public static Map<String, BaseTemplate> form(TemplateEngine templateEngine, Set<String> pathNames) {
        return pathNames.stream().map(pathName ->
            BaseTemplate.form(templateEngine, pathName)
        ).collect(Collectors.toMap(BaseTemplate::getPathName, template -> template));
    }

    @Override
    public void render(Map<?, ?> bindingMap, Writer writer) {
        delegate.render(bindingMap, writer);
    }

    @Override
    public void render(Map<?, ?> bindingMap, OutputStream out) {
        delegate.render(bindingMap, out);
    }

    @Override
    public void render(Map<?, ?> bindingMap, File file) {
        delegate.render(bindingMap, file);
    }

    @Override
    public String render(Map<?, ?> bindingMap) {
        return delegate.render(bindingMap);
    }
}
