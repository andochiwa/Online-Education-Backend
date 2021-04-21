package com.github.acl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HAN
 * @version 1.0
 * @create 04-22-0:54
 */
@SpringBootApplication
@ComponentScan("com.github")
@EnableFeignClients
public class AclApplicationMain8008 {

    public static void main(String[] args){
        SpringApplication.run(AclApplicationMain8008.class, args);
    }
}
