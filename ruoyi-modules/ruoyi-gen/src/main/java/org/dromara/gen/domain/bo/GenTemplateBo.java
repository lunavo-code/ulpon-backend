package org.dromara.gen.domain.bo;

import org.dromara.gen.domain.GenTemplate;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 代码生成模板业务对象 gen_template
 *
 * @author Ulpon
 * @date 2026-08-28 14:02:26
 */
@Data
@AutoMapper(target = GenTemplate.class, reverseConvertGenerate = false)
public class GenTemplateBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 模板类型: backend,frontend-vue,frontend-react,sql
     */
    @NotBlank(message = "模板类型: backend,frontend-vue,frontend-react,sql不能为空", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     * 生成路径
     */
    @NotBlank(message = "生成路径不能为空", groups = { AddGroup.class, EditGroup.class })
    private String path;

    /**
     * 模板名称
     */
    @NotBlank(message = "模板名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 模板内容
     */
    @NotBlank(message = "模板内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;

    /**
     * 排序
     */
    private Integer sort;


}
