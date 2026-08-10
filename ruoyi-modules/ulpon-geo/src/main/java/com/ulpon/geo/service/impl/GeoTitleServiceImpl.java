package com.ulpon.geo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ulpon.geo.domain.GeoTitle;
import com.ulpon.geo.domain.bo.GeoTitleBo;
import com.ulpon.geo.domain.vo.GeoTitleVo;
import com.ulpon.geo.exceptions.GeoException;
import com.ulpon.geo.mapper.GeoTitleMapper;
import com.ulpon.geo.service.IGeoTitleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.query.QueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * GEO标题生成Service业务层处理
 *
 * @author Ulpon
 * @date 2026-08-09 17:39:11
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GeoTitleServiceImpl implements IGeoTitleService {

    private final GeoTitleMapper geoTitleMapper;

    /**
     * 查询GEO标题生成
     *
     * @param id 主键
     * @return GEO标题生成
     */
    @Override
    public GeoTitleVo queryById(Long id) {
        return geoTitleMapper.selectVoById(id);
    }

    /**
     * 分页查询GEO标题生成列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return GEO标题生成分页列表
     */
    @Override
    public PageResult<GeoTitleVo> queryPageList(GeoTitleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GeoTitle> lqw = buildQueryWrapper(bo);
        Page<GeoTitleVo> result = geoTitleMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的GEO标题生成列表
     *
     * @param bo 查询条件
     * @return GEO标题生成列表
     */
    @Override
    public List<GeoTitleVo> queryList(GeoTitleBo bo) {
        LambdaQueryWrapper<GeoTitle> lqw = buildQueryWrapper(bo);
        return geoTitleMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<GeoTitle> buildQueryWrapper(GeoTitleBo bo) {
        return QueryBuilder.lambda(GeoTitle.class)
            .eqIfPresent(GeoTitle::getKeywordId, bo.getKeywordId())
            .likeIfText(GeoTitle::getTitle, bo.getTitle())
            .eqIfPresent(GeoTitle::getStatus, bo.getStatus())
            .orderByAsc(GeoTitle::getId)
            .build();
    }

    /**
     * 新增GEO标题生成
     *
     * @param bo GEO标题生成
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(GeoTitleBo bo) {
        GeoTitle add = MapstructUtils.convert(bo, GeoTitle.class);
        validEntityBeforeSave(add);
        List<GeoTitle> list = Arrays.stream(add.getTitle().split("\n")).map(title -> {
            GeoTitle geoTitle = MapstructUtils.convert(bo, GeoTitle.class);
            geoTitle.setTitle(title);
            return geoTitle;
        }).toList();
        return geoTitleMapper.insertBatch(list);
    }

    /**
     * 修改GEO标题生成
     *
     * @param bo GEO标题生成
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(GeoTitleBo bo) {
        GeoTitle update = MapstructUtils.convert(bo, GeoTitle.class);
        validEntityBeforeSave(update);
        return geoTitleMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GeoTitle entity) {
        if(entity.getKeywordId() == null) {
            throw new GeoException("关键词不能为空");
        }
        if(entity.getTitle() == null) {
            throw new GeoException("标题不能为空");
        }
    }


    /**
     * 校验并批量删除GEO标题生成信息
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
        return geoTitleMapper.deleteByIds(ids) > 0;
    }

}
