package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否建议 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoYesNoEnum {

    NO("0", "否"),
    YES("1", "是");

    private final String code;
    private final String info;
}
