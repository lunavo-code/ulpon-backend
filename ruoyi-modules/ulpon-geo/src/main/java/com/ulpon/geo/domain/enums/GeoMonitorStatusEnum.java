package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 监控状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoMonitorStatusEnum {

    NORMAL("0", "正常"),
    DISABLE("1", "停用");

    private final String code;
    private final String info;
}
