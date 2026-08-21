package com.ulpon.ai.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
import com.ulpon.ai.domain.bo.AiChatMessageBo;
import com.ulpon.ai.domain.vo.AiChatMessageVo;
import com.ulpon.ai.domain.AiChatMessage;
import com.ulpon.ai.mapper.AiChatMessageMapper;
import com.ulpon.ai.service.IAiChatMessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 对话消息Service业务层处理
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AiChatMessageServiceImpl extends ServiceImpl<AiChatMessageMapper, AiChatMessage> implements IAiChatMessageService {

    private final AiChatMessageMapper aiChatMessageMapper;

    /**
     * 查询对话消息
     *
     * @param messageId 主键
     * @return 对话消息
     */
    @Override
    public AiChatMessageVo queryById(Long messageId) {
        return aiChatMessageMapper.selectVoById(messageId);
    }

    @Override
    public List<AiChatMessageVo> queryBySessionId(Long sessionId) {
        LambdaQueryWrapper<AiChatMessage> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(AiChatMessage::getSessionId, sessionId)
            .orderByAsc(AiChatMessage::getCreateTime);
        return aiChatMessageMapper.selectVoList(wrapper);
    }

    /**
     * 查询对话消息列表
     *
     * @param messageIdList 主键列表
     * @return 对话消息列表
     */
    @Override
    public List<AiChatMessageVo> queryByIds(Collection<Long> messageIdList) {
        if (CollUtil.isEmpty(messageIdList)) return List.of();
        return aiChatMessageMapper.selectVoByIds(messageIdList);
    }

    /**
     * 分页查询对话消息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 对话消息分页列表
     */
    @Override
    public PageResult<AiChatMessageVo> queryPageList(AiChatMessageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiChatMessage> lqw = buildQueryWrapper(bo);
        Page<AiChatMessageVo> result = aiChatMessageMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的对话消息列表
     *
     * @param bo 查询条件
     * @return 对话消息列表
     */
    @Override
    public List<AiChatMessageVo> queryList(AiChatMessageBo bo) {
        LambdaQueryWrapper<AiChatMessage> lqw = buildQueryWrapper(bo);
        return aiChatMessageMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<AiChatMessage> buildQueryWrapper(AiChatMessageBo bo) {
        return QueryBuilder.lambda(AiChatMessage.class)
            .eqIfPresent(AiChatMessage::getSessionId, bo.getSessionId())
            .eqIfText(AiChatMessage::getRole, bo.getRole())
            .eqIfText(AiChatMessage::getContent, bo.getContent())
            .eqIfPresent(AiChatMessage::getTokenCount, bo.getTokenCount())
            .orderByAsc(AiChatMessage::getMessageId)
            .build();
    }

    /**
     * 新增对话消息
     *
     * @param bo 对话消息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AiChatMessageBo bo) {
        AiChatMessage add = MapstructUtils.convert(bo, AiChatMessage.class);
        validEntityBeforeSave(add);
        boolean flag = aiChatMessageMapper.insert(add) > 0;
        if (flag) {
            bo.setMessageId(add.getMessageId());
        }
        return flag;
    }

    /**
     * 修改对话消息
     *
     * @param bo 对话消息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AiChatMessageBo bo) {
        AiChatMessage update = MapstructUtils.convert(bo, AiChatMessage.class);
        validEntityBeforeSave(update);
        return aiChatMessageMapper.updateById(update) > 0;
    }


    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AiChatMessage entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除对话消息信息
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
        return aiChatMessageMapper.deleteByIds(ids) > 0;
    }

    @Override
    public void deleteBySessionId(Long sessionId) {
        LambdaUpdateWrapper<AiChatMessage> queryWrapper = Wrappers.lambdaUpdate();
        queryWrapper.eq(AiChatMessage::getSessionId, sessionId);
        aiChatMessageMapper.delete(queryWrapper);
    }

}
