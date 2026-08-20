package com.ulpon.geo.service;

import com.ulpon.geo.domain.vo.GeoKeywordVo;
import com.ulpon.geo.domain.bo.GeoKeywordBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 核心词管理Service接口
 *
 * @author Ulpon
 * @date 2026-08-20 18:03:06
 */
public interface IGeoKeywordService {

    /**
     * 查询核心词管理
     *
     * @param keywordId 主键
     * @return 核心词管理
     */
    GeoKeywordVo queryById(Long keywordId);

    /**
     * 查询核心词管理列表
     *
     * @param keywordIdList 主键列表
     * @return 核心词管理列表
     */
    List<GeoKeywordVo> queryByIds(Collection<Long> keywordIdList);

    /**
     * 查询核心词管理map
     *
     * @param keywordIdList 主键列表
     * @return 核心词管理map
     */
    default Map<Long, GeoKeywordVo> queryMapByIds(Collection<Long> keywordIdList){
        return queryByIds(keywordIdList).stream().collect(Collectors.toMap(GeoKeywordVo::getKeywordId, v -> v));
    }

    /**
     * 分页查询核心词管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 核心词管理分页列表
     */
    PageResult<GeoKeywordVo> queryPageList(GeoKeywordBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的核心词管理列表
     *
     * @param bo 查询条件
     * @return 核心词管理列表
     */
    List<GeoKeywordVo> queryList(GeoKeywordBo bo);


    /**
     * 新增核心词管理
     *
     * @param bo 核心词管理
     * @return 是否新增成功
     */
    Boolean insertByBo(GeoKeywordBo bo);

    /**
     * 修改核心词管理
     *
     * @param bo 核心词管理
     * @return 是否修改成功
     */
    Boolean updateByBo(GeoKeywordBo bo);



    /**
     * 校验并批量删除核心词管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
