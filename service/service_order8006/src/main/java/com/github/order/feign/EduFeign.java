package com.github.order.feign;

import com.github.utils.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author HAN
 * @version 1.0
 * @create 04-19-2:40
 */
@FeignClient("service-edu")
public interface EduFeign {

    /**
     * 查询课程信息
     * @param courseId 课程id
     */
    @GetMapping("/eduservice/front-course/feign/{id}")
    ResultCommon getCourseInfoCommon(@PathVariable("id") Long courseId);
}
