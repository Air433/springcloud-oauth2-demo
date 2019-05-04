# Spring Cloud Finchley Oauth2 权限认证服务示例 
 <p align="center">
   <img src="https://img.shields.io/badge/Spring%20Cloud-Finchley.SR2-blue.svg" alt="Coverage Status">
   <img src="https://img.shields.io/badge/Spring%20Boot-2.0.8.RELEASE-blue.svg" alt="Downloads">
   <img src="https://img.shields.io/badge/Mybatis%20Plus-2.3-blue.svg" alt="Downloads">
 </p>  
 
 #### 模块说明
 ```
 demo-oauth-Finchley-02
 ├── demo-auth ── 授权服务器
 ├── demo-common ── 公共模块
 ├── demo-gateway -- Spring Cloud Gateway网关
 └── demo-user -- 通用用户权限管理模块
      └── demo-user-api -- 用户权限管理系统公共api模块
      └── demo-user-biz -- 用户权限管理系统业务处理模块
```
 #### 启动顺序
 ````
 DemoEurekaApplication
 DemoGatewayApplication
 DemoAuthApplication
 DemoUserBizApplication
 ````
 #### 通过密码授权
  ##### 客户端信息
 ![image](https://github.com/Air433/demo-oauth-Finchley-02/blob/master/image/readme/password-grand-client.png)   
  ##### 账户信息
 ![image](https://github.com/Air433/demo-oauth-Finchley-02/blob/master/image/readme/password-grand.png)  
 ##### 通过token访问资源
 ![image](https://github.com/Air433/demo-oauth-Finchley-02/blob/master/image/readme/menulist.png)  
 
 

 
  
