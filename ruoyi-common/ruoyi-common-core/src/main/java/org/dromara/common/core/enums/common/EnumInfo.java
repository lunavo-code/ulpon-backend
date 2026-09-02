package org.dromara.common.core.enums.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnumInfo {
    private String model;
    @JsonIgnore
    private String classPath;
    private String key;
    private String name;
    private String desc;
    private List<KV> values;

    @Data
    @AllArgsConstructor
    public static class KV{
        private String code;
        private String label;
    }
}
