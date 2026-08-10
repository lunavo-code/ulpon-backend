package com.ulpon.geo.service.impl;

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
import com.ulpon.geo.domain.bo.GeoKeywordBo;
import com.ulpon.geo.domain.vo.GeoKeywordVo;
import com.ulpon.geo.domain.GeoKeyword;
import com.ulpon.geo.mapper.GeoKeywordMapper;
import com.ulpon.geo.service.IGeoKeywordService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 核心关键词Service业务层处理
 *
 * @author Ulpon
 * @date 2026-08-09 12:53:08
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GeoKeywordServiceImpl implements IGeoKeywordService {

    private final GeoKeywordMapper geoKeywordMapper;

    /**
     * 查询核心关键词
     *
     * @param id 主键
     * @return 核心关键词
     */
    @Override
    public GeoKeywordVo queryById(Long id) {
        return geoKeywordMapper.selectVoById(id);
    }

    /**
     * 分页查询核心关键词列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 核心关键词分页列表
     */
    @Override
    public PageResult<GeoKeywordVo> queryPageList(GeoKeywordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GeoKeyword> lqw = buildQueryWrapper(bo);
        Page<GeoKeywordVo> result = geoKeywordMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的核心关键词列表
     *
     * @param bo 查询条件
     * @return 核心关键词列表
     */
    @Override
    public List<GeoKeywordVo> queryList(GeoKeywordBo bo) {
        LambdaQueryWrapper<GeoKeyword> lqw = buildQueryWrapper(bo);
        return geoKeywordMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<GeoKeyword> buildQueryWrapper(GeoKeywordBo bo) {
        return QueryBuilder.lambda(GeoKeyword.class)
            .likeIfText(GeoKeyword::getKeyword, bo.getKeyword())
            .likeIfText(GeoKeyword::getHit, bo.getHit())
            .eqIfPresent(GeoKeyword::getStatus, bo.getStatus())
            .orderByAsc(GeoKeyword::getId)
            .build();
    }

    /**
     * 新增核心关键词
     *
     * @param bo 核心关键词
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(GeoKeywordBo bo) {
        GeoKeyword add = MapstructUtils.convert(bo, GeoKeyword.class);
        validEntityBeforeSave(add);
        boolean flag = geoKeywordMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改核心关键词
     *
     * @param bo 核心关键词
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(GeoKeywordBo bo) {
        GeoKeyword update = MapstructUtils.convert(bo, GeoKeyword.class);
        validEntityBeforeSave(update);
        return geoKeywordMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GeoKeyword entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除核心关键词信息
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
        return geoKeywordMapper.deleteByIds(ids) > 0;
    }

}
