package com.github.center.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-16:57
 */
@Aspect
@Component
public class LoginAspect {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private StreamBridge streamBridge;

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
            streamBridge.send("loginCount-out-0",
                    MessageBuilder.withPayload(LocalDate.now().toString()).build());
        });
    }
}
