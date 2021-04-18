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
@FeignClient("service-center")
public interface UcenterFeign {

    /**
     * 根据id获取用户信息
     * @param id 用户id
     */
    @GetMapping("/edu-center/user-info/{id}")
    ResultCommon infoUserById(@PathVariable("id") String id);
}
