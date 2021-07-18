

[日本語](https://github.com/andochiwa/Online-Education-Backend/blob/master/README_JP.MD)

# 简介

基于 Spring Cloud Alibaba 的前后端分离微服务在线教育项目，整合了 Spring Security 鉴权，Seata 分布式事务，消息驱动 RabbitMQ，缓存 Redis，以及 alibaba 全套服务发现，服务配置，流量控制，监控等

# [前台页面](https://github.com/andochiwa/Online-Education-Frontend-web) [后台管理](https://github.com/andochiwa/Online-Education-Frontend-admin)

# 架构

![](https://raw.githubusercontent.com/andochiwa/Online-Education-Backend/master/image/architect.png)

# docker-compose 一键部署

> 注意：无 docker 版本请移动到另一个 branch

部署前请确保当前有 `java15`，`maven` 以及 `docker compose` 环境

```bash
git clone https://github.com/andochiwa/Online-Education-Backend.git
cd Online-Education-Backend

chmod +x wait-for-it.sh
mvn clean install -DskipTests
docker-compose up -d --build
```

如需使用`阿里云oss`，`github登录`, `发送邮箱`等服务，请连接至 mysql (宿主端口3307) 并找到`online/three_party_info`表，根据说明修改表信息，然后重启端口为 8002, 8003, 8005 的容器

|        容器        | 宿主端口: 容器内部端口 |  版本  |    用户名: 密码    |
| :----------------: | :--------------------: | :----: | :----------------: |
|       nacos        |       8848: 8848       | 1.4.0  |    nacos: nacos    |
|       mysql        |       3307: 3306       | 8.0.22 |     root: root     |
| sentinel-dashboard |       8858: 8858       | latest | sentinel: sentinel |
|       redis        |       6379: 6379       | 6.2.4  |                    |
|      rabbitmq      | 5672:5672, 15672:15672 | 3.8.19 | andochiwa: 123789  |
|       seata        |       8091: 8091       | 1.3.0  |                    |

|      其他信息      |     用户名: 密码     |
| :----------------: | :------------------: |
| 管理系统超级管理员 | andochiwa: andochiwa |



# 模块说明

```
education_parent
|- common									|| 公共模块
	|- common_utils							|| 存放各种工具
	|- service_base							|| 全局bean，全局设置等
	|- spring_security						|| spring_security专用设置, 与service_acl模块相连
|- infrastructure							|| 基础设施模块
	|- api_gateway80						|| 微服务api网关
|- service									
	|- service_edu8001						|| 提供主要服务api接口
	|- service_oss8002						|| 文件上传至阿里云oss
	|- service_vod8003						|| 视频点播，上传至阿里云oss
	|- service_cms8004						|| 控制首页数据
	|- service_center8005					|| 用户登录注册模块
	|- service_order8006					|| 订单模块
	|- service_statistic8007				|| 统计模块
	|- service_acl							|| 后台管理权限控制
```

# 使用技术栈

|         框架          |               使用内容                |                             官网                             |       备注        |  版本  |
| :-------------------: | :-----------------------------------: | :----------------------------------------------------------: | :---------------: | :----: |
|         Nacos         |         服务注册、发现、配置          |              https://nacos.io/zh-cn/index.html               |                   | 1.4.1  |
|       Sentinel        |         服务熔断、流量监控等          |               https://sentinelguard.io/zh-cn/                | 需要到Nacos上配置 |        |
| Spring Cloud Gateway  |  服务网关，负责请求转发，负载均衡等   |       https://spring.io/projects/spring-cloud-gateway        |                   |        |
|       OpenFeign       |   Http客户端，负责微服务之间的调用    |      https://spring.io/projects/spring-cloud-openfeign       |                   |        |
| Spring Cloud Security |          负责管理系统的鉴权           |       https://spring.io/projects/spring-cloud-security       |                   |        |
|       RabbitMQ        |    使用Spring Cloud Stream消息驱动    | https://www.rabbitmq.com/#features<br />https://spring.io/projects/spring-cloud-stream |                   | 3.8.14 |
|         MySQL         |                数据库                 |                    https://www.mysql.com/                    | 需要到Nacos上配置 | 8.0.22 |
|         Druid         |       为监控而生的数据库连接池        |               https://github.com/alibaba/druid               | 需要到Nacos上配置 |        |
|         Redis         | 缓存数据库，为spring、mybatis提供缓存 |                      https://redis.io/                       | 需要到Nacos上配置 | 6.2.1  |
|         Seata         |              分布式事务               |                    http://seata.io/zh-cn/                    | 需要到Nacos上配置 | 1.3.0  |
|      Spring Boot      |        Spring快速开发的脚手架         |           https://spring.io/projects/spring-boot/            |                   |        |
|     Mybatis-Plus      |           为Mybatis简化开发           |                   https://mp.baomidou.com/                   | 需要到Nacos上配置 |        |
|        Hutool         |              糊涂工具包               |                https://www.hutool.cn/docs/#/                 |                   |        |
|         java          |                                       |                                                              |                   |   15   |
|        Swagger        |             生成接口文档              |                     https://swagger.io/                      |                   | 3.0.0  |
