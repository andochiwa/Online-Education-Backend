package com.github.center.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-16:57
 */
@Component
@Aspect
@EnableBinding(Source.class)
public class LoginAspect {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private MessageChannel output;

    @Before("execution(* com.github.center.controller.UcenterMemberController.loginUser(..))")
    public void loginCount() {
        threadPoolExecutor.execute(() -> {
            output.send(MessageBuilder.withPayload(LocalDate.now().toString()).build(), 60L);
        });
    }
}
