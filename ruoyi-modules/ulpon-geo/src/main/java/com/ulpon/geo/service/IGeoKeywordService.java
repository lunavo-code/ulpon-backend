package com.ulpon.geo.service;

import com.ulpon.geo.domain.vo.GeoKeywordVo;
import com.ulpon.geo.domain.bo.GeoKeywordBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 核心关键词Service接口
 *
 * @author Ulpon
 * @date 2026-08-09 12:53:08
 */
public interface IGeoKeywordService {

    /**
     * 查询核心关键词
     *
     * @param id 主键
     * @return 核心关键词
     */
    GeoKeywordVo queryById(Long id);

    /**
     * 分页查询核心关键词列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 核心关键词分页列表
     */
    PageResult<GeoKeywordVo> queryPageList(GeoKeywordBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的核心关键词列表
     *
     * @param bo 查询条件
     * @return 核心关键词列表
     */
    List<GeoKeywordVo> queryList(GeoKeywordBo bo);


    /**
     * 新增核心关键词
     *
     * @param bo 核心关键词
     * @return 是否新增成功
     */
    Boolean insertByBo(GeoKeywordBo bo);

    /**
     * 修改核心关键词
     *
     * @param bo 核心关键词
     * @return 是否修改成功
     */
    Boolean updateByBo(GeoKeywordBo bo);



    /**
     * 校验并批量删除核心关键词信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
