package org.dromara.gen.service.impl;

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
import org.dromara.gen.domain.bo.GenTemplateBo;
import org.dromara.gen.domain.vo.GenTemplateVo;
import org.dromara.gen.domain.GenTemplate;
import org.dromara.gen.mapper.GenTemplateMapper;
import org.dromara.gen.service.IGenTemplateService;
import org.dromara.gen.domain.GenTemplate;
import org.dromara.gen.mapper.GenTemplateMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 代码生成模板Service业务层处理
 *
 * @author Ulpon
 * @date 2026-08-28 14:02:26
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GenTemplateServiceImpl extends ServiceImpl<GenTemplateMapper, GenTemplate> implements IGenTemplateService {

    private final GenTemplateMapper genTemplateMapper;

    /**
     * 查询代码生成模板
     *
     * @param id 主键
     * @return 代码生成模板
     */
    @Override
    public GenTemplateVo queryById(Long id) {
        return genTemplateMapper.selectVoById(id);
    }

    /**
     * 查询代码生成模板列表
     *
     * @param idList 主键列表
     * @return 代码生成模板列表
     */
    @Override
    public List<GenTemplateVo> queryByIds(Collection<Long> idList) {
        if (CollUtil.isEmpty(idList)) return List.of();
        return genTemplateMapper.selectVoByIds(idList);
    }

    /**
     * 分页查询代码生成模板列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 代码生成模板分页列表
     */
    @Override
    public PageResult<GenTemplateVo> queryPageList(GenTemplateBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GenTemplate> lqw = buildQueryWrapper(bo);
        Page<GenTemplateVo> result = genTemplateMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的代码生成模板列表
     *
     * @param bo 查询条件
     * @return 代码生成模板列表
     */
    @Override
    public List<GenTemplateVo> queryList(GenTemplateBo bo) {
        LambdaQueryWrapper<GenTemplate> lqw = buildQueryWrapper(bo);
        return genTemplateMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<GenTemplate> buildQueryWrapper(GenTemplateBo bo) {
        return QueryBuilder.lambda(GenTemplate.class)
            .eqIfText(GenTemplate::getType, bo.getType())
            .eqIfText(GenTemplate::getPath, bo.getPath())
            .likeIfText(GenTemplate::getName, bo.getName())
            .eqIfText(GenTemplate::getContent, bo.getContent())
            .eqIfPresent(GenTemplate::getSort, bo.getSort())
            .orderByAsc(GenTemplate::getId)
            .build();
    }

    /**
     * 新增代码生成模板
     *
     * @param bo 代码生成模板
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(GenTemplateBo bo) {
        GenTemplate add = MapstructUtils.convert(bo, GenTemplate.class);
        validEntityBeforeSave(add);
        boolean flag = genTemplateMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改代码生成模板
     *
     * @param bo 代码生成模板
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(GenTemplateBo bo) {
        GenTemplate update = MapstructUtils.convert(bo, GenTemplate.class);
        validEntityBeforeSave(update);
        return genTemplateMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GenTemplate entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除代码生成模板信息
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
        return genTemplateMapper.deleteByIds(ids) > 0;
    }

}
