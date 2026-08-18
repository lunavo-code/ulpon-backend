package com.ulpon.geo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GEO诊断平台 枚举
 */
@Getter
@AllArgsConstructor
public enum GeoPlatformEnum {

    DEEPSEEK("1", "DeepSeek"),
    DOUBAO("2", "豆包"),
    KIMI("3", "Kimi"),
    YUANBAO("4", "腾讯元宝"),
    QWEN("5", "通义千问"),
    WENXIN("6", "文心一言");

    private final String code;
    private final String info;
}
