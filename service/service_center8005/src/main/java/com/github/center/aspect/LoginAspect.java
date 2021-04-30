package com.github.center.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.time.LocalDate;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-16:57
 */
@Aspect
@EnableBinding(Source.class)
public class LoginAspect {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private MessageChannel output;

    @Pointcut("execution(* com.github.center.controller.UcenterMemberController.loginUser(..))")
    public void loginUser() {}

    @Pointcut("execution(* com.github.center.controller.GithubController.loginUser(..))")
    public void loginGithub() {}

    /**
     * 统计登陆次数+1
     */
    @Before("loginUser() || loginGithub()")
    public void loginCount() {
        threadPoolExecutor.execute(() -> {
            output.send(MessageBuilder.withPayload(LocalDate.now().toString()).build(), 60L);
        });
    }
}
