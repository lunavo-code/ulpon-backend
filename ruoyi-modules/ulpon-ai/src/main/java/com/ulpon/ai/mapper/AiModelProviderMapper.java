package com.ulpon.ai.mapper;

import com.ulpon.ai.domain.AiModelProvider;
import com.ulpon.ai.domain.vo.AiModelProviderVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 大模型提供商Mapper接口
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Mapper
public interface AiModelProviderMapper extends BaseMapperPlus<AiModelProvider, AiModelProviderVo> {

}
