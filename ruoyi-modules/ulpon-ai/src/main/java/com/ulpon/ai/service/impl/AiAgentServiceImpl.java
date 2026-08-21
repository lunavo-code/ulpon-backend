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
import com.ulpon.ai.domain.bo.AiAgentBo;
import com.ulpon.ai.domain.vo.AiAgentVo;
import com.ulpon.ai.domain.AiAgent;
import com.ulpon.ai.mapper.AiAgentMapper;
import com.ulpon.ai.service.IAiAgentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 智能体Service业务层处理
 *
 * @author Ulpon
 * @date 2026-08-21 12:08:12
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AiAgentServiceImpl extends ServiceImpl<AiAgentMapper, AiAgent> implements IAiAgentService {

    private final AiAgentMapper aiAgentMapper;

    /**
     * 查询智能体
     *
     * @param agentId 主键
     * @return 智能体
     */
    @Override
    public AiAgentVo queryById(Long agentId) {
        return aiAgentMapper.selectVoById(agentId);
    }

    /**
     * 查询智能体列表
     *
     * @param agentIdList 主键列表
     * @return 智能体列表
     */
    @Override
    public List<AiAgentVo> queryByIds(Collection<Long> agentIdList) {
        if (CollUtil.isEmpty(agentIdList)) return List.of();
        return aiAgentMapper.selectVoByIds(agentIdList);
    }

    /**
     * 分页查询智能体列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 智能体分页列表
     */
    @Override
    public PageResult<AiAgentVo> queryPageList(AiAgentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiAgent> lqw = buildQueryWrapper(bo);
        Page<AiAgentVo> result = aiAgentMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的智能体列表
     *
     * @param bo 查询条件
     * @return 智能体列表
     */
    @Override
    public List<AiAgentVo> queryList(AiAgentBo bo) {
        LambdaQueryWrapper<AiAgent> lqw = buildQueryWrapper(bo);
        return aiAgentMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<AiAgent> buildQueryWrapper(AiAgentBo bo) {
        return QueryBuilder.lambda(AiAgent.class)
            .likeIfText(AiAgent::getAgentName, bo.getAgentName())
            .eqIfText(AiAgent::getAvatar, bo.getAvatar())
            .eqIfText(AiAgent::getDescription, bo.getDescription())
            .eqIfText(AiAgent::getSystemPrompt, bo.getSystemPrompt())
            .eqIfPresent(AiAgent::getModelConfigId, bo.getModelConfigId())
            .eqIfPresent(AiAgent::getKbEnabled, bo.getKbEnabled())
            .eqIfPresent(AiAgent::getKbId, bo.getKbId())
            .eqIfPresent(AiAgent::getSearchEnabled, bo.getSearchEnabled())
            .eqIfPresent(AiAgent::getMemoryEnabled, bo.getMemoryEnabled())
            .eqIfPresent(AiAgent::getMemoryWindow, bo.getMemoryWindow())
            .eqIfText(AiAgent::getGreeting, bo.getGreeting())
            .eqIfText(AiAgent::getPresetQuestions, bo.getPresetQuestions())
            .eqIfText(AiAgent::getScopeType, bo.getScopeType())
            .eqIfPresent(AiAgent::getStatus, bo.getStatus())
            .orderByAsc(AiAgent::getAgentId)
            .build();
    }

    /**
     * 新增智能体
     *
     * @param bo 智能体
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AiAgentBo bo) {
        AiAgent add = MapstructUtils.convert(bo, AiAgent.class);
        validEntityBeforeSave(add);
        boolean flag = aiAgentMapper.insert(add) > 0;
        if (flag) {
            bo.setAgentId(add.getAgentId());
        }
        return flag;
    }

    /**
     * 修改智能体
     *
     * @param bo 智能体
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AiAgentBo bo) {
        AiAgent update = MapstructUtils.convert(bo, AiAgent.class);
        validEntityBeforeSave(update);
        return aiAgentMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AiAgent entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除智能体信息
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
        return aiAgentMapper.deleteByIds(ids) > 0;
    }

}
