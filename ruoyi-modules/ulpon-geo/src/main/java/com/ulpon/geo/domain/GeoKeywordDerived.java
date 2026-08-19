package com.ulpon.geo.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 派生标题管理对象 geo_keyword_derived
 *
 * @author Ulpon
 * @date 2026-08-20 06:16:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("geo_keyword_derived")
public class GeoKeywordDerived extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "derived_id")
    private Long derivedId;

    /**
     * 关联核心词ID
     */
    private Long keywordId;

    /**
     * 标题类型
     */
    private String titleType;

    /**
     * 生成的文章标题/用户提问内容
     */
    private String derivedQuestion;

    /**
     * 状态(0待创作 1已创作 2已禁用)
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
