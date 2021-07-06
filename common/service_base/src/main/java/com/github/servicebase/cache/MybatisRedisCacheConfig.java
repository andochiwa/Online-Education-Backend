package com.github.servicebase.cache;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author HAN
 * @version 1.0
 * @create 04-27-0:57
 */
@Slf4j
public class MybatisRedisCacheConfig implements Cache {

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private RedisTemplate<String, Object> redisTemplate;

    private String id;

    public MybatisRedisCacheConfig(String id) {
        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    private void getRedisTemplate() {
        if (redisTemplate == null) {
            this.redisTemplate = SpringUtil.getBean("redisTemplate");
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            getRedisTemplate();
            log.info("save cache to redis" + this.id);
            // 保存数据设置为一天
            redisTemplate.opsForHash().put(this.id, key, value);
            redisTemplate.expire(this.id, 1, TimeUnit.DAYS);
        }
    }

    @Override
    public Object getObject(Object key) {
        try {
            if (!ObjectUtils.isEmpty(key)) {
                getRedisTemplate();
                return redisTemplate.opsForHash().get(this.id, key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        if (!ObjectUtils.isEmpty(key)) {
            getRedisTemplate();
            log.info("remove redis cache");
            redisTemplate.opsForHash().delete(this.id, key);
        }
        return null;
    }

    @Override
    public void clear() {
        getRedisTemplate();
        log.info("clear redis cache" + this.id);
        redisTemplate.delete(this.id);
    }

    @Override
    public int getSize() {
        getRedisTemplate();
        Long size = redisTemplate.execute(RedisServerCommands::dbSize);
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
