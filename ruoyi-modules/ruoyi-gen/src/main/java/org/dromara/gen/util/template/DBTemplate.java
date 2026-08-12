package org.dromara.gen.util.template;

import cn.hutool.extra.template.Template;
import lombok.Getter;

@Getter
public class DBTemplate extends BaseTemplate {

    private final String context;

    public DBTemplate(String pathName, Template delegate, String context) {
        super();
        this.context = context;
    }

}
