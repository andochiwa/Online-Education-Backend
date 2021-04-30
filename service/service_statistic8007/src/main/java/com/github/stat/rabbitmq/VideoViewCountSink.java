package com.github.stat.rabbitmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-21:44
 */
public interface VideoViewCountSink {

    String INPUT = "videoViewCountStat";

    @Input(VideoViewCountSink.INPUT)
    SubscribableChannel input();
}
