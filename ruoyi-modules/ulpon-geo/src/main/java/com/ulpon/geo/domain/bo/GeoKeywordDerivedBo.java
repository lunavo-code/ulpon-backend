package com.ulpon.geo.domain.bo;

import com.ulpon.geo.domain.GeoKeywordDerived;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 派生标题管理业务对象 geo_keyword_derived
 *
 * @author Ulpon
 * @date 2026-08-20 06:16:32
 */
@Data
@AutoMapper(target = GeoKeywordDerived.class, reverseConvertGenerate = false)
public class GeoKeywordDerivedBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long derivedId;

    /**
     * 关联核心词ID
     */
    @NotNull(message = "关联核心词ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long keywordId;

    /**
     * 标题类型
     */
    @NotBlank(message = "标题类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String titleType;

    /**
     * 生成的文章标题/用户提问内容
     */
    @NotBlank(message = "生成的文章标题/用户提问内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String derivedQuestion;

    /**
     * 状态(0待创作 1已创作 2已禁用)
     */
    @NotBlank(message = "状态(0待创作 1已创作 2已禁用)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 备注
     */
    private String remark;


}
