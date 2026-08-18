package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO诊断状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoDiagStatusEnum {

    PENDING("0", "排队中"),
    DIAGNOSING("1", "诊断中"),
    COMPLETED("2", "已完成"),
    FAILED("3", "失败");

    private final String code;
    private final String info;
}
