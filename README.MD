

[日本語](https://github.com/andochiwa/Online-Education-Backend/blob/master/README_JP.MD)

# 简介

基于SpringCloud的微服务架构

# [前台页面](https://github.com/andochiwa/Online-Education-Frontend-web)

# [后台管理](https://github.com/andochiwa/Online-Education-Frontend-admin)

# 架构

![](https://raw.githubusercontent.com/andochiwa/Online-Education-Backend/master/image/architect.png)

# 使用技术栈

|         框架          |               使用内容                |                             官网                             |       备注        |
| :-------------------: | :-----------------------------------: | :----------------------------------------------------------: | :---------------: |
|         Nacos         |         服务注册、发现、配置          |              https://nacos.io/zh-cn/index.html               |                   |
|       Sentinel        |         服务熔断、流量监控等          |               https://sentinelguard.io/zh-cn/                | 需要到Nacos上配置 |
| Spring Cloud Gateway  |  服务网关，负责请求转发，负载均衡等   |       https://spring.io/projects/spring-cloud-gateway        |                   |
|       OpenFeign       |   Http客户端，负责微服务之间的调用    |      https://spring.io/projects/spring-cloud-openfeign       |                   |
| Spring Cloud Security |          负责管理系统的鉴权           |       https://spring.io/projects/spring-cloud-security       |                   |
|       RabbitMQ        |    使用Spring Cloud Stream消息驱动    | https://www.rabbitmq.com/#features<br />https://spring.io/projects/spring-cloud-stream |                   |
|         MySQL         |                数据库                 |                    https://www.mysql.com/                    | 需要到Nacos上配置 |
|         Druid         |       为监控而生的数据库连接池        |               https://github.com/alibaba/druid               | 需要到Nacos上配置 |
|         Redis         | 缓存数据库，为spring、mybatis提供缓存 |                      https://redis.io/                       | 需要到Nacos上配置 |
|         Seata         |              分布式事务               |                    http://seata.io/zh-cn/                    | 需要到Nacos上配置 |
|      Spring Boot      |        Spring快速开发的脚手架         |           https://spring.io/projects/spring-boot/            |                   |
|     Mybatis-Plus      |           为Mybatis简化开发           |                   https://mp.baomidou.com/                   | 需要到Nacos上配置 |

# 模块说明

后缀数字部分为端口号

## common

公共模块，主要是各种配置以及工具包

## infrastructure -- api_gateway

Spring Cloud Gateway模块

## service

> service_edu

主要服务api接口

> service_oss

负责将文件上传到阿里云

> service_vod

负责将视频上传到阿里云

> service_cms

首页幻灯片数据

> service_center

用户的登陆、注册、修改等

> service_order

订单支付模块，目前没有任何支付通道，所有支付请求都会直接通过

> service_statistic

统计数据

> service_acl

鉴权，与`common`中`spring_security`模块连接