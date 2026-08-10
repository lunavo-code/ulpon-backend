package com.ulpon.geo.mapper;

import com.ulpon.geo.domain.GeoTitle;
import com.ulpon.geo.domain.vo.GeoTitleVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * GEO标题生成Mapper接口
 *
 * @author Ulpon
 * @date 2026-08-09 17:39:11
 */
@Mapper
public interface GeoTitleMapper extends BaseMapperPlus<GeoTitle, GeoTitleVo> {

}
