package com.ulpon.ai.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.ulpon.ai.domain.AiModelConfig;
import com.ulpon.ai.domain.vo.AiModelConfigVo;
import com.ulpon.ai.domain.bo.AiModelConfigBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 大模型配置Service接口
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
public interface IAiModelConfigService extends IService<AiModelConfig> {

    /**
     * 查询大模型配置
     *
     * @param modelConfigId 主键
     * @return 大模型配置
     */
    AiModelConfigVo queryById(Long modelConfigId);

    /**
     * 查询大模型配置列表
     *
     * @param modelConfigIdList 主键列表
     * @return 大模型配置列表
     */
    List<AiModelConfigVo> queryByIds(Collection<Long> modelConfigIdList);

    /**
     * 查询核心词管理map
     *
     * @param modelConfigIdList 主键列表
     * @return 核心词管理map
     */
    default Map<Long, AiModelConfigVo> queryMapByIds(Collection<Long> modelConfigIdList){
        return queryByIds(modelConfigIdList).stream().collect(Collectors.toMap(AiModelConfigVo::getModelConfigId, v -> v));
    }

    /**
     * 分页查询大模型配置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 大模型配置分页列表
     */
    PageResult<AiModelConfigVo> queryPageList(AiModelConfigBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的大模型配置列表
     *
     * @param bo 查询条件
     * @return 大模型配置列表
     */
    List<AiModelConfigVo> queryList(AiModelConfigBo bo);


    /**
     * 新增大模型配置
     *
     * @param bo 大模型配置
     * @return 是否新增成功
     */
    Boolean insertByBo(AiModelConfigBo bo);

    /**
     * 修改大模型配置
     *
     * @param bo 大模型配置
     * @return 是否修改成功
     */
    Boolean updateByBo(AiModelConfigBo bo);



    /**
     * 校验并批量删除大模型配置信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
