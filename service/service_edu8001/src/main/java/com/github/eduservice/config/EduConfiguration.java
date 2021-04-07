package com.github.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/4
 */
@Configuration
@MapperScan("com.github.eduservice.mapper")
public class EduConfiguration {
}
