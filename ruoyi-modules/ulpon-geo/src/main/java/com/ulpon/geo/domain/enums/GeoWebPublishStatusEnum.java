package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO官网发布状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoWebPublishStatusEnum {

    DIRECT_PUBLISH("0", "直接发布"),
    SAVE_DRAFT("1", "存为草稿");

    private final String code;
    private final String info;
}
