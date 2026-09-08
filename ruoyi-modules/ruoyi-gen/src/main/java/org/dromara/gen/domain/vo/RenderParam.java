package org.dromara.gen.domain.vo;

import lombok.Data;

import java.util.Map;

@Data
public class RenderParam {
    private String content;
    private Map<String, Object> params;
}
