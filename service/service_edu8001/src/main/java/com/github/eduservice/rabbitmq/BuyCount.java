package com.github.eduservice.rabbitmq;

import com.github.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-4:58
 */
@Component
@EnableBinding(BuyCountSink.class)
public class BuyCount {

    @Autowired
    private EduCourseService eduCourseService;

    @StreamListener(BuyCountSink.INPUT)
    public void buyCountCourse(Message<Long> message) {
        eduCourseService.buyCount(message.getPayload());
    }
}
