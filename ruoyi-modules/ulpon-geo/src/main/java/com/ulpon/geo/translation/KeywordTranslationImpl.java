package com.ulpon.geo.translation;

import cn.hutool.core.convert.Convert;
import com.ulpon.geo.constant.GeoTranslationConstant;
import com.ulpon.geo.domain.vo.GeoKeywordVo;
import com.ulpon.geo.service.IGeoKeywordService;
import lombok.AllArgsConstructor;
import org.dromara.common.translation.annotation.TranslationType;
import org.dromara.common.translation.core.TranslationInterface;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@TranslationType(type = GeoTranslationConstant.KEYWORD_ID_2_KEYWORD)
public class KeywordTranslationImpl implements TranslationInterface<String> {

    private final IGeoKeywordService keywordService;

    @Override
    public String translation(Object key, String other) {
        if (key instanceof Long id) {
            GeoKeywordVo geoKeywordVo = keywordService.queryById(id);
            return geoKeywordVo == null ? null : geoKeywordVo.getKeyword();
        } else if (key instanceof String ids) {
            Map<Long, GeoKeywordVo> map = keywordService.queryMapByIds(parseLongIds(ids));
            return joinMappedValues(ids, map::get);
        }
        return null;
    }

    @Override
    public Map<Object, String> translationBatch(Set<Object> keys, String other) {
        Set<Long> ids = collectLongIds(keys);
        if (ids.isEmpty()) return Map.of();
        Map<Long, GeoKeywordVo> map = keywordService.queryMapByIds(ids);
        Map<Object, String> result = new LinkedHashMap<>(keys.size());
        for (Object key : keys) {
            result.put(key, buildValue(key, map));
        }
        return result;
    }

    private String buildValue(Object source, Map<Long, GeoKeywordVo> map) {
        if (source instanceof String ids) return joinMappedValues(ids, l -> map.get(l).getKeyword());
        return source == null ? null : map.get(Convert.toLong(source)).getKeyword();
    }
}
