package com.github.stat.rabbitmq;

import com.github.stat.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-17:31
 */
@EnableBinding(LoginCountSink.class)
public class LoginCount {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @StreamListener(LoginCountSink.INPUT)
    public void loginCount(Message<String> message) {
        String date = message.getPayload();
        statisticsDailyService.loginCount(date);
    }
}