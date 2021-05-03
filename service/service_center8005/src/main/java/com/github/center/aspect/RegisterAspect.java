package com.github.center.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author HAN
 * @version 1.0
 * @create 05-04-1:59
 */
@Aspect
@Component
@Slf4j
public class RegisterAspect {

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 统计注册人数+1
     */
    @Before("execution(* com.github.center.service.UcenterMemberService.register(..))")
    public void countRegister() {
        threadPoolExecutor.execute(() -> {
            streamBridge.send("registerCount-out-0",
                    MessageBuilder.withPayload(LocalDate.now().toString()).build());
        });
    }
}
