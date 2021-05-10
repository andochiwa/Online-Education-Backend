

# 前書き

SpringCloudに基づいてマイクロサービスアーキテクチャである

# [Front Page](https://github.com/andochiwa/Online-Education-Frontend-web)

# [Front Managment System](https://github.com/andochiwa/Online-Education-Frontend-admin)

# Architecture

![](https://raw.githubusercontent.com/andochiwa/Online-Education-Backend/master/image/architect.png)

# 技術スタック

|    フレームワーク     |                           使用内容                           |                          公式サイト                          |           備考            |
| :-------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :-----------------------: |
|         Nacos         |                   サービス登録、発見、配備                   |              https://nacos.io/en-us/index.html               |                           |
|       Sentinel        |                 フロー制御、Circuit Breakers                 |               https://sentinelguard.io/en-us/                | Nacosに配備する必要がある |
| Spring Cloud Gateway  | サービスゲートウェイ，Request Forward、Load Balanceなどを担当する |       https://spring.io/projects/spring-cloud-gateway        |                           |
|       OpenFeign       |      Http Client，マイクロサービスの間の転送を担当する       |      https://spring.io/projects/spring-cloud-openfeign       |                           |
| Spring Cloud Security |                 管理システムの認証を担当する                 |       https://spring.io/projects/spring-cloud-security       |                           |
|       RabbitMQ        |                Spring Cloud Streamを利用する                 | https://www.rabbitmq.com/#features<br />https://spring.pleiades.io/projects/spring-cloud-stream |                           |
|         MySQL         |                         データベース                         |                    https://www.mysql.com/                    | Nacosに配備する必要がある |
|         Druid         |            モニタリングのためのデータベースプール            |               https://github.com/alibaba/druid               | Nacosに配備する必要がある |
|         Redis         |     Cacheデータベース、MybatisやSpringのCacheを提供する      |                      https://redis.io/                       | Nacosに配備する必要がある |
|         Seata         |                       分散Transaction                        |                    http://seata.io/en-us/                    | Nacosに配備する必要がある |
|      Spring Boot      |               Springで効率で開発できるscaffold               |           https://spring.io/projects/spring-boot/            |                           |
|     Mybatis-Plus      |                 Mybatisの利用を簡略化にする                  |                 https://mp.baomidou.com/en/                  | Nacosに配備する必要がある |
|        Hutool         |                         Hutoolツール                         | https://github.com/dromara/hutool/blob/v5-master/README-EN.md |                           |

# Modules説明

Suffixの数字部分はポート番号である

## common

公共module、主に様々なConfigとツールキット

## infrastructure -- api_gateway

Spring Cloud Gateway module

## service

> service_edu

メインサービスのApi Interface

> service_oss

ファイルをAliCloudにアップロードすることを担当する

> service_vod

ビデオをAliCloudにアップロードすることを担当する

> service_cms

ホームスライドデータを処理する

> service_center

ユーザーの登録、ログイン、アップデートなど

> service_order

注文や支払いmodule

> service_statistic

データを統計する

> service_acl

認証，`common`の`spring_security`moduleと繋がる