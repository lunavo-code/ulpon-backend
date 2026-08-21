package com.ulpon.ai.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.ulpon.ai.domain.AiChatMessage;
import com.ulpon.ai.domain.vo.AiChatMessageVo;
import com.ulpon.ai.domain.bo.AiChatMessageBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 对话消息Service接口
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
public interface IAiChatMessageService extends IService<AiChatMessage> {

    /**
     * 查询对话消息
     *
     * @param messageId 主键
     * @return 对话消息
     */
    AiChatMessageVo queryById(Long messageId);

    List<AiChatMessageVo> queryBySessionId(Long sessionId);

    /**
     * 查询对话消息列表
     *
     * @param messageIdList 主键列表
     * @return 对话消息列表
     */
    List<AiChatMessageVo> queryByIds(Collection<Long> messageIdList);

    /**
     * 查询核心词管理map
     *
     * @param messageIdList 主键列表
     * @return 核心词管理map
     */
    default Map<Long, AiChatMessageVo> queryMapByIds(Collection<Long> messageIdList){
        return queryByIds(messageIdList).stream().collect(Collectors.toMap(AiChatMessageVo::getMessageId, v -> v));
    }

    /**
     * 分页查询对话消息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 对话消息分页列表
     */
    PageResult<AiChatMessageVo> queryPageList(AiChatMessageBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的对话消息列表
     *
     * @param bo 查询条件
     * @return 对话消息列表
     */
    List<AiChatMessageVo> queryList(AiChatMessageBo bo);


    /**
     * 新增对话消息
     *
     * @param bo 对话消息
     * @return 是否新增成功
     */
    Boolean insertByBo(AiChatMessageBo bo);

    /**
     * 修改对话消息
     *
     * @param bo 对话消息
     * @return 是否修改成功
     */
    Boolean updateByBo(AiChatMessageBo bo);



    /**
     * 校验并批量删除对话消息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    void deleteBySessionId(Long sessionId);
}
