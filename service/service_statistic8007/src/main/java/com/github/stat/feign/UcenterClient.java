package com.github.stat.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author HAN
 * @version 1.0
 * @create 04-20-5:44
 */
@FeignClient("service-center")
public interface UcenterClient {

    @GetMapping("/edu-center/stat-register/{date}")
    int statRegister(@PathVariable("date") String date);
}
