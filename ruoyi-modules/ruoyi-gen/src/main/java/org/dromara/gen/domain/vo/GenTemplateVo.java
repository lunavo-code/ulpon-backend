package org.dromara.gen.domain.vo;

import org.dromara.gen.domain.GenTemplate;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 代码生成模板视图对象 gen_template
 *
 * @author Ulpon
 * @date 2026-08-28 14:02:26
 */
@Data
//@ExcelIgnoreUnannotated
@AutoMapper(target = GenTemplate.class)
public class GenTemplateVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 模板类型: backend,frontend-vue,frontend-react,sql
     */
    @ExcelProperty(value = "模板类型: backend,frontend-vue,frontend-react,sql")
    private String type;

    /**
     * 生成路径
     */
    @ExcelProperty(value = "生成路径")
    private String path;

    /**
     * 模板名称
     */
    @ExcelProperty(value = "模板名称")
    private String name;

    /**
     * 模板内容
     */
    @ExcelProperty(value = "模板内容")
    private String content;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Integer sort;


}
