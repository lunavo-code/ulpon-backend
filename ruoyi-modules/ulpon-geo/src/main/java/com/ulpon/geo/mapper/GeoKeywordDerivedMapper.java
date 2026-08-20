package com.ulpon.geo.mapper;

import com.ulpon.geo.domain.GeoKeywordDerived;
import com.ulpon.geo.domain.vo.GeoKeywordDerivedVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 派生标题管理Mapper接口
 *
 * @author Ulpon
 * @date 2026-08-20 18:03:07
 */
@Mapper
public interface GeoKeywordDerivedMapper extends BaseMapperPlus<GeoKeywordDerived, GeoKeywordDerivedVo> {

}
