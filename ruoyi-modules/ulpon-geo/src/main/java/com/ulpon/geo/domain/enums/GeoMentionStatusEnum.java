package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO品牌提及状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoMentionStatusEnum {

    UNMENTIONED("0", "未提及"),
    POSITIVE("1", "褒义"),
    NEUTRAL("2", "中性"),
    NEGATIVE("3", "贬义");

    private final String code;
    private final String info;
}
