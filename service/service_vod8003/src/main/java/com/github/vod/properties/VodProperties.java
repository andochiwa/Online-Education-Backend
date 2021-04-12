package com.github.vod.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/12
 */
@Data
@Component
public class VodProperties {
    @Value("${aliyun.vod.file.keyId}")
    private String keyId;

    @Value("${aliyun.vod.file.keySecret}")
    private String keySecret;
}
