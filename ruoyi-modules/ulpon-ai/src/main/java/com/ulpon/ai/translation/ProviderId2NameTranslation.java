package com.ulpon.ai.translation;

import cn.hutool.core.convert.Convert;
import com.ulpon.ai.constant.AiTranslationConstant;
import com.ulpon.ai.domain.vo.AiModelProviderVo;
import com.ulpon.ai.service.IAiModelProviderService;
import lombok.AllArgsConstructor;
import org.dromara.common.translation.annotation.TranslationType;
import org.dromara.common.translation.core.TranslationInterface;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
@TranslationType(type = AiTranslationConstant.MODEL_PROVIDER_ID_2_NAME)
public class ProviderId2NameTranslation implements TranslationInterface<String> {
    private final IAiModelProviderService baseService;

    @Override
    public String translation(Object key, String other) {
        if(key instanceof Long id){
            AiModelProviderVo res = baseService.queryById(id);
            return res == null ? null : res.getProviderName();
        }else if(key instanceof String ids) {
            Map<Long, AiModelProviderVo> map = baseService.queryMapByIds(parseLongIds(ids));
            return joinMappedValues(ids, map::get);
        }
        return "";
    }

    @Override
    public Map<Object, String> translationBatch(Set<Object> keys, String other) {
        Set<Long> ids = collectLongIds(keys);
        if (ids.isEmpty()) return Map.of();
        Map<Long, AiModelProviderVo> map = baseService.queryMapByIds(ids);
        Map<Object, String> result = new LinkedHashMap<>(keys.size());
        for (Object key : keys) {
            result.put(key, buildValue(key, map));
        }
        return result;
    }

    private String buildValue(Object source, Map<Long, AiModelProviderVo> map) {
        if (source instanceof String ids) return joinMappedValues(ids, l -> map.get(l).getProviderName());
        return source == null ? null : map.get(Convert.toLong(source)).getProviderName();
    }
}
