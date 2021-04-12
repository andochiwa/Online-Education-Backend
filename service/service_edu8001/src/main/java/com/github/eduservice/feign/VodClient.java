package com.github.eduservice.feign;

import com.github.eduservice.feign.fallback.VodClientFallback;
import com.github.utils.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/13
 */
@FeignClient(value = "service-vod", fallback = VodClientFallback.class)
public interface VodClient {

    @DeleteMapping("/edu-vod/video/{id}")
    ResultCommon deleteVideo(@PathVariable("id") String id);
}
