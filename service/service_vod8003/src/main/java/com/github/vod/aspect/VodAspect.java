package com.github.vod.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-21:23
 */
@Aspect
@Component
@EnableBinding(VodViewCountSource.class)
public class VodAspect {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private MessageChannel videoViewCountStat;

    @Before("execution(* com.github.vod.controller.VodController.getVideoPlayAuth(..))")
    public void videoViewCount() {
        threadPoolExecutor.execute(() -> {
            videoViewCountStat.send(MessageBuilder.withPayload(LocalDate.now().toString()).build());
        });
    }
}
