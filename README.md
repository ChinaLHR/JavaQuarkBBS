# JavaQuark社区

 [![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

## 简介

    1、JavaQuarkBBS是一款使用Java编写的简易社区系统。
    2、采用前后端分离的机制实现。前台项目通过http访问RESTFulAPI获取信息渲染页面。
    3、前台页面来自FlyUI的开源社区模板
	4、服务层采用springboot+springmvc搭建RESTFul服务，WebSocket+stomp搭建推送服务
	5、后台管理采用springboot+shiro搭建的权限管理系统
	6、前台展示使用LayUI，jQuery渲染页面

## 系统结构图
	1、quark-common :基础服务抽象,DAO层，Entity以及DTO
	2、quark-admin：后台管理系统，使用shiro制作基于URL的权限控制，进行帖子管理，回复管理，用户管理等操作
	3、quark-rest：使用springMVC搭建RESTFul服务，使用WebSocket搭建推送服务，使用Redis进行缓存，面向各个客户端
	4、quark-portal：前台社区系统，使用springMVC进行也页面跳转与拦截，ajax获取数据，通过LayUI渲染页面。
 ![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/system1.png)  <br>
	

## 运行环境
- JDK 8
- Maven
- MySQL
- Nginx
- Redis

## 主要技术
- Springboot
- springMVC
- springData
- hibernate-jpa
- shiro
- thymeleaf
- Ehcache
- swagger2
- Bootstrap
- Datatables，zTree
- LayUI

## swagger2生成的RESTFul API文档
(默认再http://loclhost:8081下)
 ![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_rest_1.JPG)  <br>
 ![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_rest_2.JPG)  <br>

## 推送服务流程图：
  ![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/system2.jpg)  <br>

## 环境部署
	导入resource文件夹下的sql文件
	Redis服务器：默认端口
	Nginx部署图片服务器到目录：root   D:\home;
	后台管理员：账号：lhr 密码：root

## 效果图
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_portal_1.JPG)  <br>
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_portal_2.JPG)  <br>
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_portal_3.JPG)  <br>
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_portal_4.JPG)  <br>
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_portal_5.JPG)  <br>
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_admin_1.JPG)  <br>
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_admin_2.JPG)  <br>
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_admin_3.JPG)  <br>

## License

    Copyright 2016 Maat


    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.