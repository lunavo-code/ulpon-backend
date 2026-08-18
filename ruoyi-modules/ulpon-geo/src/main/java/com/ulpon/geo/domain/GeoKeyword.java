package com.ulpon.geo.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 核心关键词对象 geo_keyword
 *
 * @author Ulpon
 * @date 2026-08-09 12:53:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("geo_keyword")
public class GeoKeyword extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 达标命中
     */
    private String hit;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private Boolean delFlag;


}
