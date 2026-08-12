package org.dromara.gen.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.gen.domain.GenTemplate;

/**
 * 业务 数据层
 *
 * @author Ulpon
 */
@Mapper
@InterceptorIgnore(dataPermission = "true", tenantLine = "true")
public interface GenTemplateMapper extends BaseMapperPlus<GenTemplate, GenTemplate> {
}
