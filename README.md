# Spring Cloud Finchley Oauth2 权限认证服务示例 
 <p align="center">
   <img src="https://img.shields.io/badge/Spring%20Cloud-Finchley.SR2-blue.svg" alt="Coverage Status">
   <img src="https://img.shields.io/badge/Spring%20Boot-2.0.8.RELEASE-blue.svg" alt="Downloads">
   <img src="https://img.shields.io/badge/Mybatis%20Plus-2.3-blue.svg" alt="Downloads">
 </p>  
 
 #### 依赖版本 
 
 依赖 | 版本
 ---|---
 Spring Boot |  2.0.8.RELEASE  
 Spring Cloud | Finchley.SR2   
 Spring Security OAuth2 | 2.0.1.RELEASE
 Spring Security | 5.0.11.RELEASE
 Mybatis Plus | 3.0.7.1
 
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
 
 #### 预览图 
  ![image](https://github.com/Air433/demo-oauth-Finchley-02/blob/master/image/readme/role-manager-page.jpg)  
 
 #### 通过密码授权
  ##### 客户端信息
 ![image](https://github.com/Air433/demo-oauth-Finchley-02/blob/master/image/readme/password-grand-client.png)   
  ##### 账户信息
 ![image](https://github.com/Air433/demo-oauth-Finchley-02/blob/master/image/readme/password-grand.png)  
 ##### 通过token访问资源
 ![image](https://github.com/Air433/demo-oauth-Finchley-02/blob/master/image/readme/menulist.png)  
 
 
 #### 通过授权码授权
 ##### 浏览器输入  
http://192.168.0.108:9032/oauth/authorize?response_type=code&client_id=air_client&redirect_uri=http://www.baidu.com&grant_type=code  
 192.168.0.108为本机ip  
 ##### 跳转到登陆页面  
  ![image](https://github.com/Air433/demo-oauth-Finchley-02/blob/master/image/readme/code-login.png)  
 ##### 登陆后重定向到前面URL指定的网址 后面code是授权码
   ![image](https://github.com/Air433/demo-oauth-Finchley-02/blob/master/image/readme/getcode.png)  
 ##### 通过授权码获取token  
   ![image](https://github.com/Air433/demo-oauth-Finchley-02/blob/master/image/readme/codeGetToken.png)  
 ##### 最后通过token访问资源
   ![image](https://github.com/Air433/demo-oauth-Finchley-02/blob/master/image/readme/codeAccessResource.png)  

 
 

 
  
