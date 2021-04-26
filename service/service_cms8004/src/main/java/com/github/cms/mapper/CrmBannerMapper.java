package com.github.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.cms.entity.CrmBanner;
import com.github.servicebase.cache.MybatisRedisCacheConfig;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 * 首页banner表 Mapper 接口
 * </p>
 *
 * @author HAN
 * @since 2021-04-13
 */
@CacheNamespace(implementation = MybatisRedisCacheConfig.class, eviction = MybatisRedisCacheConfig.class)
public interface CrmBannerMapper extends BaseMapper<CrmBanner> {

}
