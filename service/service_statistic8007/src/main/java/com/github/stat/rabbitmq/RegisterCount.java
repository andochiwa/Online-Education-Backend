package com.github.stat.rabbitmq;

import com.github.stat.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author HAN
 * @version 1.0
 * @create 05-04-2:10
 */
@Component
public class RegisterCount {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * 统计注册数量+1
     */
    @Bean
    public Consumer<String> statRegisterCount() {
        return date -> statisticsDailyService.registerCount(date);
    }
}
