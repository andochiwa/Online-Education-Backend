package com.github.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.acl.entity.Role;
import com.github.servicebase.cache.MybatisRedisCacheConfig;

import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HAN
 * @since 2021-04-22
 */
@CacheNamespace(implementation = MybatisRedisCacheConfig.class, eviction = MybatisRedisCacheConfig.class)
public interface RoleMapper extends BaseMapper<Role> {

}
