package org.dromara.common.core.enums.common;

import cn.hutool.core.util.ClassUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnumScanner implements InitializingBean {
    private static final List<String> packageList = List.of("org.dromara", "com.ulpon");
    private static final Map<String, EnumInfo> enumMap = new HashMap<>();

    private static final EnumInfo NULL_RES = new EnumInfo();

    private String buildKey(String model, String key) {
        return "%s:%s".formatted(model, key);
    }

    public Map<String, EnumInfo> get(String model, List<String> keys) {
        Map<String, EnumInfo> infoMap = enumMap.get(model);
        return keys.stream().collect(Collectors.toMap(
            key -> buildKey(model, key),
            key -> infoMap == null ? NULL_RES : infoMap.get(key))
        );
    }

    @Override
    public void afterPropertiesSet() {
        log.info("扫描交互枚举");
        packageList.stream().flatMap(packageName -> ClassUtil.scanPackage(packageName).stream())
            .filter(Class::isEnum)
            .filter(clazz -> clazz.isAnnotationPresent(EnumName.class))
            .filter(BaseEnum.class::isAssignableFrom)
            .forEach(clazz -> {
                EnumName annotation = clazz.getAnnotation(EnumName.class);
                String modelName = annotation.modelName();
                String name = annotation.name();
                String desc = annotation.desc();
                enumMap.get(buildKey(modelName, ))


                Map<String, EnumInfo> orDefault = enumMap.getOrDefault(modelName, new HashMap<>());
                enumMap.put(modelName, orDefault);
                String simpleName = clazz.getSimpleName();
                if (orDefault.containsKey(simpleName)) {
                    throw new RuntimeException("枚举名称重复");
                }
                log.info("load enum: " + clazz.getName());
                List<EnumInfo.KV> values = Arrays.stream(clazz.getEnumConstants()).map(obj -> (BaseEnum) obj)
                    .map(be -> new EnumInfo.KV(be.getCode(), be.getDesc()))
                    .toList();
                orDefault.put(simpleName, new EnumInfo(modelName, simpleName, name, desc, values));
            });
    }
}
