package com.ulpon.ai.translation;

import cn.hutool.core.convert.Convert;
import com.ulpon.ai.constant.AiTranslationConstant;
import com.ulpon.ai.domain.vo.AiModelConfigVo;
import com.ulpon.ai.service.IAiModelConfigService;
import lombok.AllArgsConstructor;
import org.dromara.common.translation.annotation.TranslationType;
import org.dromara.common.translation.core.TranslationInterface;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
@TranslationType(type = AiTranslationConstant.CHAT_MODEL_ID_2_NAME)
public class ModelId2NameTranslation implements TranslationInterface<String> {
    private final IAiModelConfigService baseService;

    @Override
    public String translation(Object key, String other) {
        if(key instanceof Long id){
            AiModelConfigVo res = baseService.queryById(id);
            return res == null ? null : res.getConfigName();
        }else if(key instanceof String ids) {
            Map<Long, AiModelConfigVo> map = baseService.queryMapByIds(parseLongIds(ids));
            return joinMappedValues(ids, map::get);
        }
        return "";
    }

    @Override
    public Map<Object, String> translationBatch(Set<Object> keys, String other) {
        Set<Long> ids = collectLongIds(keys);
        if (ids.isEmpty()) return Map.of();
        Map<Long, AiModelConfigVo> map = baseService.queryMapByIds(ids);
        Map<Object, String> result = new LinkedHashMap<>(keys.size());
        for (Object key : keys) {
            result.put(key, buildValue(key, map));
        }
        return result;
    }

    private String buildValue(Object source, Map<Long, AiModelConfigVo> map) {
        if (source instanceof String ids) return joinMappedValues(ids, l -> map.get(l).getConfigName());
        return source == null ? null : map.get(Convert.toLong(source)).getConfigName();
    }
}
