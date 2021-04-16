package com.github.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/12
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.github")
public class VodApplicationMain8003 {

    public static void main(String[] args){
        SpringApplication.run(VodApplicationMain8003.class, args);
    }
}
