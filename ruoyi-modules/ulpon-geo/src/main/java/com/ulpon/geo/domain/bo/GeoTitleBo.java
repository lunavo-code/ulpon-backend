package com.ulpon.geo.domain.bo;

import com.ulpon.geo.domain.GeoTitle;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * GEO标题生成业务对象 geo_title
 *
 * @author Ulpon
 * @date 2026-08-09 17:39:11
 */
@Data
@AutoMapper(target = GeoTitle.class, reverseConvertGenerate = false)
public class GeoTitleBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 关键词id
     */
    @NotNull(message = "关键词id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long keywordId;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * 收录状态
     */
//    @NotBlank(message = "收录状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String included;

    /**
     * 状态
     */
    private Boolean status;


}
