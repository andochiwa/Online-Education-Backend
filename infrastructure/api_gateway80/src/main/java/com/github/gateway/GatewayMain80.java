package com.github.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HAN
 * @version 1.0
 * @create 04-21-4:16
 */
@SpringBootApplication
@EnableFeignClients
@ComponentScan("com.github")
public class GatewayMain80 {

    public static void main(String[] args){
        SpringApplication.run(GatewayMain80.class, args);
    }
}
