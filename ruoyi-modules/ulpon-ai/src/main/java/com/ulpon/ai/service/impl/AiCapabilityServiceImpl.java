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
import com.ulpon.ai.domain.bo.AiCapabilityBo;
import com.ulpon.ai.domain.vo.AiCapabilityVo;
import com.ulpon.ai.domain.AiCapability;
import com.ulpon.ai.mapper.AiCapabilityMapper;
import com.ulpon.ai.service.IAiCapabilityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * AI能力Service业务层处理
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AiCapabilityServiceImpl extends ServiceImpl<AiCapabilityMapper, AiCapability> implements IAiCapabilityService {

    private final AiCapabilityMapper aiCapabilityMapper;

    /**
     * 查询AI能力
     *
     * @param capabilityId 主键
     * @return AI能力
     */
    @Override
    public AiCapabilityVo queryById(Long capabilityId) {
        return aiCapabilityMapper.selectVoById(capabilityId);
    }

    /**
     * 查询AI能力列表
     *
     * @param capabilityIdList 主键列表
     * @return AI能力列表
     */
    @Override
    public List<AiCapabilityVo> queryByIds(Collection<Long> capabilityIdList) {
        if (CollUtil.isEmpty(capabilityIdList)) return List.of();
        return aiCapabilityMapper.selectVoByIds(capabilityIdList);
    }

    /**
     * 分页查询AI能力列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return AI能力分页列表
     */
    @Override
    public PageResult<AiCapabilityVo> queryPageList(AiCapabilityBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiCapability> lqw = buildQueryWrapper(bo);
        Page<AiCapabilityVo> result = aiCapabilityMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的AI能力列表
     *
     * @param bo 查询条件
     * @return AI能力列表
     */
    @Override
    public List<AiCapabilityVo> queryList(AiCapabilityBo bo) {
        LambdaQueryWrapper<AiCapability> lqw = buildQueryWrapper(bo);
        return aiCapabilityMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<AiCapability> buildQueryWrapper(AiCapabilityBo bo) {
        return QueryBuilder.lambda(AiCapability.class)
            .likeIfText(AiCapability::getCapabilityName, bo.getCapabilityName())
            .eqIfText(AiCapability::getDescription, bo.getDescription())
            .eqIfText(AiCapability::getSystemPrompt, bo.getSystemPrompt())
            .eqIfPresent(AiCapability::getModelConfigId, bo.getModelConfigId())
            .eqIfText(AiCapability::getScopeType, bo.getScopeType())
            .eqIfPresent(AiCapability::getDeptId, bo.getDeptId())
            .eqIfPresent(AiCapability::getUserId, bo.getUserId())
            .eqIfPresent(AiCapability::getStatus, bo.getStatus())
            .orderByAsc(AiCapability::getCapabilityId)
            .build();
    }

    /**
     * 新增AI能力
     *
     * @param bo AI能力
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AiCapabilityBo bo) {
        AiCapability add = MapstructUtils.convert(bo, AiCapability.class);
        validEntityBeforeSave(add);
        boolean flag = aiCapabilityMapper.insert(add) > 0;
        if (flag) {
            bo.setCapabilityId(add.getCapabilityId());
        }
        return flag;
    }

    /**
     * 修改AI能力
     *
     * @param bo AI能力
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AiCapabilityBo bo) {
        AiCapability update = MapstructUtils.convert(bo, AiCapability.class);
        validEntityBeforeSave(update);
        return aiCapabilityMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AiCapability entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除AI能力信息
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
        return aiCapabilityMapper.deleteByIds(ids) > 0;
    }

}
