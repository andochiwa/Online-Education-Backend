package com.github.eduservice.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-4:58
 */
@Component
@EnableBinding(Sink.class)
@Slf4j
public class BuyCount {

    @StreamListener(Sink.INPUT)
    public void butCount(Message<String> message) {
        log.info("buy count input ==== " + message.getPayload());
    }
}
