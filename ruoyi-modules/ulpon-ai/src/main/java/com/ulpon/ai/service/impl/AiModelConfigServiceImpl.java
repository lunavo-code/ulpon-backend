package com.ulpon.ai.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.ulpon.ai.domain.AiModelConfig;
import com.ulpon.ai.domain.AiModelProvider;
import com.ulpon.ai.domain.bo.AiModelConfigBo;
import com.ulpon.ai.domain.vo.AiModelConfigVo;
import com.ulpon.ai.mapper.AiModelConfigMapper;
import com.ulpon.ai.mapper.AiModelProviderMapper;
import com.ulpon.ai.service.IAiModelConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.query.QueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 大模型配置Service业务层处理
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AiModelConfigServiceImpl extends ServiceImpl<AiModelConfigMapper, AiModelConfig> implements IAiModelConfigService {

    private final AiModelConfigMapper aiModelConfigMapper;
    private final AiModelProviderMapper aiModelProviderMapper;

    /**
     * 查询大模型配置
     *
     * @param modelConfigId 主键
     * @return 大模型配置
     */
    @Override
    public AiModelConfigVo queryById(Long modelConfigId) {
        return aiModelConfigMapper.selectVoById(modelConfigId);
    }

    /**
     * 查询大模型配置列表
     *
     * @param modelConfigIdList 主键列表
     * @return 大模型配置列表
     */
    @Override
    public List<AiModelConfigVo> queryByIds(Collection<Long> modelConfigIdList) {
        if (CollUtil.isEmpty(modelConfigIdList)) return List.of();
        return aiModelConfigMapper.selectVoByIds(modelConfigIdList);
    }

    /**
     * 分页查询大模型配置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 大模型配置分页列表
     */
    @Override
    public PageResult<AiModelConfigVo> queryPageList(AiModelConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiModelConfig> lqw = buildQueryWrapper(bo);
        Page<AiModelConfigVo> result = aiModelConfigMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的大模型配置列表
     *
     * @param bo 查询条件
     * @return 大模型配置列表
     */
    @Override
    public List<AiModelConfigVo> queryList(AiModelConfigBo bo) {
        LambdaQueryWrapper<AiModelConfig> lqw = buildQueryWrapper(bo);
        return aiModelConfigMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<AiModelConfig> buildQueryWrapper(AiModelConfigBo bo) {
        return QueryBuilder.lambda(AiModelConfig.class)
            .eqIfPresent(AiModelConfig::getProviderId, bo.getProviderId())
            .likeIfText(AiModelConfig::getConfigName, bo.getConfigName())
            .likeIfText(AiModelConfig::getModelName, bo.getModelName())
            .eqIfText(AiModelConfig::getBaseUrl, bo.getBaseUrl())
            .eqIfText(AiModelConfig::getApiKey, bo.getApiKey())
            .eqIfPresent(AiModelConfig::getMaxTokens, bo.getMaxTokens())
            .eqIfPresent(AiModelConfig::getTemperature, bo.getTemperature())
            .eqIfPresent(AiModelConfig::getStatus, bo.getStatus())
            .eqIfPresent(AiModelConfig::getIsDefault, bo.getIsDefault())
            .orderByAsc(AiModelConfig::getModelConfigId)
            .build();
    }

    /**
     * 新增大模型配置
     *
     * @param bo 大模型配置
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AiModelConfigBo bo) {
        AiModelConfig add = MapstructUtils.convert(bo, AiModelConfig.class);
        validEntityBeforeSave(add);
        boolean flag = aiModelConfigMapper.insert(add) > 0;
        if (flag) {
            bo.setModelConfigId(add.getModelConfigId());
        }
        return flag;
    }

    /**
     * 修改大模型配置
     *
     * @param bo 大模型配置
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AiModelConfigBo bo) {
        AiModelConfig update = MapstructUtils.convert(bo, AiModelConfig.class);
        validEntityBeforeSave(update);
        return aiModelConfigMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AiModelConfig entity) {
        String baseUrl = entity.getBaseUrl();
        if(baseUrl == null) {
            AiModelProvider provider = aiModelProviderMapper.selectById(entity.getProviderId());
            entity.setBaseUrl(provider.getDefaultBaseUrl());
        }
    }


    /**
     * 校验并批量删除大模型配置信息
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
        return aiModelConfigMapper.deleteByIds(ids) > 0;
    }

}
