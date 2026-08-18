package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO自学习模式 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoSyncModeEnum {

    CRAWLER("1", "传统爬虫抓取"),
    API_PULL("2", "API定时拉取"),
    WEBHOOK("3", "Webhook被动接收");

    private final String code;
    private final String info;
}
