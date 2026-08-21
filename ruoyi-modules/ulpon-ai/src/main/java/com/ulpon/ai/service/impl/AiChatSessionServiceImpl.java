package com.ulpon.ai.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.query.QueryBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.ulpon.ai.domain.bo.AiChatSessionBo;
import com.ulpon.ai.domain.vo.AiChatSessionVo;
import com.ulpon.ai.domain.AiChatSession;
import com.ulpon.ai.mapper.AiChatSessionMapper;
import com.ulpon.ai.service.IAiChatSessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 对话会话Service业务层处理
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AiChatSessionServiceImpl extends ServiceImpl<AiChatSessionMapper, AiChatSession> implements IAiChatSessionService {

    private final AiChatSessionMapper aiChatSessionMapper;

    /**
     * 查询对话会话
     *
     * @param sessionId 主键
     * @return 对话会话
     */
    @Override
    public AiChatSessionVo queryById(Long sessionId) {
        return aiChatSessionMapper.selectVoById(sessionId);
    }

    /**
     * 查询对话会话列表
     *
     * @param sessionIdList 主键列表
     * @return 对话会话列表
     */
    @Override
    public List<AiChatSessionVo> queryByIds(Collection<Long> sessionIdList) {
        if (CollUtil.isEmpty(sessionIdList)) return List.of();
        return aiChatSessionMapper.selectVoByIds(sessionIdList);
    }

    /**
     * 分页查询对话会话列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 对话会话分页列表
     */
    @Override
    public PageResult<AiChatSessionVo> queryPageList(AiChatSessionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiChatSession> lqw = buildQueryWrapper(bo);
        Page<AiChatSessionVo> result = aiChatSessionMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的对话会话列表
     *
     * @param bo 查询条件
     * @return 对话会话列表
     */
    @Override
    public List<AiChatSessionVo> queryList(AiChatSessionBo bo) {
        LambdaQueryWrapper<AiChatSession> lqw = buildQueryWrapper(bo);
        return aiChatSessionMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<AiChatSession> buildQueryWrapper(AiChatSessionBo bo) {
        return QueryBuilder.lambda(AiChatSession.class)
            .likeIfText(AiChatSession::getSessionName, bo.getSessionName())
            .eqIfPresent(AiChatSession::getAgentId, bo.getAgentId())
            .eqIfPresent(AiChatSession::getUserId, bo.getUserId())
            .orderByAsc(AiChatSession::getSessionId)
            .build();
    }

    /**
     * 新增对话会话
     *
     * @param bo 对话会话
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AiChatSessionBo bo) {
        AiChatSession add = MapstructUtils.convert(bo, AiChatSession.class);
        validEntityBeforeSave(add);
        boolean flag = aiChatSessionMapper.insert(add) > 0;
        if (flag) {
            bo.setSessionId(add.getSessionId());
        }
        return flag;
    }

    /**
     * 修改对话会话
     *
     * @param bo 对话会话
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AiChatSessionBo bo) {
        AiChatSession update = MapstructUtils.convert(bo, AiChatSession.class);
        validEntityBeforeSave(update);
        return aiChatSessionMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AiChatSession entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除对话会话信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 可在此扩展删除前业务校验
        }
        return aiChatSessionMapper.deleteByIds(ids) > 0;
    }

}
