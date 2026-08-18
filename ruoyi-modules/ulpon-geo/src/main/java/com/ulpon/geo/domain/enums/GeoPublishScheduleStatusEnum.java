package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO发布执行状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoPublishScheduleStatusEnum {

    PENDING("0", "待发布"),
    PUBLISHING("1", "发布中"),
    SUCCESS("2", "成功"),
    FAILED("3", "失败");

    private final String code;
    private final String info;
}
