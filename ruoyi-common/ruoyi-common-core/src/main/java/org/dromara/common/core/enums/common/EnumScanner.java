package org.dromara.common.core.enums.common;

import cn.hutool.core.util.ClassUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.exception.base.BaseException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnumScanner implements InitializingBean {
    private static final List<String> packageList = List.of("org.dromara", "com.ulpon");
    //    private static final EnumDefinition.EnumInfo NULL_RES = new EnumDefinition.EnumInfo();
    private final Map<String, Map<String, EnumDefinition.EnumInfo>> enumMap = new HashMap<>();

    public List<Map<String, String>> getCatalog() {
        return enumMap.entrySet().stream()
            .flatMap(e -> e.getValue().values().stream().map(value -> {
                String model = value.getModel();
                String name = value.getName();
                String key = value.getKey();
                return Map.of(
                    "enumName", name,
                    "enumType", model + ":" + key
                );
            })).toList();
    }

    public List<EnumDefinition.EnumInfo.KV> getEnums(String model, String enumType) {
        Map<String, EnumDefinition.EnumInfo> infoMap = enumMap.get(model);
        if (infoMap == null) {
            return List.of();
        }
        return infoMap.get(enumType).getValues();
    }

    @Override
    public void afterPropertiesSet() {
        log.info("扫描交互枚举");
        Map<String, Map<String, EnumDefinition.EnumInfo>> map = packageList.stream()
            .flatMap(packageName -> ClassUtil.scanPackage(packageName).stream())
            .filter(Class::isEnum)
            .filter(clazz -> clazz.isAnnotationPresent(EnumName.class))
            .filter(EnumDefinition.class::isAssignableFrom)
            .map(this::toEnumInfo)
            .collect(Collectors.groupingBy(
                EnumDefinition.EnumInfo::getModel,
                Collectors.toMap(
                    EnumDefinition.EnumInfo::getKey,
                    info -> info,
                    (a, b) -> {
                        throw new BaseException("相同模块中不可定义重名枚举 %s:%s，%s, %s".formatted(a.getModel(), a.getKey(), a.getClassPath(), b.getClassPath()));
                    }
                )
            ));
        enumMap.putAll(map);
    }

    private EnumDefinition.EnumInfo toEnumInfo(Class<?> clazz) {
        EnumName annotation = clazz.getAnnotation(EnumName.class);
        List<EnumDefinition.EnumInfo.KV> values = Arrays.stream(clazz.getEnumConstants())
            .map(EnumDefinition.class::cast)
            .map(e -> new EnumDefinition.EnumInfo.KV(e.getCode(), e.getLabel(), e.getDesc()))
            .toList();
        return new EnumDefinition.EnumInfo(annotation.modelName(), clazz.getName(), clazz.getSimpleName(), annotation.name(), annotation.desc(), values);
    }
}
