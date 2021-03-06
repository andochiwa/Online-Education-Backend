package com.github.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/8
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.github")
public class OssApplicationMain8002 {

    public static void main(String[] args){
        SpringApplication.run(OssApplicationMain8002.class, args);
    }
}
