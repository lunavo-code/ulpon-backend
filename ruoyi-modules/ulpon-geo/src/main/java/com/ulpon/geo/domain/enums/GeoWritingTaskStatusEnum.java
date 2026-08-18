package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO写作任务状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoWritingTaskStatusEnum {

    PENDING("0", "排队中"),
    WRITING("1", "智写中"),
    COMPLETED("2", "已完成"),
    PARTIAL_FAIL("3", "部分失败"),
    PAUSED("4", "已暂停"),
    CANCELLED("5", "已取消");

    private final String code;
    private final String info;
}
