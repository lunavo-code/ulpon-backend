package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO文章状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoArticleStatusEnum {

    DRAFT("0", "草稿/待发"),
    UNDER_REVIEW("1", "审核中"),
    PUBLISHING("2", "发布中"),
    PUBLISHED("3", "已发布"),
    PARTIAL_SUCCESS("4", "部分成功"),
    FAILED("5", "发布失败");

    private final String code;
    private final String info;
}
