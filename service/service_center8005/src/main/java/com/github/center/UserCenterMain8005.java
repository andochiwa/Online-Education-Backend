package com.github.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/15
 */
@SpringBootApplication
@ComponentScan("com.github")
@EnableFeignClients
public class UserCenterMain8005 {

    public static void main(String[] args){
        SpringApplication.run(UserCenterMain8005.class, args);
    }
}
