package com.ulpon.ai.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.ulpon.ai.domain.AiCapabilityTask;
import com.ulpon.ai.domain.vo.AiCapabilityTaskVo;
import com.ulpon.ai.domain.bo.AiCapabilityTaskBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AI能力任务Service接口
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
public interface IAiCapabilityTaskService extends IService<AiCapabilityTask> {

    /**
     * 查询AI能力任务
     *
     * @param taskId 主键
     * @return AI能力任务
     */
    AiCapabilityTaskVo queryById(Long taskId);

    /**
     * 查询AI能力任务列表
     *
     * @param taskIdList 主键列表
     * @return AI能力任务列表
     */
    List<AiCapabilityTaskVo> queryByIds(Collection<Long> taskIdList);

    /**
     * 查询核心词管理map
     *
     * @param taskIdList 主键列表
     * @return 核心词管理map
     */
    default Map<Long, AiCapabilityTaskVo> queryMapByIds(Collection<Long> taskIdList){
        return queryByIds(taskIdList).stream().collect(Collectors.toMap(AiCapabilityTaskVo::getTaskId, v -> v));
    }

    /**
     * 分页查询AI能力任务列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return AI能力任务分页列表
     */
    PageResult<AiCapabilityTaskVo> queryPageList(AiCapabilityTaskBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的AI能力任务列表
     *
     * @param bo 查询条件
     * @return AI能力任务列表
     */
    List<AiCapabilityTaskVo> queryList(AiCapabilityTaskBo bo);


    /**
     * 新增AI能力任务
     *
     * @param bo AI能力任务
     * @return 是否新增成功
     */
    Boolean insertByBo(AiCapabilityTaskBo bo);

    /**
     * 修改AI能力任务
     *
     * @param bo AI能力任务
     * @return 是否修改成功
     */
    Boolean updateByBo(AiCapabilityTaskBo bo);



    /**
     * 校验并批量删除AI能力任务信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
