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
    private static final EnumInfo NULL_RES = new EnumInfo();
    private final Map<String, Map<String, EnumInfo>> enumMap = new HashMap<>();

    public Map<String, EnumInfo> get(String model, List<String> keys) {
        Map<String, EnumInfo> infoMap = enumMap.get(model);
        return keys.stream().collect(Collectors.toMap(
            key -> key,
            key -> infoMap == null ? NULL_RES : infoMap.getOrDefault(key, NULL_RES))
        );
    }

    @Override
    public void afterPropertiesSet() {
        log.info("扫描交互枚举");
        Map<String, Map<String, EnumInfo>> map = packageList.stream()
            .flatMap(packageName -> ClassUtil.scanPackage(packageName).stream())
            .filter(Class::isEnum)
            .filter(clazz -> clazz.isAnnotationPresent(EnumName.class))
            .filter(BaseEnum.class::isAssignableFrom)
            .map(this::toEnumInfo)
            .collect(Collectors.groupingBy(
                EnumInfo::getModel,
                Collectors.toMap(
                    EnumInfo::getKey,
                    info -> info,
                    (a, b) -> {
                        throw new BaseException("相同模块中不可定义重名枚举 %s:%s，%s, %s".formatted(a.getModel(), a.getKey(), a.getClassPath(), b.getClassPath()));
                    }
                )
            ));
        enumMap.putAll(map);
    }

    private EnumInfo toEnumInfo(Class<?> clazz) {
        EnumName annotation = clazz.getAnnotation(EnumName.class);
        List<EnumInfo.KV> values = Arrays.stream(clazz.getEnumConstants())
            .map(BaseEnum.class::cast)
            .map(e -> new EnumInfo.KV(e.getCode(), e.getDesc()))
            .toList();
        return new EnumInfo(annotation.modelName(), clazz.getName(), clazz.getSimpleName(), annotation.name(), annotation.desc(), values);
    }
}
