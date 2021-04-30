package com.github.vod.aspect;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-21:38
 */
public interface VodViewCountSource {

    String OUTPUT = "videoViewCountStat";

    @Output(VodViewCountSource.OUTPUT)
    MessageChannel output();
}
