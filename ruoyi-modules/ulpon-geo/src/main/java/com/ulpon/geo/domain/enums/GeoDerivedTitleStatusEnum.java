package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO派生标题状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoDerivedTitleStatusEnum {

    PENDING("0", "待创作"),
    CREATED("1", "已创作"),
    DISABLED("2", "已禁用");

    private final String code;
    private final String info;
}
