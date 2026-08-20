package com.ulpon.geo.domain.bo;

import com.ulpon.geo.domain.GeoKeyword;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 核心词管理业务对象 geo_keyword
 *
 * @author Ulpon
 * @date 2026-08-20 18:03:06
 */
@Data
@AutoMapper(target = GeoKeyword.class, reverseConvertGenerate = false)
public class GeoKeywordBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long keywordId;

    /**
     * 公司/品牌名称
     */
    @NotBlank(message = "公司/品牌名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String companyName;

    /**
     * 关键词分类
     */
    @NotBlank(message = "关键词分类不能为空", groups = { AddGroup.class, EditGroup.class })
    private String keywordType;

    /**
     * 核心关键词
     */
    @NotBlank(message = "核心关键词不能为空", groups = { AddGroup.class, EditGroup.class })
    private String keyword;

    /**
     * 状态(0启用 1停用)
     */
    @NotBlank(message = "状态(0启用 1停用)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 备注
     */
    private String remark;


}
