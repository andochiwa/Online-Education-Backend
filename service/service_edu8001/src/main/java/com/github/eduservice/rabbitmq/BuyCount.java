package com.github.eduservice.rabbitmq;

import com.github.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-4:58
 */
@Component
public class BuyCount {

    @Autowired
    private EduCourseService eduCourseService;

    @Bean
    public Consumer<Long> buyCountCourse() {
        return courseId -> eduCourseService.buyCount(courseId);
    }
}
