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
 * 核心关键词业务对象 geo_keyword
 *
 * @author Ulpon
 * @date 2026-08-09 12:53:08
 */
@Data
@AutoMapper(target = GeoKeyword.class, reverseConvertGenerate = false)
public class GeoKeywordBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 关键词
     */
    @NotBlank(message = "关键词不能为空", groups = { AddGroup.class, EditGroup.class })
    private String keyword;

    /**
     * 达标命中
     */
    @NotBlank(message = "达标命中不能为空", groups = { AddGroup.class, EditGroup.class })
    private String hit;

    /**
     * 状态
     */
    private Boolean status;


}
