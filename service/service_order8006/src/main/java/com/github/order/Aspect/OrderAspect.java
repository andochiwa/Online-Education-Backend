package com.github.order.Aspect;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-2:09
 */
@Component
@Aspect
@Slf4j
@EnableBinding(Source.class)
public class OrderAspect {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private MessageChannel output;

    @Before("execution(* com.github.order.controller.PayLogController.getOrderStatus(..)) ")
    public void payCount() {
        threadPoolExecutor.execute(() -> {
            log.info("send message to rabbitmq");
            output.send(MessageBuilder.withPayload(IdUtil.fastUUID()).build(), 30L);
        });
    }
}
