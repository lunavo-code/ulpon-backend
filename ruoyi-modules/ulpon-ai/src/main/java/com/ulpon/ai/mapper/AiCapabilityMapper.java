package com.ulpon.ai.mapper;

import com.ulpon.ai.domain.AiCapability;
import com.ulpon.ai.domain.vo.AiCapabilityVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * AI能力Mapper接口
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
@Mapper
public interface AiCapabilityMapper extends BaseMapperPlus<AiCapability, AiCapabilityVo> {

}
