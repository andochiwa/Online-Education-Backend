package com.github.eduservice.mapper;

import com.github.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.servicebase.cache.MybatisRedisCacheConfig;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author HAN
 * @since 2021-05-01
 */
@CacheNamespace(implementation = MybatisRedisCacheConfig.class, eviction = MybatisRedisCacheConfig.class)
public interface EduCommentMapper extends BaseMapper<EduComment> {

}
