package com.github.eduservice.feign.fallback;

import com.github.eduservice.feign.VodClient;
import com.github.utils.ResultCommon;
import org.springframework.stereotype.Component;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/13
 */
@Component
public class VodClientFallback implements VodClient {
    @Override
    public ResultCommon deleteVideo(String id) {
        return ResultCommon.fail().setMessage("删除视频熔断失败");
    }
}
