package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO独立站系统接口 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoCmsTypeEnum {

    WORDPRESS("1", "WordPress标准API"),
    PBOOTCMS("2", "PbootCMS专用脚本"),
    EYOUCMS("3", "EyouCMS专用脚本"),
    WEBHOOK("4", "自定义通用Webhook");

    private final String code;
    private final String info;
}
