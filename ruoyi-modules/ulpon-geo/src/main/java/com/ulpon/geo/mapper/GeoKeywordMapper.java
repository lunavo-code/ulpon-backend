package com.ulpon.geo.mapper;

import com.ulpon.geo.domain.GeoKeyword;
import com.ulpon.geo.domain.vo.GeoKeywordVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 核心词管理Mapper接口
 *
 * @author Ulpon
 * @date 2026-08-20 18:03:06
 */
@Mapper
public interface GeoKeywordMapper extends BaseMapperPlus<GeoKeyword, GeoKeywordVo> {

}
