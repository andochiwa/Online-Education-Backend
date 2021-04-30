package com.github.stat.rabbitmq;

import com.github.stat.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-17:31
 */
@Component
public class LoginCount {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * 统计登陆数量+1
     */
    @Bean
    public Consumer<String> statLoginCount() {
        return date -> statisticsDailyService.loginCount(date);
    }
}
