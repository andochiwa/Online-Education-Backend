package com.github.stat.rabbitmq;

import com.github.stat.service.StatisticsDailyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-20:54
 */
@EnableBinding(CourseViewCountSink.class)
@Slf4j
public class CourseViewCount {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * 统计课程数量+1
     * @param message 日期
     */
    @StreamListener(CourseViewCountSink.INPUT)
    public void courseViewCount(Message<String> message) {
        String date = message.getPayload();
        statisticsDailyService.courseViewCount(date);
    }
}
