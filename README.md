# zuoben 

#### 项目介绍
SpringBoot + SpringCloud 构建分布式、负载均衡系统，前后分离企业级快速开发脚手架

这是我平时测试用的项目，整天想着开源一直忙没有时间整理，现在整理出来，有兴趣的朋友可以用于研究学习也可以在此基础上快速进行二次开发。

#### 技术栈
    1. Spring Boot
    2. Spring Cloud
    3. MySQL
    4. JWT
    5. RPC
    6. Email
    7. Bootstrap
    8. mybatis、mybatis-generator
    9. Alibaba Druid
    10，redis
    
#### 部署
导入项目后，通过以下步骤来启动项目：
1. 导入数据库
    在项目的sql文件夹下有数据库脚本，首先导入数据。
2. 启动redis服务
3. 修改相关配置
    1. 数据库相关配置
    2. redis相关配置
    3. 都在**.yml配置文件中
    
4. 启动项目

    每个工程启动的依次顺序是eureka、gete、auth、sys

#### 开发
项目一共包含三个工程，zuoben-eureka（注册中心），zuoben-gate（网关），zuoben-auth（鉴权），zuoben-sys（用户）

工程分为controller、service、mapper、model、dto、vo、client、config、interceptor、client、rpc层。 

controller主要负责转发、service主要负责业务逻辑、mapper主要是数据库的操作、model持久化对象、dto数据传输对象、vo表现对象。


#### 其他
联系我们  QQ群[![zuoben](https://pub.idqqimg.com/wpa/images/group.png)](https://jq.qq.com/?_wv=1027&k=5mwcHLH)   
![](https://gitee.com/zuoben/zuoben-web/raw/master/img/zuoben.png)
