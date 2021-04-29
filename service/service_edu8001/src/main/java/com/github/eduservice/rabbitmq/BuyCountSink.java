package com.github.eduservice.rabbitmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-6:44
 */
public interface BuyCountSink {

    String INPUT = "buyCountCourse";

    //接收消息，使用SubscribableChannel
    @Input(BuyCountSink.INPUT)
    SubscribableChannel input();
}
