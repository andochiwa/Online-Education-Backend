package com.github.servicebase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/4
 */
@Configuration
@EnableSwagger2 // swagger注解
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(apiInfo())
                .select()
                .paths(Predicate.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicate.not(PathSelectors.regex("/error.*")))
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("课程中心Api文档")
                .description("本文档定义了课程中心微服务接口定义")
                .version("1.0")
                .contact(new Contact("HAN", null, "a1066079469@gmail.com"))
                .build();
    }
}
