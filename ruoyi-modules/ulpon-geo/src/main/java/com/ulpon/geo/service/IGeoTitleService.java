package com.ulpon.geo.service;

import com.ulpon.geo.domain.vo.GeoTitleVo;
import com.ulpon.geo.domain.bo.GeoTitleBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * GEO标题生成Service接口
 *
 * @author Ulpon
 * @date 2026-08-09 17:39:11
 */
public interface IGeoTitleService {

    /**
     * 查询GEO标题生成
     *
     * @param id 主键
     * @return GEO标题生成
     */
    GeoTitleVo queryById(Long id);

    /**
     * 分页查询GEO标题生成列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return GEO标题生成分页列表
     */
    PageResult<GeoTitleVo> queryPageList(GeoTitleBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的GEO标题生成列表
     *
     * @param bo 查询条件
     * @return GEO标题生成列表
     */
    List<GeoTitleVo> queryList(GeoTitleBo bo);


    /**
     * 新增GEO标题生成
     *
     * @param bo GEO标题生成
     * @return 是否新增成功
     */
    Boolean insertByBo(GeoTitleBo bo);

    /**
     * 修改GEO标题生成
     *
     * @param bo GEO标题生成
     * @return 是否修改成功
     */
    Boolean updateByBo(GeoTitleBo bo);



    /**
     * 校验并批量删除GEO标题生成信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
