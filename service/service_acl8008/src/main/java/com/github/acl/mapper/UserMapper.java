package com.github.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.security.entity.User;
import com.github.servicebase.cache.MybatisRedisCacheConfig;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author HAN
 * @since 2021-04-22
 */
@CacheNamespace(implementation = MybatisRedisCacheConfig.class, eviction = MybatisRedisCacheConfig.class)
public interface UserMapper extends BaseMapper<User> {

}
