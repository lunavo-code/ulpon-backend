package org.dromara.gen.service;

import com.baomidou.mybatisplus.spring.service.IService;
import org.dromara.gen.domain.GenTemplate;
import org.dromara.gen.domain.vo.GenTemplateVo;
import org.dromara.gen.domain.bo.GenTemplateBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 代码生成模板Service接口
 *
 * @author Ulpon
 * @date 2026-08-28 14:02:26
 */
public interface IGenTemplateService extends IService<GenTemplate> {

    /**
     * 查询代码生成模板
     *
     * @param id 主键
     * @return 代码生成模板
     */
    GenTemplateVo queryById(Long id);

    /**
     * 查询代码生成模板列表
     *
     * @param idList 主键列表
     * @return 代码生成模板列表
     */
    List<GenTemplateVo> queryByIds(Collection<Long> idList);

    /**
     * 查询核心词管理map
     *
     * @param idList 主键列表
     * @return 核心词管理map
     */
    default Map<Long, GenTemplateVo> queryMapByIds(Collection<Long> idList){
        return queryByIds(idList).stream().collect(Collectors.toMap(GenTemplateVo::getId, v -> v));
    }

    /**
     * 分页查询代码生成模板列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 代码生成模板分页列表
     */
    PageResult<GenTemplateVo> queryPageList(GenTemplateBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的代码生成模板列表
     *
     * @param bo 查询条件
     * @return 代码生成模板列表
     */
    List<GenTemplateVo> queryList(GenTemplateBo bo);


    /**
     * 新增代码生成模板
     *
     * @param bo 代码生成模板
     * @return 是否新增成功
     */
    Boolean insertByBo(GenTemplateBo bo);

    /**
     * 修改代码生成模板
     *
     * @param bo 代码生成模板
     * @return 是否修改成功
     */
    Boolean updateByBo(GenTemplateBo bo);



    /**
     * 校验并批量删除代码生成模板信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
