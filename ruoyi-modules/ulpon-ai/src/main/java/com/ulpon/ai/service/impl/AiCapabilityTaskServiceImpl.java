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
import com.ulpon.ai.domain.bo.AiCapabilityTaskBo;
import com.ulpon.ai.domain.vo.AiCapabilityTaskVo;
import com.ulpon.ai.domain.AiCapabilityTask;
import com.ulpon.ai.mapper.AiCapabilityTaskMapper;
import com.ulpon.ai.service.IAiCapabilityTaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * AI能力任务Service业务层处理
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AiCapabilityTaskServiceImpl extends ServiceImpl<AiCapabilityTaskMapper, AiCapabilityTask> implements IAiCapabilityTaskService {

    private final AiCapabilityTaskMapper aiCapabilityTaskMapper;

    /**
     * 查询AI能力任务
     *
     * @param taskId 主键
     * @return AI能力任务
     */
    @Override
    public AiCapabilityTaskVo queryById(Long taskId) {
        return aiCapabilityTaskMapper.selectVoById(taskId);
    }

    /**
     * 查询AI能力任务列表
     *
     * @param taskIdList 主键列表
     * @return AI能力任务列表
     */
    @Override
    public List<AiCapabilityTaskVo> queryByIds(Collection<Long> taskIdList) {
        if (CollUtil.isEmpty(taskIdList)) return List.of();
        return aiCapabilityTaskMapper.selectVoByIds(taskIdList);
    }

    /**
     * 分页查询AI能力任务列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return AI能力任务分页列表
     */
    @Override
    public PageResult<AiCapabilityTaskVo> queryPageList(AiCapabilityTaskBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiCapabilityTask> lqw = buildQueryWrapper(bo);
        Page<AiCapabilityTaskVo> result = aiCapabilityTaskMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的AI能力任务列表
     *
     * @param bo 查询条件
     * @return AI能力任务列表
     */
    @Override
    public List<AiCapabilityTaskVo> queryList(AiCapabilityTaskBo bo) {
        LambdaQueryWrapper<AiCapabilityTask> lqw = buildQueryWrapper(bo);
        return aiCapabilityTaskMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<AiCapabilityTask> buildQueryWrapper(AiCapabilityTaskBo bo) {
        return QueryBuilder.lambda(AiCapabilityTask.class)
            .eqIfPresent(AiCapabilityTask::getCapabilityId, bo.getCapabilityId())
            .likeIfText(AiCapabilityTask::getUserContent, bo.getUserContent())
            .likeIfText(AiCapabilityTask::getResContent, bo.getResContent())
            .eqIfPresent(AiCapabilityTask::getTokenCount, bo.getTokenCount())
            .orderByAsc(AiCapabilityTask::getTaskId)
            .build();
    }

    /**
     * 新增AI能力任务
     *
     * @param bo AI能力任务
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AiCapabilityTaskBo bo) {
        AiCapabilityTask add = MapstructUtils.convert(bo, AiCapabilityTask.class);
        validEntityBeforeSave(add);
        boolean flag = aiCapabilityTaskMapper.insert(add) > 0;
        if (flag) {
            bo.setTaskId(add.getTaskId());
        }
        return flag;
    }

    /**
     * 修改AI能力任务
     *
     * @param bo AI能力任务
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AiCapabilityTaskBo bo) {
        AiCapabilityTask update = MapstructUtils.convert(bo, AiCapabilityTask.class);
        validEntityBeforeSave(update);
        return aiCapabilityTaskMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AiCapabilityTask entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除AI能力任务信息
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
        return aiCapabilityTaskMapper.deleteByIds(ids) > 0;
    }

}
