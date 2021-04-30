package com.github.stat.schedule;

import com.github.stat.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author HAN
 * @version 1.0
 * @create 04-20-22:00
 */
@Component
public class StatCountSchedule {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @Scheduled(cron = "1 0 0 * * ?")
    public void count() {
        statisticsDailyService.countRegister(LocalDate.now().plusDays(-1L).toString());
    }
}
