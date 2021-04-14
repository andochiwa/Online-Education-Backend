package com.github.msm;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/15
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@ComponentScan(basePackages = "com.github")
public class MsmApplicationMain8005 {

    public static void main(String[] args){
        SpringApplication.run(MsmApplicationMain8005.class, args);
    }
}
