

# 前書き

SpringCloud に基づいてマイクロサービスアーキテクチャであり、Spring Security 認証、Seata分散トランザクション、メッセージミドルウェア RabbitMQ、キャッシュRedis、および Spring Cloud alibaba のサービス発見、サービス構成、フロー制御、回路遮断など

# [Front Page](https://github.com/andochiwa/Online-Education-Frontend-web) [Front Managment System](https://github.com/andochiwa/Online-Education-Frontend-admin)

# Architecture

![](https://raw.githubusercontent.com/andochiwa/Online-Education-Backend/master/image/architect.png)

# docker-compose 配備

> 注意：dockerがないバージョンは他の branch 参照

配備する前に部署前 `java15`，`maven` および `docker compose` 环境があることを確認してください

```bash
git clone https://github.com/andochiwa/Online-Education-Backend.git
cd Online-Education-Backend

chmod +x wait-for-it.sh
mvn clean install -DskipTests
docker-compose up -d --build
```

|      コンテナ      | host port: container port | version | username: password |
| :----------------: | :-----------------------: | :-----: | :----------------: |
|       nacos        |        8848: 8848         |  1.4.0  |    nacos: nacos    |
|       mysql        |        3307: 3306         | 8.0.22  |     root: root     |
| sentinel-dashboard |        8858: 8858         | latest  | sentinel: sentinel |
|       redis        |        6379: 6379         |  6.2.4  |                    |
|      rabbitmq      |  5672:5672, 15672:15672   | 3.8.19  | andochiwa: 123789  |
|       seata        |        8091: 8091         |  1.3.0  |                    |

|                            その他                            |  username: password  |
| :----------------------------------------------------------: | :------------------: |
|                  管理システムスーパー管理者                  | andochiwa: andochiwa |
| Druidデータベース接続プール監視SQLページ, ip:port/druidをアクセスしてください, <br />(ipはサーバーのip, portは監視したいサービスのport) |      root: root      |
| swaggerインターフェースドキュメント<br />ip:port/swagger-ui/をアクセスしてください |                      |

Module 説明

```
education_parent
|- common                               || 公共Module
	|- common_utils                     || いろんなツールを保存する
	|- service_base                     || グローバルBean, configなど
	|- spring_security                  || spring_security専用, service_acl Moduleと繋がる
|- infrastructure                       || インフラModule
	|- api_gateway80                    || マイクロサービスAPI Gateway
|- service									
	|- service_edu8001                  || メインサービスのApiを提供
	|- service_oss8002                  || alibaba cloud ossにファイルをアップロード
	|- service_vod8003                  || alibaba cloud ossにビデオをアップロード
	|- service_cms8004                  || ホームページのデータ管理
	|- service_center8005               || ユーザーログイン登録
	|- service_order8006                || 注文Module
	|- service_statistic8007            || 統計Module
	|- service_acl                      || 管理システムの認証、承認
```

# 技術スタック

|    フレームワーク     |                           使用内容                           |                          公式サイト                          |           備考            | version |
| :-------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :-----------------------: | :-----: |
|         Nacos         |                   サービス登録、発見、配備                   |              https://nacos.io/en-us/index.html               |                           |  1.4.1  |
|       Sentinel        |                 フロー制御、Circuit Breakers                 |               https://sentinelguard.io/en-us/                | Nacosに配備する必要がある |         |
| Spring Cloud Gateway  | サービスゲートウェイ，Request Forward、Load Balanceなどを担当する |       https://spring.io/projects/spring-cloud-gateway        |                           |         |
|       OpenFeign       |      Http Client，マイクロサービスの間の転送を担当する       |      https://spring.io/projects/spring-cloud-openfeign       |                           |         |
| Spring Cloud Security |                 管理システムの認証を担当する                 |       https://spring.io/projects/spring-cloud-security       |                           |         |
|       RabbitMQ        |                Spring Cloud Streamを利用する                 | https://www.rabbitmq.com/#features<br />https://spring.pleiades.io/projects/spring-cloud-stream |                           | 3.8.14  |
|         MySQL         |                         データベース                         |                    https://www.mysql.com/                    | Nacosに配備する必要がある | 8.0.22  |
|         Druid         |            モニタリングのためのデータベースプール            |               https://github.com/alibaba/druid               | Nacosに配備する必要がある |         |
|         Redis         |     Cacheデータベース、MybatisやSpringのCacheを提供する      |                      https://redis.io/                       | Nacosに配備する必要がある |  6.2.1  |
|         Seata         |                       分散Transaction                        |                    http://seata.io/en-us/                    | Nacosに配備する必要がある |  1.3.0  |
|      Spring Boot      |               Springで効率で開発できるscaffold               |           https://spring.io/projects/spring-boot/            |                           |         |
|     Mybatis-Plus      |                 Mybatisの利用を簡略化にする                  |                 https://mp.baomidou.com/en/                  | Nacosに配備する必要がある |         |
|        Hutool         |                         Hutoolツール                         | https://github.com/dromara/hutool/blob/v5-master/README-EN.md |                           |         |
|         java          |                                                              |                                                              |                           |   15    |
|        Swagger        |                インターフェース ドキュメント                 |                     https://swagger.io/                      |    https://swagger.io/    |  3.0.0  |
