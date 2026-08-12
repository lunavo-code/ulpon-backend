package org.dromara.gen.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("gen_template")
public class GenTemplate extends BaseEntity {
    private Long id;
    private String type;
    private String name;
    private String content;
    private Integer sort;
}
