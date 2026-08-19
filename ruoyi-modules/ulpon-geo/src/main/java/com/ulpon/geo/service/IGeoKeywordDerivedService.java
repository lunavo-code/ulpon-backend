package com.ulpon.geo.service;

import com.ulpon.geo.domain.vo.GeoKeywordDerivedVo;
import com.ulpon.geo.domain.bo.GeoKeywordDerivedBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 派生标题管理Service接口
 *
 * @author Ulpon
 * @date 2026-08-20 06:16:32
 */
public interface IGeoKeywordDerivedService {

    /**
     * 查询派生标题管理
     *
     * @param derivedId 主键
     * @return 派生标题管理
     */
    GeoKeywordDerivedVo queryById(Long derivedId);

    /**
     * 分页查询派生标题管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 派生标题管理分页列表
     */
    PageResult<GeoKeywordDerivedVo> queryPageList(GeoKeywordDerivedBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的派生标题管理列表
     *
     * @param bo 查询条件
     * @return 派生标题管理列表
     */
    List<GeoKeywordDerivedVo> queryList(GeoKeywordDerivedBo bo);


    /**
     * 新增派生标题管理
     *
     * @param bo 派生标题管理
     * @return 是否新增成功
     */
    Boolean insertByBo(GeoKeywordDerivedBo bo);

    /**
     * 修改派生标题管理
     *
     * @param bo 派生标题管理
     * @return 是否修改成功
     */
    Boolean updateByBo(GeoKeywordDerivedBo bo);



    /**
     * 校验并批量删除派生标题管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
