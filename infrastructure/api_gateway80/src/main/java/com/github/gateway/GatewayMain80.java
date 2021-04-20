package com.github.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author HAN
 * @version 1.0
 * @create 04-21-4:16
 */
@SpringBootApplication
@EnableFeignClients
public class GatewayMain80 {

    public static void main(String[] args){
        SpringApplication.run(GatewayMain80.class, args);
    }
}
