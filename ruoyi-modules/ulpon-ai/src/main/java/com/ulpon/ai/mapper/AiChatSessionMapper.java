package com.ulpon.ai.mapper;

import com.ulpon.ai.domain.AiChatSession;
import com.ulpon.ai.domain.vo.AiChatSessionVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 对话会话Mapper接口
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Mapper
public interface AiChatSessionMapper extends BaseMapperPlus<AiChatSession, AiChatSessionVo> {

}
