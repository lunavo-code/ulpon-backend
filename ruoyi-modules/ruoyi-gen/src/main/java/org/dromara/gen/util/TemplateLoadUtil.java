package org.dromara.gen.util;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.gen.domain.GenTemplate;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

public class TemplateLoadUtil {
    public enum CodeType {
        JAVA, VUE, TS, JS, SQL, JSON
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CodeInfo {
        private String filePath;
        private CodeType codeType;
        private String content;
    }

    public static List<CodeInfo> loadTemplateMap(Object varObj, List<GenTemplate> templateList) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
//        configuration.setInterpolationSyntax(Configuration.SQUARE_BRACKET_INTERPOLATION_SYNTAX);
        StringTemplateLoader loader = new StringTemplateLoader();
        configuration.setTemplateLoader(loader);
        templateList.forEach(i -> loader.putTemplate(i.getId().toString(), i.getContent()));
        return templateList.stream().map(i -> {
                try {
                    Template template = configuration.getTemplate(i.getId().toString());
                    StringWriter stringWriter = new StringWriter();
                    template.process(varObj, stringWriter);
                    return stringWriter.toString();
                } catch (IOException | TemplateException e) {
                    throw new RuntimeException(e);
                }
            })
            .map(s -> new CodeInfo("", CodeType.JAVA, s)).toList();
    }

    public static void main(String[] args) {
        String content = """
                package                 org.dromara.gen.util;
                public class CodeInfo {
                                                private String filePath;
                    private CodeType codeType;
                                    private String content;
                }
            """;

        GenTemplate genTemplate = new GenTemplate(100L, "1.0", "vue", "/src/test", "abcde", content, 0);

        List<CodeInfo> codeInfos = loadTemplateMap(Map.of("visible", "测试功能"), List.of(genTemplate));

        codeInfos.forEach(c -> {
            System.out.println(c.content);
            System.out.println(c.content);
        });
    }
}
