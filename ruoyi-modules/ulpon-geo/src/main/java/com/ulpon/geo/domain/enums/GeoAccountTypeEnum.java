package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO宣发通道类型 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoAccountTypeEnum {

    MEDIA("1", "自媒体号"),
    WEBSITE("2", "企业官方网站");

    private final String code;
    private final String info;
}
