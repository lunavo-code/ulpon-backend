package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO爆文复刻批次状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoReplicateBatchStatusEnum {

    PENDING("0", "排队中"),
    EXECUTING("1", "执行中"),
    COMPLETED("2", "已完成"),
    PARTIAL_FAIL("3", "部分失败"),
    PAUSED("4", "已暂停"),
    CANCELLED("5", "已取消");

    private final String code;
    private final String info;
}
