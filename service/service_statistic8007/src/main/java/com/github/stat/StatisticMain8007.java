package com.github.stat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HAN
 * @version 1.0
 * @create 04-20-5:04
 */
@SpringBootApplication
@ComponentScan("com.github")
@EnableFeignClients
public class StatisticMain8007 {

    public static void main(String[] args){
        SpringApplication.run(StatisticMain8007.class, args);
    }
}
