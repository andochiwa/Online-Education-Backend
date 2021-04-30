package com.github.eduservice.aspect;

import com.github.eduservice.service.EduCourseService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.time.LocalDate;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author HAN
 * @version 1.0
 * @create 04-30-1:33
 */
@Aspect
@EnableBinding(FrontCourseSink.class)
public class FrontCourseAspect {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private MessageChannel courseViewCountStat;

    /**
     * 切面，切入课程信息之后，计算浏览数量
     */
    @After("execution(* com.github.eduservice.controller.FrontCourseController.getCourseInfo(..))")
    public void buyCount(JoinPoint joinPoint) {
        threadPoolExecutor.execute(() -> {
            Object[] args = joinPoint.getArgs();
            eduCourseService.viewCount((Long) args[0]);
            // 发送到消息队列统计数量
            courseViewCountStat.send(MessageBuilder.withPayload(LocalDate.now().toString()).build());
        });
    }

}
