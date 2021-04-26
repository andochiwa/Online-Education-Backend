package com.github.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.acl.entity.RolePermission;
import com.github.servicebase.cache.MybatisRedisCacheConfig;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 * 角色权限 Mapper 接口
 * </p>
 *
 * @author HAN
 * @since 2021-04-22
 */
@CacheNamespace(implementation = MybatisRedisCacheConfig.class, eviction = MybatisRedisCacheConfig.class)
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

}
