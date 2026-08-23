package com.ulpon.ai.mapper;

import com.ulpon.ai.domain.AiCapabilityTask;
import com.ulpon.ai.domain.vo.AiCapabilityTaskVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * AI能力任务Mapper接口
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
@Mapper
public interface AiCapabilityTaskMapper extends BaseMapperPlus<AiCapabilityTask, AiCapabilityTaskVo> {

}
