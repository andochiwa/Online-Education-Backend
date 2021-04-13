package com.github.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/13
 */
@SpringBootConfiguration
@EnableFeignClients
@ComponentScan(basePackages = "com.github")
public class CmsApplicationMain8004 {

    public static void main(String[] args){
        SpringApplication.run(CmsApplicationMain8004.class, args);
    }
}
