package com.github.stat.rabbitmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-17:51
 */
public interface LoginCountSink {

    String INPUT = "StatLoginCount";

    @Input(LoginCountSink.INPUT)
    SubscribableChannel input();
}
