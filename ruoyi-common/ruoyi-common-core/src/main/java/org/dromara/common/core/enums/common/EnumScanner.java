package org.dromara.common.core.enums.common;

import cn.hutool.core.util.ClassUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EnumScanner implements InitializingBean {
    private static final List<String> packageList = List.of("org.dromara", "com.ulpon");
    private static final Map<String, Map<String, EnumInfo>> enumMap = new HashMap<>();
    private Map<String, Collection<EnumInfo>> collect;

    public Map<String, Collection<EnumInfo>> getAll() {
        return collect;
    }

    public Collection<EnumInfo> getList(String model) {
        Map<String, EnumInfo> infoMap = enumMap.get(model);
        return infoMap == null ? null : infoMap.values();
    }

    public EnumInfo get(String model, String key) {
        Map<String, EnumInfo> infoMap = enumMap.get(model);
        return infoMap == null ? null : infoMap.get(key);
    }

    @Override
    public void afterPropertiesSet() {
        packageList.stream().flatMap(packageName -> ClassUtil.scanPackage(packageName).stream())
            .filter(Class::isEnum)
            .filter(clazz -> clazz.isAnnotationPresent(EnumName.class))
            .filter(BaseEnum.class::isAssignableFrom)
            .forEach(clazz -> {
                EnumName annotation = clazz.getAnnotation(EnumName.class);
                String modelName = annotation.modelName();
                String name = annotation.name();
                String desc = annotation.desc();
                Map<String, EnumInfo> orDefault = enumMap.getOrDefault(modelName, new HashMap<>());
                enumMap.put(modelName, orDefault);
                String simpleName = clazz.getSimpleName();
                if (orDefault.containsKey(simpleName)) {
                    throw new RuntimeException("枚举名称重复");
                }
                List<EnumInfo.KV> values = Arrays.stream(clazz.getEnumConstants()).map(obj -> (BaseEnum) obj)
                    .map(be -> new EnumInfo.KV(be.getCode(), be.getDesc()))
                    .toList();
                orDefault.put(simpleName, new EnumInfo(modelName, simpleName, name, desc, values));
            });
        collect = enumMap.entrySet().stream().collect(Collectors.toMap(
            Map.Entry::getKey,
            i -> i.getValue().values()
        ));
    }
}
