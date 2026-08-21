package com.ulpon.ai.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
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
import com.ulpon.ai.domain.bo.AiModelProviderBo;
import com.ulpon.ai.domain.vo.AiModelProviderVo;
import com.ulpon.ai.domain.AiModelProvider;
import com.ulpon.ai.mapper.AiModelProviderMapper;
import com.ulpon.ai.service.IAiModelProviderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 大模型提供商Service业务层处理
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AiModelProviderServiceImpl implements IAiModelProviderService {

    private final AiModelProviderMapper aiModelProviderMapper;

    /**
     * 查询大模型提供商
     *
     * @param providerId 主键
     * @return 大模型提供商
     */
    @Override
    public AiModelProviderVo queryById(Long providerId) {
        return aiModelProviderMapper.selectVoById(providerId);
    }

    /**
     * 查询大模型提供商列表
     *
     * @param providerIdList 主键列表
     * @return 大模型提供商列表
     */
    @Override
    public List<AiModelProviderVo> queryByIds(Collection<Long> providerIdList) {
        if (CollUtil.isEmpty(providerIdList)) return List.of();
        return aiModelProviderMapper.selectVoByIds(providerIdList);
    }

    /**
     * 分页查询大模型提供商列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 大模型提供商分页列表
     */
    @Override
    public PageResult<AiModelProviderVo> queryPageList(AiModelProviderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiModelProvider> lqw = buildQueryWrapper(bo);
        Page<AiModelProviderVo> result = aiModelProviderMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的大模型提供商列表
     *
     * @param bo 查询条件
     * @return 大模型提供商列表
     */
    @Override
    public List<AiModelProviderVo> queryList(AiModelProviderBo bo) {
        LambdaQueryWrapper<AiModelProvider> lqw = buildQueryWrapper(bo);
        return aiModelProviderMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<AiModelProvider> buildQueryWrapper(AiModelProviderBo bo) {
        return QueryBuilder.lambda(AiModelProvider.class)
            .likeIfText(AiModelProvider::getProviderName, bo.getProviderName())
            .eqIfText(AiModelProvider::getProviderCode, bo.getProviderCode())
            .eqIfText(AiModelProvider::getDefaultBaseUrl, bo.getDefaultBaseUrl())
            .eqIfPresent(AiModelProvider::getStatus, bo.getStatus())
            .orderByAsc(AiModelProvider::getProviderId)
            .build();
    }

    /**
     * 新增大模型提供商
     *
     * @param bo 大模型提供商
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AiModelProviderBo bo) {
        AiModelProvider add = MapstructUtils.convert(bo, AiModelProvider.class);
        validEntityBeforeSave(add);
        boolean flag = aiModelProviderMapper.insert(add) > 0;
        if (flag) {
            bo.setProviderId(add.getProviderId());
        }
        return flag;
    }

    /**
     * 修改大模型提供商
     *
     * @param bo 大模型提供商
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AiModelProviderBo bo) {
        AiModelProvider update = MapstructUtils.convert(bo, AiModelProvider.class);
        validEntityBeforeSave(update);
        return aiModelProviderMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AiModelProvider entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除大模型提供商信息
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
        return aiModelProviderMapper.deleteByIds(ids) > 0;
    }

}
