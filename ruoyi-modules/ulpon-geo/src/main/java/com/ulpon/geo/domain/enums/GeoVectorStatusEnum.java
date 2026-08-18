package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 向量同步状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoVectorStatusEnum {

    SYNCED("0", "已同步"),
    UNSYNCED("1", "未同步");

    private final String code;
    private final String info;
}
