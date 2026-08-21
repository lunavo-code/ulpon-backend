package com.ulpon.ai.mapper;

import com.ulpon.ai.domain.AiModelConfig;
import com.ulpon.ai.domain.vo.AiModelConfigVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 大模型配置Mapper接口
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Mapper
public interface AiModelConfigMapper extends BaseMapperPlus<AiModelConfig, AiModelConfigVo> {

}
