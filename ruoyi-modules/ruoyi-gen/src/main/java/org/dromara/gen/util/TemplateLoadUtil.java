package org.dromara.gen.util;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.gen.domain.GenTemplate;
import org.dromara.gen.enums.TemplateTypeEnum;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class TemplateLoadUtil {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CodeInfo {
        private String filePath;
        private TemplateTypeEnum codeType;
        private String content;
    }

    public static CodeInfo loadTemplateMap(Object varObj, GenTemplate template) throws TemplateException, IOException {
        return loadTemplateMap(varObj, List.of(template)).getFirst();
    }

    public static List<CodeInfo> loadTemplateMap(Object varObj, List<GenTemplate> templateList) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
//        configuration.setInterpolationSyntax(Configuration.SQUARE_BRACKET_INTERPOLATION_SYNTAX);
        StringTemplateLoader loader = new StringTemplateLoader();
        configuration.setTemplateLoader(loader);

        ArrayList<CodeInfo> codeInfos = new ArrayList<>();
        for (GenTemplate i : templateList) {
            String contentKey = i.getId().toString() + "-content";
            loader.putTemplate(contentKey, i.getContent());
            Template contentTemplate = configuration.getTemplate(contentKey);
            StringWriter contentWriter = new StringWriter();
            contentTemplate.process(varObj, contentWriter);

            String pathKey = i.getId().toString() + "-path";
            loader.putTemplate(pathKey, i.getPath());
            Template pathTemplate = configuration.getTemplate(pathKey);
            StringWriter pathWriter = new StringWriter();
            pathTemplate.process(varObj, pathWriter);

            codeInfos.add(new CodeInfo(pathWriter.toString(), i.getType(), contentWriter.toString()));
        }
        return codeInfos;
    }
}
