package com.github.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HAN
 * @version 1.0
 * @create 04-19-0:29
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.github")
@EnableFeignClients
public class OrderApplicationMain8006 {

    public static void main(String[] args){
        SpringApplication.run(OrderApplicationMain8006.class, args);
    }
}
