package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO自媒体账号状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoMediaAccountStatusEnum {

    ALIVE("0", "登录存活"),
    DISABLE("1", "停用"),
    EXPIRED("2", "授权过期需重签");

    private final String code;
    private final String info;
}
