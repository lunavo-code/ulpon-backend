package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO财务账单分类 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoBillTypeEnum {

    RECHARGE("1", "在线充值"),
    WRITE_CONSUME("2", "AI写作消费"),
    MEDIA_CONSUME("3", "外链投放消费"),
    REFUND("4", "驳回退款");

    private final String code;
    private final String info;
}
