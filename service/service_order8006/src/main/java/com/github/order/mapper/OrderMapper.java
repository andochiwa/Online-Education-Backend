package com.github.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.order.entity.Order;
import com.github.servicebase.cache.MybatisRedisCacheConfig;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author HAN
 * @since 2021-04-19
 */
@CacheNamespace(implementation = MybatisRedisCacheConfig.class, eviction = MybatisRedisCacheConfig.class)
public interface OrderMapper extends BaseMapper<Order> {

}
