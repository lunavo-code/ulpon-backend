package com.ulpon.ai.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.ulpon.ai.domain.AiAgent;
import com.ulpon.ai.domain.vo.AiAgentVo;
import com.ulpon.ai.domain.bo.AiAgentBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 智能体Service接口
 *
 * @author Ulpon
 * @date 2026-08-21 12:08:12
 */
public interface IAiAgentService extends IService<AiAgent> {

    /**
     * 查询智能体
     *
     * @param agentId 主键
     * @return 智能体
     */
    AiAgentVo queryById(Long agentId);

    /**
     * 查询智能体列表
     *
     * @param agentIdList 主键列表
     * @return 智能体列表
     */
    List<AiAgentVo> queryByIds(Collection<Long> agentIdList);

    /**
     * 查询核心词管理map
     *
     * @param agentIdList 主键列表
     * @return 核心词管理map
     */
    default Map<Long, AiAgentVo> queryMapByIds(Collection<Long> agentIdList){
        return queryByIds(agentIdList).stream().collect(Collectors.toMap(AiAgentVo::getAgentId, v -> v));
    }

    /**
     * 分页查询智能体列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 智能体分页列表
     */
    PageResult<AiAgentVo> queryPageList(AiAgentBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的智能体列表
     *
     * @param bo 查询条件
     * @return 智能体列表
     */
    List<AiAgentVo> queryList(AiAgentBo bo);


    /**
     * 新增智能体
     *
     * @param bo 智能体
     * @return 是否新增成功
     */
    Boolean insertByBo(AiAgentBo bo);

    /**
     * 修改智能体
     *
     * @param bo 智能体
     * @return 是否修改成功
     */
    Boolean updateByBo(AiAgentBo bo);



    /**
     * 校验并批量删除智能体信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
