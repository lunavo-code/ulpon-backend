package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO实名审核状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoAuthStatusEnum {

    PENDING("0", "待审核"),
    APPROVED("1", "已通过"),
    REJECTED("2", "已驳回");

    private final String code;
    private final String info;
}
