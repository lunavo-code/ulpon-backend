package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO单篇复刻明细状态 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoReplicateTaskStatusEnum {

    CRAWLING("0", "抓取中"),
    PARSING("1", "解析中"),
    REWRITING("2", "重写中"),
    SUCCESS("3", "成功"),
    FAILED("4", "失败");

    private final String code;
    private final String info;
}
