package com.github.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.order.entity.PayLog;
import com.github.servicebase.cache.MybatisRedisCacheConfig;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 * 支付日志表 Mapper 接口
 * </p>
 *
 * @author HAN
 * @since 2021-04-19
 */
@CacheNamespace(implementation = MybatisRedisCacheConfig.class, eviction = MybatisRedisCacheConfig.class)
public interface PayLogMapper extends BaseMapper<PayLog> {

}
