package com.github.center.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author HAN
 * @version 1.0
 * @create 04-16-16:01
 */
@Configuration
public class BeanConfig {

    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(
                5,
                5,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

}
