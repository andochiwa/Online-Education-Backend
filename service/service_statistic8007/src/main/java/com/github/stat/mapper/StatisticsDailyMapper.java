package com.github.stat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.servicebase.cache.MybatisRedisCacheConfig;
import com.github.stat.entity.StatisticsDaily;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 * @author HAN
 * @since 2021-04-20
 */
@CacheNamespace(implementation = MybatisRedisCacheConfig.class, eviction = MybatisRedisCacheConfig.class)
public interface StatisticsDailyMapper extends BaseMapper<StatisticsDaily> {

    void loginCount(String date);

    void courseViewCount(String date);
}
