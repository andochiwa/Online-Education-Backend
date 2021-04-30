package com.github.stat.rabbitmq;

import com.github.stat.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-21:44
 */
@EnableBinding(VideoViewCountSink.class)
public class VideoViewCount {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @StreamListener(VideoViewCountSink.INPUT)
    public void videoViewCount(Message<String> message) {
        String date = message.getPayload();
        statisticsDailyService.videoViewCount(date);
    }
}
