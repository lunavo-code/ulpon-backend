package com.ulpon.ai.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.ulpon.ai.domain.AiChatSession;
import com.ulpon.ai.domain.vo.AiChatSessionVo;
import com.ulpon.ai.domain.bo.AiChatSessionBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 对话会话Service接口
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
public interface IAiChatSessionService extends IService<AiChatSession> {

    /**
     * 查询对话会话
     *
     * @param sessionId 主键
     * @return 对话会话
     */
    AiChatSessionVo queryById(Long sessionId);

    /**
     * 查询对话会话列表
     *
     * @param sessionIdList 主键列表
     * @return 对话会话列表
     */
    List<AiChatSessionVo> queryByIds(Collection<Long> sessionIdList);

    /**
     * 查询核心词管理map
     *
     * @param sessionIdList 主键列表
     * @return 核心词管理map
     */
    default Map<Long, AiChatSessionVo> queryMapByIds(Collection<Long> sessionIdList){
        return queryByIds(sessionIdList).stream().collect(Collectors.toMap(AiChatSessionVo::getSessionId, v -> v));
    }

    /**
     * 分页查询对话会话列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 对话会话分页列表
     */
    PageResult<AiChatSessionVo> queryPageList(AiChatSessionBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的对话会话列表
     *
     * @param bo 查询条件
     * @return 对话会话列表
     */
    List<AiChatSessionVo> queryList(AiChatSessionBo bo);


    /**
     * 新增对话会话
     *
     * @param bo 对话会话
     * @return 是否新增成功
     */
    Boolean insertByBo(AiChatSessionBo bo);

    /**
     * 修改对话会话
     *
     * @param bo 对话会话
     * @return 是否修改成功
     */
    Boolean updateByBo(AiChatSessionBo bo);



    /**
     * 校验并批量删除对话会话信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
