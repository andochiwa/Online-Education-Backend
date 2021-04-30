package com.github.eduservice.config;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/4
 */
@Configuration
public class EduConfiguration {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(10,
                30,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(30),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
