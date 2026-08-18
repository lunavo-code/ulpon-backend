package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO外链投递工单状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoMediaOrderStatusEnum {

    PENDING("0", "待提交"),
    UNDER_REVIEW("1", "审核中"),
    SUCCESS("2", "发布成功"),
    REFUNDED("3", "已驳回退款");

    private final String code;
    private final String info;
}
