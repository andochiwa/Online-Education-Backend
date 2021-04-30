package com.github.stat.rabbitmq;

import com.github.stat.service.StatisticsDailyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-20:54
 */
@Slf4j
@Component
public class CourseViewCount {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * 统计课程数量+1
     */
    @Bean
    public Consumer<String> courseViewCountStat() {
        return date -> statisticsDailyService.courseViewCount(date);
    }
}
