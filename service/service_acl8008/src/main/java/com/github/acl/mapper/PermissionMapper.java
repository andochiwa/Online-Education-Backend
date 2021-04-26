package com.github.acl.mapper;

import com.github.acl.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.servicebase.cache.MybatisRedisCacheConfig;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author HAN
 * @since 2021-04-22
 */
@CacheNamespace(implementation = MybatisRedisCacheConfig.class, eviction = MybatisRedisCacheConfig.class)
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户id查询权限
     * @param id 用户id
     * @return 权限列表
     */
    List<String> getPermissionByUserId(Long id);
}
