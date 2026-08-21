package com.ulpon.ai.service;

import com.ulpon.ai.domain.vo.AiModelProviderVo;
import com.ulpon.ai.domain.bo.AiModelProviderBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 大模型提供商Service接口
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
public interface IAiModelProviderService {

    /**
     * 查询大模型提供商
     *
     * @param providerId 主键
     * @return 大模型提供商
     */
    AiModelProviderVo queryById(Long providerId);

    /**
     * 查询大模型提供商列表
     *
     * @param providerIdList 主键列表
     * @return 大模型提供商列表
     */
    List<AiModelProviderVo> queryByIds(Collection<Long> providerIdList);

    /**
     * 查询核心词管理map
     *
     * @param providerIdList 主键列表
     * @return 核心词管理map
     */
    default Map<Long, AiModelProviderVo> queryMapByIds(Collection<Long> providerIdList){
        return queryByIds(providerIdList).stream().collect(Collectors.toMap(AiModelProviderVo::getProviderId, v -> v));
    }

    /**
     * 分页查询大模型提供商列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 大模型提供商分页列表
     */
    PageResult<AiModelProviderVo> queryPageList(AiModelProviderBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的大模型提供商列表
     *
     * @param bo 查询条件
     * @return 大模型提供商列表
     */
    List<AiModelProviderVo> queryList(AiModelProviderBo bo);


    /**
     * 新增大模型提供商
     *
     * @param bo 大模型提供商
     * @return 是否新增成功
     */
    Boolean insertByBo(AiModelProviderBo bo);

    /**
     * 修改大模型提供商
     *
     * @param bo 大模型提供商
     * @return 是否修改成功
     */
    Boolean updateByBo(AiModelProviderBo bo);



    /**
     * 校验并批量删除大模型提供商信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
