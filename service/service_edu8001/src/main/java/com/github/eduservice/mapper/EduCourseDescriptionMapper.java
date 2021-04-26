package com.github.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.eduservice.entity.EduCourseDescription;
import com.github.servicebase.cache.MybatisRedisCacheConfig;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 * 课程简介 Mapper 接口
 * </p>
 *
 * @author HAN
 * @since 2021-04-09
 */
@CacheNamespace(implementation = MybatisRedisCacheConfig.class, eviction = MybatisRedisCacheConfig.class)
public interface EduCourseDescriptionMapper extends BaseMapper<EduCourseDescription> {

}
