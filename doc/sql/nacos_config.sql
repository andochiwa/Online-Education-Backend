/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.22 : Database - nacos_config
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nacos_config` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `nacos_config`;

/*Table structure for table `config_info` */

DROP TABLE IF EXISTS `config_info`;

CREATE TABLE `config_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=292 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

/*Data for the table `config_info` */

insert  into `config_info`(`id`,`data_id`,`group_id`,`content`,`md5`,`gmt_create`,`gmt_modified`,`src_user`,`src_ip`,`app_name`,`tenant_id`,`c_desc`,`c_use`,`effect`,`type`,`c_schema`) values 
(180,'redis.yml','DEFAULT_GROUP','spring:\n  redis:\n    host: redis\n    port: 6379\n    # 连接超时时间（毫秒）\n    timeout: 150000\n    lettuce:\n      pool:\n        # 连接池最大连接数（使用负值表示没有限制）\n        max-active: 30\n        # 连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: 2\n        # 连接池中的最大空闲连接\n        max-idle: 10\n        # 连接池中的最小空闲连接\n        min-idle: 0','b9853c3c8676098db12164214cf2dd3d','2021-04-29 01:11:29','2021-07-18 04:07:32',NULL,'0:0:0:0:0:0:0:1','','b909e98d-d1c4-4319-879a-758981c15700','redis配置','','','yaml',''),
(181,'mysql.yml','DEFAULT_GROUP','spring:\n  datasource:\n    username: root\n    password: root\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://mysql:3306/online?serverTimezone=Japan&useUnicode=true&characterEncoding=UTF-8\n    druid:\n      filters: stat,wall # 底层开启功能，stat（sql监控），wall（防火墙）\n      max-active: 12\n      stat-view-servlet: # 配置监控页功能\n        enabled: true\n        login-username: root\n        login-password: root\n        url-pattern: /druid/*\n        reset-enable: false\n        allow:\n      web-stat-filter: # 监控web\n        exclusions: \'*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*\' # 可以不写，默认配置\n        enabled: true\n        url-pattern: /*\n      aop-patterns: com.github.* #监控SpringBean\n      filter: # 对上面filters里面的stat的详细配置\n        stat:\n          slow-sql-millis: 1000\n          log-slow-sql: true\n        wall:\n          enabled: true','36f03af34f8a112ed64e8ad6a8a4e6a9','2021-04-29 01:14:35','2021-07-18 04:07:56',NULL,'0:0:0:0:0:0:0:1','','b909e98d-d1c4-4319-879a-758981c15700','mysql配置','','','yaml',''),
(182,'mybatis-plus.yml','DEFAULT_GROUP','mybatis-plus:\r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\n    cache-enabled: true\r\n    useDeprecatedExecutor: false # Since it has not been removed, so continue to use(default true to false)','3b3b1ccf168385af8035ba3c28f7ee6e','2021-04-29 01:15:21','2021-04-29 01:15:21',NULL,'0:0:0:0:0:0:0:1','','b909e98d-d1c4-4319-879a-758981c15700','mybatis-plus配置',NULL,NULL,'yaml',NULL),
(183,'nacos-sentinel.yml','DEFAULT_GROUP','spring:\n  cloud:\n    nacos:\n      server-addr: nacos:8848\n      discovery:\n        namespace: b909e98d-d1c4-4319-879a-758981c15700\n    sentinel:\n      transport:\n        dashboard: sentinel:8858\n        port: 8719','363ca26fd60a38325d06da0068f9211e','2021-04-29 01:20:20','2021-07-18 04:09:00',NULL,'0:0:0:0:0:0:0:1','','b909e98d-d1c4-4319-879a-758981c15700','nacos 和 sentinel配置','','','text',''),
(188,'feign-sentinel.yml','DEFAULT_GROUP','feign:\r\n  sentinel:\r\n    enabled: true','b17e5588d7e01974c0e0635ee30a37dd','2021-04-29 02:02:45','2021-04-29 02:02:45',NULL,'0:0:0:0:0:0:0:1','','b909e98d-d1c4-4319-879a-758981c15700','feign-sentinel',NULL,NULL,'yaml',NULL),
(192,'rabbitmq.yml','DEFAULT_GROUP','spring:\n  cloud:\n    stream:\n      binders: # 配置要绑定的rabbitmq的服务信息\n        defaultRabbit: # 表示定义的名称，用于与Binding整合\n          type: rabbit # 消息组件类型\n          environment: # 设置rabbitmq的相关环境配置\n            spring:\n              rabbitmq:\n                host: rabbitmq\n                port: 5672\n                username: andochiwa\n                password: 123789','4a2e006e8b5aaf0f48a06d423f895094','2021-04-30 03:49:46','2021-07-18 04:22:18',NULL,'0:0:0:0:0:0:0:1','','b909e98d-d1c4-4319-879a-758981c15700','rabbitmq配置','','','yaml',''),
(195,'transport.type','SEATA_GROUP','TCP','b136ef5f6a01d816991fe3cf7a6ac763','2021-05-02 03:01:52','2021-05-02 03:01:52',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(196,'transport.server','SEATA_GROUP','NIO','b6d9dfc0fb54277321cebc0fff55df2f','2021-05-02 03:01:52','2021-05-02 03:01:52',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(197,'transport.heartbeat','SEATA_GROUP','true','b326b5062b2f0e69046810717534cb09','2021-05-02 03:01:53','2021-05-02 03:01:53',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(198,'transport.enableClientBatchSendRequest','SEATA_GROUP','false','68934a3e9455fa72420237eb05902327','2021-05-02 03:01:53','2021-05-02 03:01:53',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(199,'transport.threadFactory.bossThreadPrefix','SEATA_GROUP','NettyBoss','0f8db59a3b7f2823f38a70c308361836','2021-05-02 03:01:53','2021-05-02 03:01:53',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(200,'transport.threadFactory.workerThreadPrefix','SEATA_GROUP','NettyServerNIOWorker','a78ec7ef5d1631754c4e72ae8a3e9205','2021-05-02 03:01:53','2021-05-02 03:01:53',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(201,'transport.threadFactory.serverExecutorThreadPrefix','SEATA_GROUP','NettyServerBizHandler','11a36309f3d9df84fa8b59cf071fa2da','2021-05-02 03:01:54','2021-05-02 03:01:54',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(202,'transport.threadFactory.shareBossWorker','SEATA_GROUP','false','68934a3e9455fa72420237eb05902327','2021-05-02 03:01:54','2021-05-02 03:01:54',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(203,'transport.threadFactory.clientSelectorThreadPrefix','SEATA_GROUP','NettyClientSelector','cd7ec5a06541e75f5a7913752322c3af','2021-05-02 03:01:54','2021-05-02 03:01:54',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(204,'transport.threadFactory.clientSelectorThreadSize','SEATA_GROUP','1','c4ca4238a0b923820dcc509a6f75849b','2021-05-02 03:01:54','2021-05-02 03:01:54',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(205,'transport.threadFactory.clientWorkerThreadPrefix','SEATA_GROUP','NettyClientWorkerThread','61cf4e69a56354cf72f46dc86414a57e','2021-05-02 03:01:55','2021-05-02 03:01:55',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(206,'transport.threadFactory.bossThreadSize','SEATA_GROUP','1','c4ca4238a0b923820dcc509a6f75849b','2021-05-02 03:01:55','2021-05-02 03:01:55',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(207,'transport.threadFactory.workerThreadSize','SEATA_GROUP','default','c21f969b5f03d33d43e04f8f136e7682','2021-05-02 03:01:55','2021-05-02 03:01:55',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(208,'transport.shutdown.wait','SEATA_GROUP','3','eccbc87e4b5ce2fe28308fd9f2a7baf3','2021-05-02 03:01:55','2021-05-02 03:01:55',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(209,'service.vgroupMapping.online_edu_tx','SEATA_GROUP','default','c21f969b5f03d33d43e04f8f136e7682','2021-05-02 03:01:55','2021-05-02 03:01:55',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(210,'service.default.grouplist','SEATA_GROUP','127.0.0.1:8091','c32ce0d3e264525dcdada751f98143a3','2021-05-02 03:01:56','2021-05-02 03:01:56',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(211,'service.enableDegrade','SEATA_GROUP','false','68934a3e9455fa72420237eb05902327','2021-05-02 03:01:56','2021-05-02 03:01:56',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(212,'service.disableGlobalTransaction','SEATA_GROUP','false','68934a3e9455fa72420237eb05902327','2021-05-02 03:01:57','2021-05-02 03:01:57',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(213,'client.rm.asyncCommitBufferLimit','SEATA_GROUP','10000','b7a782741f667201b54880c925faec4b','2021-05-02 03:01:57','2021-05-02 03:01:57',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(214,'client.rm.lock.retryInterval','SEATA_GROUP','10','d3d9446802a44259755d38e6d163e820','2021-05-02 03:01:57','2021-05-02 03:01:57',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(215,'client.rm.lock.retryTimes','SEATA_GROUP','30','34173cb38f07f89ddbebc2ac9128303f','2021-05-02 03:01:58','2021-05-02 03:01:58',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(216,'client.rm.lock.retryPolicyBranchRollbackOnConflict','SEATA_GROUP','true','b326b5062b2f0e69046810717534cb09','2021-05-02 03:01:58','2021-05-02 03:01:58',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(217,'client.rm.reportRetryCount','SEATA_GROUP','5','e4da3b7fbbce2345d7772b0674a318d5','2021-05-02 03:01:58','2021-05-02 03:01:58',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(218,'client.rm.tableMetaCheckEnable','SEATA_GROUP','false','68934a3e9455fa72420237eb05902327','2021-05-02 03:01:59','2021-05-02 03:01:59',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(219,'client.rm.tableMetaCheckerInterval','SEATA_GROUP','60000','2b4226dd7ed6eb2d419b881f3ae9c97c','2021-05-02 03:01:59','2021-05-02 03:01:59',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(220,'client.rm.sqlParserType','SEATA_GROUP','druid','3d650fb8a5df01600281d48c47c9fa60','2021-05-02 03:01:59','2021-05-02 03:01:59',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(221,'client.rm.reportSuccessEnable','SEATA_GROUP','false','68934a3e9455fa72420237eb05902327','2021-05-02 03:01:59','2021-05-02 03:01:59',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(222,'client.rm.sagaBranchRegisterEnable','SEATA_GROUP','false','68934a3e9455fa72420237eb05902327','2021-05-02 03:02:00','2021-05-02 03:02:00',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(223,'client.tm.commitRetryCount','SEATA_GROUP','5','e4da3b7fbbce2345d7772b0674a318d5','2021-05-02 03:02:00','2021-05-02 03:02:00',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(224,'client.tm.rollbackRetryCount','SEATA_GROUP','5','e4da3b7fbbce2345d7772b0674a318d5','2021-05-02 03:02:00','2021-05-02 03:02:00',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(225,'client.tm.defaultGlobalTransactionTimeout','SEATA_GROUP','60000','2b4226dd7ed6eb2d419b881f3ae9c97c','2021-05-02 03:02:00','2021-05-02 03:02:00',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(226,'client.tm.degradeCheck','SEATA_GROUP','false','68934a3e9455fa72420237eb05902327','2021-05-02 03:02:00','2021-05-02 03:02:00',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(227,'client.tm.degradeCheckAllowTimes','SEATA_GROUP','10','d3d9446802a44259755d38e6d163e820','2021-05-02 03:02:01','2021-05-02 03:02:01',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(228,'client.tm.degradeCheckPeriod','SEATA_GROUP','2000','08f90c1a417155361a5c4b8d297e0d78','2021-05-02 03:02:01','2021-05-02 03:02:01',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(229,'store.mode','SEATA_GROUP','db','d77d5e503ad1439f585ac494268b351b','2021-05-02 03:02:01','2021-05-02 03:02:01',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(230,'store.file.dir','SEATA_GROUP','file_store/data','6a8dec07c44c33a8a9247cba5710bbb2','2021-05-02 03:02:01','2021-05-02 03:02:01',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(231,'store.file.maxBranchSessionSize','SEATA_GROUP','16384','c76fe1d8e08462434d800487585be217','2021-05-02 03:02:02','2021-05-02 03:02:02',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(232,'store.file.maxGlobalSessionSize','SEATA_GROUP','512','10a7cdd970fe135cf4f7bb55c0e3b59f','2021-05-02 03:02:02','2021-05-02 03:02:02',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(233,'store.file.fileWriteBufferCacheSize','SEATA_GROUP','16384','c76fe1d8e08462434d800487585be217','2021-05-02 03:02:02','2021-05-02 03:02:02',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(234,'store.file.flushDiskMode','SEATA_GROUP','async','0df93e34273b367bb63bad28c94c78d5','2021-05-02 03:02:02','2021-05-02 03:02:02',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(235,'store.file.sessionReloadReadSize','SEATA_GROUP','100','f899139df5e1059396431415e770c6dd','2021-05-02 03:02:03','2021-05-02 03:02:03',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(236,'store.db.datasource','SEATA_GROUP','druid','3d650fb8a5df01600281d48c47c9fa60','2021-05-02 03:02:03','2021-05-02 03:02:03',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(237,'store.db.dbType','SEATA_GROUP','mysql','81c3b080dad537de7e10e0987a4bf52e','2021-05-02 03:02:03','2021-05-02 03:02:03',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(238,'store.db.driverClassName','SEATA_GROUP','com.mysql.cj.jdbc.Driver','33763409bb7f4838bde4fae9540433e4','2021-05-02 03:02:03','2021-05-02 03:02:03',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(239,'store.db.url','SEATA_GROUP','jdbc:mysql://127.0.0.1:3306/seata?serverTimezone=Japan','8ef892481a5137c3f4eb911fb41eb44e','2021-05-02 03:02:03','2021-05-02 03:02:03',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(240,'store.db.user','SEATA_GROUP','root','63a9f0ea7bb98050796b649e85481845','2021-05-02 03:02:03','2021-05-02 03:02:03',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(241,'store.db.password','SEATA_GROUP','root','63a9f0ea7bb98050796b649e85481845','2021-05-02 03:02:04','2021-05-02 03:02:04',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(242,'store.db.minConn','SEATA_GROUP','5','e4da3b7fbbce2345d7772b0674a318d5','2021-05-02 03:02:04','2021-05-02 03:02:04',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(243,'store.db.maxConn','SEATA_GROUP','30','34173cb38f07f89ddbebc2ac9128303f','2021-05-02 03:02:04','2021-05-02 03:02:04',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(244,'store.db.globalTable','SEATA_GROUP','global_table','8b28fb6bb4c4f984df2709381f8eba2b','2021-05-02 03:02:04','2021-05-02 03:02:04',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(245,'store.db.branchTable','SEATA_GROUP','branch_table','54bcdac38cf62e103fe115bcf46a660c','2021-05-02 03:02:05','2021-05-02 03:02:05',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(246,'store.db.queryLimit','SEATA_GROUP','100','f899139df5e1059396431415e770c6dd','2021-05-02 03:02:05','2021-05-02 03:02:05',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(247,'store.db.lockTable','SEATA_GROUP','lock_table','55e0cae3b6dc6696b768db90098b8f2f','2021-05-02 03:02:05','2021-05-02 03:02:05',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(248,'store.db.maxWait','SEATA_GROUP','5000','a35fe7f7fe8217b4369a0af4244d1fca','2021-05-02 03:02:05','2021-05-02 03:02:05',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(249,'store.redis.mode','SEATA_GROUP','single','dd5c07036f2975ff4bce568b6511d3bc','2021-05-02 03:02:05','2021-05-02 03:02:05',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(250,'store.redis.single.host','SEATA_GROUP','127.0.0.1','f528764d624db129b32c21fbca0cb8d6','2021-05-02 03:02:06','2021-05-02 03:02:06',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(251,'store.redis.single.port','SEATA_GROUP','6379','92c3b916311a5517d9290576e3ea37ad','2021-05-02 03:02:06','2021-05-02 03:02:06',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(252,'store.redis.maxConn','SEATA_GROUP','10','d3d9446802a44259755d38e6d163e820','2021-05-02 03:02:06','2021-05-02 03:02:06',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(253,'store.redis.minConn','SEATA_GROUP','1','c4ca4238a0b923820dcc509a6f75849b','2021-05-02 03:02:06','2021-05-02 03:02:06',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(254,'store.redis.maxTotal','SEATA_GROUP','100','f899139df5e1059396431415e770c6dd','2021-05-02 03:02:06','2021-05-02 03:02:06',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(255,'store.redis.database','SEATA_GROUP','0','cfcd208495d565ef66e7dff9f98764da','2021-05-02 03:02:07','2021-05-02 03:02:07',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(256,'store.redis.queryLimit','SEATA_GROUP','100','f899139df5e1059396431415e770c6dd','2021-05-02 03:02:07','2021-05-02 03:02:07',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(257,'server.recovery.committingRetryPeriod','SEATA_GROUP','1000','a9b7ba70783b617e9998dc4dd82eb3c5','2021-05-02 03:02:07','2021-05-02 03:02:07',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(258,'server.recovery.asynCommittingRetryPeriod','SEATA_GROUP','1000','a9b7ba70783b617e9998dc4dd82eb3c5','2021-05-02 03:02:07','2021-05-02 03:02:07',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(259,'server.recovery.rollbackingRetryPeriod','SEATA_GROUP','1000','a9b7ba70783b617e9998dc4dd82eb3c5','2021-05-02 03:02:08','2021-05-02 03:02:08',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(260,'server.recovery.timeoutRetryPeriod','SEATA_GROUP','1000','a9b7ba70783b617e9998dc4dd82eb3c5','2021-05-02 03:02:08','2021-05-02 03:02:08',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(261,'server.maxCommitRetryTimeout','SEATA_GROUP','-1','6bb61e3b7bce0931da574d19d1d82c88','2021-05-02 03:02:08','2021-05-02 03:02:08',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(262,'server.maxRollbackRetryTimeout','SEATA_GROUP','-1','6bb61e3b7bce0931da574d19d1d82c88','2021-05-02 03:02:08','2021-05-02 03:02:08',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(263,'server.rollbackRetryTimeoutUnlockEnable','SEATA_GROUP','false','68934a3e9455fa72420237eb05902327','2021-05-02 03:02:08','2021-05-02 03:02:08',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(264,'client.undo.dataValidation','SEATA_GROUP','true','b326b5062b2f0e69046810717534cb09','2021-05-02 03:02:09','2021-05-02 03:02:09',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(265,'client.undo.logSerialization','SEATA_GROUP','jackson','b41779690b83f182acc67d6388c7bac9','2021-05-02 03:02:09','2021-05-02 03:02:09',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(266,'client.undo.onlyCareUpdateColumns','SEATA_GROUP','true','b326b5062b2f0e69046810717534cb09','2021-05-02 03:02:09','2021-05-02 03:02:09',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(267,'server.undo.logSaveDays','SEATA_GROUP','7','8f14e45fceea167a5a36dedd4bea2543','2021-05-02 03:02:09','2021-05-02 03:02:09',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(268,'server.undo.logDeletePeriod','SEATA_GROUP','86400000','f4c122804fe9076cb2710f55c3c6e346','2021-05-02 03:02:09','2021-05-02 03:02:09',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(269,'client.undo.logTable','SEATA_GROUP','undo_log','2842d229c24afe9e61437135e8306614','2021-05-02 03:02:10','2021-05-02 03:02:10',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(270,'client.undo.compress.enable','SEATA_GROUP','true','b326b5062b2f0e69046810717534cb09','2021-05-02 03:02:10','2021-05-02 03:02:10',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(271,'client.undo.compress.type','SEATA_GROUP','zip','adcdbd79a8d84175c229b192aadc02f2','2021-05-02 03:02:10','2021-05-02 03:02:10',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(272,'client.undo.compress.threshold','SEATA_GROUP','64k','bd44a6458bdbff0b5cac721ba361f035','2021-05-02 03:02:10','2021-05-02 03:02:10',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(273,'log.exceptionRate','SEATA_GROUP','100','f899139df5e1059396431415e770c6dd','2021-05-02 03:02:11','2021-05-02 03:02:11',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(274,'transport.serialization','SEATA_GROUP','seata','b943081c423b9a5416a706524ee05d40','2021-05-02 03:02:11','2021-05-02 03:02:11',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(275,'transport.compressor','SEATA_GROUP','none','334c4a4c42fdb79d7ebc3e73b517e6f8','2021-05-02 03:02:11','2021-05-02 03:02:11',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(276,'metrics.enabled','SEATA_GROUP','false','68934a3e9455fa72420237eb05902327','2021-05-02 03:02:11','2021-05-02 03:02:11',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(277,'metrics.registryType','SEATA_GROUP','compact','7cf74ca49c304df8150205fc915cd465','2021-05-02 03:02:11','2021-05-02 03:02:11',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(278,'metrics.exporterList','SEATA_GROUP','prometheus','e4f00638b8a10e6994e67af2f832d51c','2021-05-02 03:02:12','2021-05-02 03:02:12',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(279,'metrics.exporterPrometheusPort','SEATA_GROUP','9898','7b9dc501afe4ee11c56a4831e20cee71','2021-05-02 03:02:12','2021-05-02 03:02:12',NULL,'127.0.0.1','','',NULL,NULL,NULL,'text',NULL),
(280,'seata.yml','DEFAULT_GROUP','seata:\n  enabled: true\n  application-id: ${spring.application.name}\n  tx-service-group: online_edu_tx # 事务群组，要和nacos上的配置保持一致\n  config:\n    type: nacos\n    nacos:\n      server-addr: nacos:8848\n      group: SEATA_GROUP\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: nacos:8848\n      group: SEATA_GROUP\n      namespace: b909e98d-d1c4-4319-879a-758981c15700','1e47e2655ab6e7639778db1e6d2726c5','2021-05-02 03:20:50','2021-07-18 04:24:49',NULL,'0:0:0:0:0:0:0:1','','b909e98d-d1c4-4319-879a-758981c15700','seata config','','','text','');

/*Table structure for table `config_info_aggr` */

DROP TABLE IF EXISTS `config_info_aggr`;

CREATE TABLE `config_info_aggr` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';

/*Data for the table `config_info_aggr` */

/*Table structure for table `config_info_beta` */

DROP TABLE IF EXISTS `config_info_beta`;

CREATE TABLE `config_info_beta` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

/*Data for the table `config_info_beta` */

/*Table structure for table `config_info_tag` */

DROP TABLE IF EXISTS `config_info_tag`;

CREATE TABLE `config_info_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

/*Data for the table `config_info_tag` */

/*Table structure for table `config_tags_relation` */

DROP TABLE IF EXISTS `config_tags_relation`;

CREATE TABLE `config_tags_relation` (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

/*Data for the table `config_tags_relation` */

/*Table structure for table `group_capacity` */

DROP TABLE IF EXISTS `group_capacity`;

CREATE TABLE `group_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

/*Data for the table `group_capacity` */

/*Table structure for table `his_config_info` */

DROP TABLE IF EXISTS `his_config_info`;

CREATE TABLE `his_config_info` (
  `id` bigint unsigned NOT NULL,
  `nid` bigint unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=467 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

/*Data for the table `his_config_info` */

insert  into `his_config_info`(`id`,`nid`,`data_id`,`group_id`,`app_name`,`content`,`md5`,`gmt_create`,`gmt_modified`,`src_user`,`src_ip`,`op_type`,`tenant_id`) values 
(180,461,'redis.yml','DEFAULT_GROUP','','spring:\n  redis:\n    host: localhost\n    port: 6379\n    # 连接超时时间（毫秒）\n    timeout: 150000\n    lettuce:\n      pool:\n        # 连接池最大连接数（使用负值表示没有限制）\n        max-active: 30\n        # 连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: 2\n        # 连接池中的最大空闲连接\n        max-idle: 10\n        # 连接池中的最小空闲连接\n        min-idle: 0','bcf220e8952609786e3c12a298722c60','2021-07-18 04:07:33','2021-07-18 04:07:32',NULL,'0:0:0:0:0:0:0:1','U','b909e98d-d1c4-4319-879a-758981c15700'),
(181,462,'mysql.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    username: root\n    password: root\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/online?serverTimezone=Japan&useUnicode=true&characterEncoding=UTF-8\n    druid:\n      filters: stat,wall # 底层开启功能，stat（sql监控），wall（防火墙）\n      max-active: 12\n      stat-view-servlet: # 配置监控页功能\n        enabled: true\n        login-username: root\n        login-password: root\n        url-pattern: /druid/*\n        reset-enable: false\n        allow:\n      web-stat-filter: # 监控web\n        exclusions: \'*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*\' # 可以不写，默认配置\n        enabled: true\n        url-pattern: /*\n      aop-patterns: com.github.* #监控SpringBean\n      filter: # 对上面filters里面的stat的详细配置\n        stat:\n          slow-sql-millis: 1000\n          log-slow-sql: true\n        wall:\n          enabled: true','d36cf61473001a1a3d75591eb887a9f0','2021-07-18 04:07:56','2021-07-18 04:07:56',NULL,'0:0:0:0:0:0:0:1','U','b909e98d-d1c4-4319-879a-758981c15700'),
(183,463,'nacos-sentinel.yml','DEFAULT_GROUP','','spring:\n  cloud:\n    nacos:\n      server-addr: localhost:8848\n      discovery:\n        namespace: b909e98d-d1c4-4319-879a-758981c15700\n    sentinel:\n      transport:\n        dashboard: 192.168.0.11:8080\n        port: 8719','acaa088cb29e2f9be3bb8129d5dae3d8','2021-07-18 04:08:59','2021-07-18 04:09:00',NULL,'0:0:0:0:0:0:0:1','U','b909e98d-d1c4-4319-879a-758981c15700'),
(192,464,'rabbitmq.yml','DEFAULT_GROUP','','spring:\n  cloud:\n    stream:\n      binders: # 配置要绑定的rabbitmq的服务信息\n        defaultRabbit: # 表示定义的名称，用于与Binding整合\n          type: rabbit # 消息组件类型\n          environment: # 设置rabbitmq的相关环境配置\n            spring:\n              rabbitmq:\n                host: localhost\n                port: 5672\n                username: andochiwa\n                password: 123789','e85fe6d4d933392afb9dc9d5f0b997ff','2021-07-18 04:22:18','2021-07-18 04:22:18',NULL,'0:0:0:0:0:0:0:1','U','b909e98d-d1c4-4319-879a-758981c15700'),
(280,465,'seata.yml','DEFAULT_GROUP','','seata:\r\n  enabled: true\r\n  application-id: ${spring.application.name}\r\n  tx-service-group: online_edu_tx # 事务群组，要和nacos上的配置保持一致\r\n  config:\r\n    type: nacos\r\n    nacos:\r\n      server-addr: localhost:8848\r\n      group: SEATA_GROUP\r\n  registry:\r\n    type: nacos\r\n    nacos:\r\n      application: seata-server\r\n      server-addr: localhost:8848\r\n      group: SEATA_GROUP\r\n      namespace: b909e98d-d1c4-4319-879a-758981c15700','6ddae93576f777df96a2da478d5b0168','2021-07-18 04:22:40','2021-07-18 04:22:41',NULL,'0:0:0:0:0:0:0:1','U','b909e98d-d1c4-4319-879a-758981c15700'),
(280,466,'seata.yml','DEFAULT_GROUP','','seata:\n  enabled: true\n  application-id: ${spring.application.name}\n  tx-service-group: online_edu_tx # 事务群组，要和nacos上的配置保持一致\n  config:\n    type: nacos\n    nacos:\n      server-addr: nacos:8848\n      group: SEATA_GROUP\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: nacos:8848\n      group: SEATA_GROUP\n      namespace: b909e98d-d1c4-4319-879a-758981c15700','1e47e2655ab6e7639778db1e6d2726c5','2021-07-18 04:24:49','2021-07-18 04:24:49',NULL,'0:0:0:0:0:0:0:1','U','b909e98d-d1c4-4319-879a-758981c15700');

/*Table structure for table `permissions` */

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `permissions` */

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `roles` */

insert  into `roles`(`username`,`role`) values 
('nacos','ROLE_ADMIN');

/*Table structure for table `tenant_capacity` */

DROP TABLE IF EXISTS `tenant_capacity`;

CREATE TABLE `tenant_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

/*Data for the table `tenant_capacity` */

/*Table structure for table `tenant_info` */

DROP TABLE IF EXISTS `tenant_info`;

CREATE TABLE `tenant_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

/*Data for the table `tenant_info` */

insert  into `tenant_info`(`id`,`kp`,`tenant_id`,`tenant_name`,`tenant_desc`,`create_source`,`gmt_create`,`gmt_modified`) values 
(3,'1','b909e98d-d1c4-4319-879a-758981c15700','online-education','在线教育项目','nacos',1619625958540,1619625958540);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`enabled`) values 
('nacos','$2a$10$qlmFztfUnuVd75eydH.1r.KT1O8o5MYnn/7QqVTBjddEs1.0FotlC',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
