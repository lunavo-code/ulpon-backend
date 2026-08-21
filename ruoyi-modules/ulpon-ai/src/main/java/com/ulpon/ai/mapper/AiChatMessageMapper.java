package com.ulpon.ai.mapper;

import com.ulpon.ai.domain.AiChatMessage;
import com.ulpon.ai.domain.vo.AiChatMessageVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 对话消息Mapper接口
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Mapper
public interface AiChatMessageMapper extends BaseMapperPlus<AiChatMessage, AiChatMessageVo> {

}
