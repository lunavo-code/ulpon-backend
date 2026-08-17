package org.dromara.gen.util.template;

import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateEngine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dromara.common.core.exception.ServiceException;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@NoArgsConstructor
public class BaseTemplate implements Template {
    @Getter
    private String pathName;
    private String pathPreStr;
    @Getter
    @Setter
    private String exportFilePath;
    private Template delegate;

    public static BaseTemplate form(String pathName, String pathPreStr, Template delegate) {
        return new BaseTemplate(pathName, pathPreStr, null, delegate);
    }

    public static BaseTemplate form(TemplateEngine templateEngine, String pathName, String pathPreStr) {
        return new BaseTemplate(pathName, pathPreStr, null, templateEngine.getTemplate(pathName));
    }

//    public static Map<String, BaseTemplate> form(TemplateEngine templateEngine, Map<String, String> pathNameAndExportFilePathMap) {
//        return pathNameAndExportFilePathMap.entrySet().stream().map(entity -> {
//            String pathName = entity.getKey();
//            String exportFilePath = entity.getValue();
//            return BaseTemplate.form(templateEngine, pathName, exportFilePath);
//        }).collect(Collectors.toMap(BaseTemplate::getPathName, template -> template));
//    }

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
