package com.github.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/4
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.github")
public class EduServiceMain8001 {

    public static void main(String[] args){
        SpringApplication.run(EduServiceMain8001.class, args);
    }
}
