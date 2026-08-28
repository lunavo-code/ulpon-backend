package org.dromara.gen.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 代码生成模板对象 gen_template
 *
 * @author Ulpon
 * @date 2026-08-28 14:02:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gen_template")
@AllArgsConstructor
@NoArgsConstructor
public class GenTemplate extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 版本
     */
    @Version
    private String version;

    /**
     * 模板类型: backend,frontend-vue,frontend-react,sql
     */
    private String type;

    /**
     * 生成路径
     */
    private String path;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 模板内容
     */
    private String content;

    /**
     * 排序
     */
    private Integer sort;


}
