package com.ulpon.geo.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * GEO标题生成对象 geo_title
 *
 * @author Ulpon
 * @date 2026-08-09 17:39:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("geo_title")
public class GeoTitle extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 关键词id
     */
    private Long keywordId;

    /**
     * 标题
     */
    private String title;

    /**
     * 收录状态
     */
    private String included;

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
