package com.github.oss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.oss.properties.ConstantProperties;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HAN
 * @version 1.0
 * @create 04-16-21:36
 */
@Mapper
public interface OssMapper extends BaseMapper<ConstantProperties> {
}
