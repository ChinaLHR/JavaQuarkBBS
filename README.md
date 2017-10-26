# JavaQuark社区

 [![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

## 简介
	1、JavaQuarkBBS是一款使用Java编写的简易社区系统。
	2、采用前后端分离的机制实现。前台项目通过http访问RESTFulAPI获取信息渲染页面。
	3、项目技术分层明显,模块分离，采用springboot构建模块。
	4、前台页面来自FlyUI的开源社区模板

## 系统结构图
	1、quark-common :采用了Springdata+MySql实现基础服务抽象,DAO层，Entity以及DTO
	2、quark-admin：采用springboot+shiro搭建的细粒度的基于URL的权限管理系统，进行帖子管理，回复管理，用户管理等操作
	3、quark-rest：使用springMVC搭建RESTFul服务，采用WebSocket协议+stomp协议搭建推送服务，实现一对一推送与一对多推送，面向各个客户端
	4、quark-portal：前台社区系统，使用springMVC进行页面跳转与拦截，采用前后端分离的机制实现。前台展示模块通过http协议访问RESTFulAPI获取数据，
	使用LayUI，jQuery渲染页面渲染页面
	5、quark-chat:采用Netty+WebSocket协议搭建的聊天室服务，通过JSON传递数据，Ping-Pong心跳检测机制保证链路可用性。
	6、使用Redis进行了热点缓存，Ehcache进行数据库的二级缓存提高应用的效率
 ![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/systemv2.png)  <br>
	

## 运行环境
- JDK 8
- Maven
- MySQL
- Nginx
- Redis

## 主要技术
- Springboot
- springData
- Netty
- hibernate-jpa
- shiro
- thymeleaf
- Ehcache
- swagger2
- Bootstrap
- Datatables，zTree
- LayUI

## swagger2生成的RESTFul API文档
(默认在http://loclhost:8081下)
 ![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_rest_1.JPG)  <br>
 ![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_rest_2.JPG)  <br>

## WebSocket聊天室
### 应用层协议
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_chat_protocol.JPG)
 
		PING_CODE = 0x01;//Ping消息(client)
		PONG_CODE = 0x02;//Pong消息(server)
		AUTH_REQUEST_CODE = 0x03;//认证消息(client)
		AUTH_RESPONSE_CODE = 0x04;//认证消息(server)
		MESSAGE_REQUEST_CODE = 0x05;//消息(client)
		MESSAGE_RESPONSE_CODE = 0x06;//消息(server)
		SYS_USERSINFO_CODE = 0x07;//在线人数消息
		SYS_MESSAGE_CODE = 0x08;//系统消息
		SYS_ERRORMESSAGE_CODE = 0x09;//系统错误消息

### 通信模型
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_chat_message.png) 

## 环境部署
	导入resource文件夹下的sql文件
	Redis服务器：默认端口
	Nginx部署图片服务器到目录：root   D:\home;
	后台管理员：账号：lhr 密码：root

## 效果图
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_portal_1.JPG)  <br>
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_portal_4.JPG)  <br>
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_portal_5.JPG)  <br>
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_admin_1.JPG)  <br>
![image](https://raw.githubusercontent.com/ChinaLHR/JavaQuarkBBS/master/resource/images/quark_chat.JPG)  <br>

## 旧版本分支入口
[V1版本](https://github.com/ChinaLHR/JavaQuarkBBS/tree/v1)

## TODO
- 对项目进行服务化与细节优化，减少冗余代码
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