package com.ulpon.geo.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 核心词管理对象 geo_keyword
 *
 * @author Ulpon
 * @date 2026-08-20 18:03:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("geo_keyword")
public class GeoKeyword extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "keyword_id")
    private Long keywordId;

    /**
     * 公司/品牌名称
     */
    private String companyName;

    /**
     * 关键词分类
     */
    private String keywordType;

    /**
     * 核心关键词
     */
    private String keyword;

    /**
     * 状态(0启用 1停用)
     */
    private String status;

    /**
     * 删除标志(0代表存在 2代表删除)
     */
    @TableLogic
    private Boolean delFlag;

    /**
     * 备注
     */
    private String remark;


}
