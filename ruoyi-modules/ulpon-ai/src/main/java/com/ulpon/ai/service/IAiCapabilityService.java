package com.ulpon.ai.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.ulpon.ai.domain.AiCapability;
import com.ulpon.ai.domain.vo.AiCapabilityVo;
import com.ulpon.ai.domain.bo.AiCapabilityBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AI能力Service接口
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
public interface IAiCapabilityService extends IService<AiCapability> {

    /**
     * 查询AI能力
     *
     * @param capabilityId 主键
     * @return AI能力
     */
    AiCapabilityVo queryById(Long capabilityId);

    /**
     * 查询AI能力列表
     *
     * @param capabilityIdList 主键列表
     * @return AI能力列表
     */
    List<AiCapabilityVo> queryByIds(Collection<Long> capabilityIdList);

    /**
     * 查询核心词管理map
     *
     * @param capabilityIdList 主键列表
     * @return 核心词管理map
     */
    default Map<Long, AiCapabilityVo> queryMapByIds(Collection<Long> capabilityIdList){
        return queryByIds(capabilityIdList).stream().collect(Collectors.toMap(AiCapabilityVo::getCapabilityId, v -> v));
    }

    /**
     * 分页查询AI能力列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return AI能力分页列表
     */
    PageResult<AiCapabilityVo> queryPageList(AiCapabilityBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的AI能力列表
     *
     * @param bo 查询条件
     * @return AI能力列表
     */
    List<AiCapabilityVo> queryList(AiCapabilityBo bo);


    /**
     * 新增AI能力
     *
     * @param bo AI能力
     * @return 是否新增成功
     */
    Boolean insertByBo(AiCapabilityBo bo);

    /**
     * 修改AI能力
     *
     * @param bo AI能力
     * @return 是否修改成功
     */
    Boolean updateByBo(AiCapabilityBo bo);



    /**
     * 校验并批量删除AI能力信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
