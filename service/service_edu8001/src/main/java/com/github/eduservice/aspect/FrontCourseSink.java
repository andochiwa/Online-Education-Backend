package com.github.eduservice.aspect;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-20:45
 */
public interface FrontCourseSink {

    String OUTPUT = "courseViewCountStat";

    @Output(FrontCourseSink.OUTPUT)
    MessageChannel output();
}
