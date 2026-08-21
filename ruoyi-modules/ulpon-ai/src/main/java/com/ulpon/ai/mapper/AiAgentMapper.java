package com.ulpon.ai.mapper;

import com.ulpon.ai.domain.AiAgent;
import com.ulpon.ai.domain.vo.AiAgentVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 智能体Mapper接口
 *
 * @author Ulpon
 * @date 2026-08-21 12:08:12
 */
@Mapper
public interface AiAgentMapper extends BaseMapperPlus<AiAgent, AiAgentVo> {

}
