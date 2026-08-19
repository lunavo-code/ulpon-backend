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
import com.ulpon.geo.domain.bo.GeoKeywordDerivedBo;
import com.ulpon.geo.domain.vo.GeoKeywordDerivedVo;
import com.ulpon.geo.domain.GeoKeywordDerived;
import com.ulpon.geo.mapper.GeoKeywordDerivedMapper;
import com.ulpon.geo.service.IGeoKeywordDerivedService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 派生标题管理Service业务层处理
 *
 * @author Ulpon
 * @date 2026-08-20 06:16:32
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GeoKeywordDerivedServiceImpl implements IGeoKeywordDerivedService {

    private final GeoKeywordDerivedMapper geoKeywordDerivedMapper;

    /**
     * 查询派生标题管理
     *
     * @param derivedId 主键
     * @return 派生标题管理
     */
    @Override
    public GeoKeywordDerivedVo queryById(Long derivedId) {
        return geoKeywordDerivedMapper.selectVoById(derivedId);
    }

    /**
     * 分页查询派生标题管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 派生标题管理分页列表
     */
    @Override
    public PageResult<GeoKeywordDerivedVo> queryPageList(GeoKeywordDerivedBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GeoKeywordDerived> lqw = buildQueryWrapper(bo);
        Page<GeoKeywordDerivedVo> result = geoKeywordDerivedMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的派生标题管理列表
     *
     * @param bo 查询条件
     * @return 派生标题管理列表
     */
    @Override
    public List<GeoKeywordDerivedVo> queryList(GeoKeywordDerivedBo bo) {
        LambdaQueryWrapper<GeoKeywordDerived> lqw = buildQueryWrapper(bo);
        return geoKeywordDerivedMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<GeoKeywordDerived> buildQueryWrapper(GeoKeywordDerivedBo bo) {
        return QueryBuilder.lambda(GeoKeywordDerived.class)
            .eqIfPresent(GeoKeywordDerived::getKeywordId, bo.getKeywordId())
            .eqIfText(GeoKeywordDerived::getTitleType, bo.getTitleType())
            .likeIfText(GeoKeywordDerived::getDerivedQuestion, bo.getDerivedQuestion())
            .eqIfText(GeoKeywordDerived::getStatus, bo.getStatus())
            .orderByAsc(GeoKeywordDerived::getDerivedId)
            .build();
    }

    /**
     * 新增派生标题管理
     *
     * @param bo 派生标题管理
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(GeoKeywordDerivedBo bo) {
        GeoKeywordDerived add = MapstructUtils.convert(bo, GeoKeywordDerived.class);
        validEntityBeforeSave(add);
        boolean flag = geoKeywordDerivedMapper.insert(add) > 0;
        if (flag) {
            bo.setDerivedId(add.getDerivedId());
        }
        return flag;
    }

    /**
     * 修改派生标题管理
     *
     * @param bo 派生标题管理
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(GeoKeywordDerivedBo bo) {
        GeoKeywordDerived update = MapstructUtils.convert(bo, GeoKeywordDerived.class);
        validEntityBeforeSave(update);
        return geoKeywordDerivedMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GeoKeywordDerived entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除派生标题管理信息
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
        return geoKeywordDerivedMapper.deleteByIds(ids) > 0;
    }

}
