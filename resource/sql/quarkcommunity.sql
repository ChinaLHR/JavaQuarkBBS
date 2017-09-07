/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : quarkcommunity

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-07 14:34:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for quark_adminuser
-- ----------------------------
DROP TABLE IF EXISTS `quark_adminuser`;
CREATE TABLE `quark_adminuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enable` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4erqa44qkwwkl539xouso7v4c` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quark_adminuser
-- ----------------------------
INSERT INTO `quark_adminuser` VALUES ('3', '1', '1148f69de18a13200acb02f593cb77a3', 'lhr');
INSERT INTO `quark_adminuser` VALUES ('11', '0', '29255b3c67b58e0b8d82f74a3530eaf1', '测试7');
INSERT INTO `quark_adminuser` VALUES ('12', '0', '6e9b52d2e884a2b059abf2c234433c3e', '测试8');
INSERT INTO `quark_adminuser` VALUES ('13', '0', '14e9820a10160875aa7a88a32d1b372b', '测试9');
INSERT INTO `quark_adminuser` VALUES ('14', '0', '0f825d19af5d8cc49247b7e8461686e2', '测试10');
INSERT INTO `quark_adminuser` VALUES ('15', '0', '74f13fd29a1d4fa1ef4f46ce64fc06db', '测试11');
INSERT INTO `quark_adminuser` VALUES ('16', '0', '559a7719b93612db82bee78ae659c725', '测试12');
INSERT INTO `quark_adminuser` VALUES ('18', '1', 'e9ba95588bc2eed4f6471106c75f7f58', '测试14');
INSERT INTO `quark_adminuser` VALUES ('19', '1', 'ad81a18da5a9f39c7a96da250751ed8e', '测试15');
INSERT INTO `quark_adminuser` VALUES ('20', '1', 'b5d3b7651b984160d61f4c00de74221c', '测试16');
INSERT INTO `quark_adminuser` VALUES ('21', '1', 'e9d105ae53a113310fc15b33cf3f873e', '测试17');
INSERT INTO `quark_adminuser` VALUES ('22', '1', '070362d370810fdce3906dc535b0b45f', '测试18');
INSERT INTO `quark_adminuser` VALUES ('23', '1', '8617c14f6c451b30588c785d25235035', '测试19');
INSERT INTO `quark_adminuser` VALUES ('24', '1', 'b4902a61495abb416e0bbed43fffe1c6', '测试20');
INSERT INTO `quark_adminuser` VALUES ('25', '1', '6983e0407991f2455709c969d56ccf4c', '测试21');
INSERT INTO `quark_adminuser` VALUES ('26', '1', '6c22558f89c412406c23565dda0aa20e', '测试22');
INSERT INTO `quark_adminuser` VALUES ('27', '1', '08223f5b1081cd5d939983df98a0b81a', '测试23');
INSERT INTO `quark_adminuser` VALUES ('28', '1', 'e83e85bfced92b109f63792f64f67a97', '测试24');
INSERT INTO `quark_adminuser` VALUES ('29', '1', '235ec579349c0f66f992a82a8dc7d09e', '测试25');
INSERT INTO `quark_adminuser` VALUES ('30', '1', '50e2e801d3f09b4a5a0a346d51518adc', '测试26');
INSERT INTO `quark_adminuser` VALUES ('31', '1', '66602298d62af6208e20847741484a13', '测试27');
INSERT INTO `quark_adminuser` VALUES ('32', '1', '9b0ad7748175e8699c9d7225b7044d92', '测试28');
INSERT INTO `quark_adminuser` VALUES ('33', '1', '6266cccde19c119d8918b432e38273a9', '测试29');
INSERT INTO `quark_adminuser` VALUES ('34', '1', '17afda489eca5feb1e2495875d993bfa', '测试30');
INSERT INTO `quark_adminuser` VALUES ('35', '1', '1f6309ad95a1bbfecbfe763c895eb289', '测试31');
INSERT INTO `quark_adminuser` VALUES ('36', '1', '1438138762a9afc0c3b094ef5655654f', '测试32');
INSERT INTO `quark_adminuser` VALUES ('37', '1', '412953cb04c90341e8435cb31c3df327', '测试33');
INSERT INTO `quark_adminuser` VALUES ('38', '1', 'f9ae90b92ffe385d1af146cc264e4bdb', '测试34');
INSERT INTO `quark_adminuser` VALUES ('39', '1', '265df116fa955d7c866a77632f4c8ffe', '测试35');
INSERT INTO `quark_adminuser` VALUES ('40', '1', '371946741b0c22d6c3b871c2f66d9a63', '测试36');
INSERT INTO `quark_adminuser` VALUES ('41', '1', 'd9fdbcc12bdfccfd8bf55c51c0fcd8c1', '测试37');
INSERT INTO `quark_adminuser` VALUES ('50', '1', '0db82a65c8f98f62e9670ba35ab7a128', 'lcc');

-- ----------------------------
-- Table structure for quark_adminuser_role
-- ----------------------------
DROP TABLE IF EXISTS `quark_adminuser_role`;
CREATE TABLE `quark_adminuser_role` (
  `adminuser_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`adminuser_id`,`role_id`),
  KEY `FK26lllx1jn0c1k8f8oj2qt4io1` (`role_id`),
  CONSTRAINT `FK26lllx1jn0c1k8f8oj2qt4io1` FOREIGN KEY (`role_id`) REFERENCES `quark_role` (`id`),
  CONSTRAINT `FKatv5o94k3fooskwr53rvqfven` FOREIGN KEY (`adminuser_id`) REFERENCES `quark_adminuser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quark_adminuser_role
-- ----------------------------
INSERT INTO `quark_adminuser_role` VALUES ('3', '1');

-- ----------------------------
-- Table structure for quark_collect
-- ----------------------------
DROP TABLE IF EXISTS `quark_collect`;
CREATE TABLE `quark_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `init_time` datetime DEFAULT NULL,
  `posts_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5a8rh83fve6ug3utggpy8wdvi` (`posts_id`),
  KEY `FKg4gtyqabrsuwr5y35lxvjy515` (`user_id`),
  CONSTRAINT `FK5a8rh83fve6ug3utggpy8wdvi` FOREIGN KEY (`posts_id`) REFERENCES `quark_posts` (`id`),
  CONSTRAINT `FKg4gtyqabrsuwr5y35lxvjy515` FOREIGN KEY (`user_id`) REFERENCES `quark_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quark_collect
-- ----------------------------

-- ----------------------------
-- Table structure for quark_label
-- ----------------------------
DROP TABLE IF EXISTS `quark_label`;
CREATE TABLE `quark_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `details` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `posts_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6vml4ba2itmaor84892v92b1f` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quark_label
-- ----------------------------
INSERT INTO `quark_label` VALUES ('1', '关于JavaSe的话题', 'JavaSe', '203');
INSERT INTO `quark_label` VALUES ('2', '关于Java企业级开发的话题', 'JavaEE', '1');
INSERT INTO `quark_label` VALUES ('3', '关于Java虚拟机的话题', 'JVM', '3');
INSERT INTO `quark_label` VALUES ('4', '关于Spring框架的话题', 'Spring', '2');
INSERT INTO `quark_label` VALUES ('5', '关于Hibernate框架的话题', 'Hibernate', '1');
INSERT INTO `quark_label` VALUES ('6', '关于Scala语言的话题', 'Scala', '0');
INSERT INTO `quark_label` VALUES ('8', '关于Java并发编程的话题', 'Java并发编程', '1');
INSERT INTO `quark_label` VALUES ('9', '关于Android的话题', 'Android', '2');

-- ----------------------------
-- Table structure for quark_notification
-- ----------------------------
DROP TABLE IF EXISTS `quark_notification`;
CREATE TABLE `quark_notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_read` bit(1) DEFAULT NULL,
  `fromuser_id` int(11) NOT NULL,
  `posts_id` int(11) NOT NULL,
  `touser_id` int(11) NOT NULL,
  `init_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKac4wjs0b3992ohkf61jy4ilmj` (`fromuser_id`),
  KEY `FKilg05a7ki3vkv7lfjnn57pdw2` (`posts_id`),
  KEY `FKquscxmtiqggsch7w833ywubux` (`touser_id`),
  CONSTRAINT `FKac4wjs0b3992ohkf61jy4ilmj` FOREIGN KEY (`fromuser_id`) REFERENCES `quark_user` (`id`),
  CONSTRAINT `FKilg05a7ki3vkv7lfjnn57pdw2` FOREIGN KEY (`posts_id`) REFERENCES `quark_posts` (`id`),
  CONSTRAINT `FKquscxmtiqggsch7w833ywubux` FOREIGN KEY (`touser_id`) REFERENCES `quark_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quark_notification
-- ----------------------------
INSERT INTO `quark_notification` VALUES ('16', '', '2', '209', '71', '2017-09-07 10:39:33');
INSERT INTO `quark_notification` VALUES ('22', '', '71', '4', '2', '2017-09-07 11:22:23');

-- ----------------------------
-- Table structure for quark_permission
-- ----------------------------
DROP TABLE IF EXISTS `quark_permission`;
CREATE TABLE `quark_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `perurl` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `parentid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quark_permission
-- ----------------------------
INSERT INTO `quark_permission` VALUES ('1', '系统设置', '/system', '1', '0', '0');
INSERT INTO `quark_permission` VALUES ('2', '管理员管理', '/adminsPage', '2', '1', '1');
INSERT INTO `quark_permission` VALUES ('3', '角色管理', '/rolesPage', '3', '1', '1');
INSERT INTO `quark_permission` VALUES ('4', '权限管理', '/permissionsPage', '4', '1', '1');
INSERT INTO `quark_permission` VALUES ('5', '添加管理员', '/admins/add', '21', '2', '2');
INSERT INTO `quark_permission` VALUES ('6', '删除管理员', '/admins/delete', '22', '2', '2');
INSERT INTO `quark_permission` VALUES ('7', '添加角色', '/roles/add', '23', '2', '3');
INSERT INTO `quark_permission` VALUES ('8', '删除角色', '/roles/delete', '24', '2', '3');
INSERT INTO `quark_permission` VALUES ('9', '添加权限', '/permissions/add', '25', '2', '4');
INSERT INTO `quark_permission` VALUES ('10', '删除权限', '/permissions/delete', '26', '2', '4');
INSERT INTO `quark_permission` VALUES ('11', '分配角色', '/admins/saveAdminRole', '27', '2', '2');
INSERT INTO `quark_permission` VALUES ('12', '分配权限', '/roles/saveRolePermission', '28', '2', '3');
INSERT INTO `quark_permission` VALUES ('13', '启用/禁用管理员', '/admins/saveAdminEnable', '29', '2', '2');
INSERT INTO `quark_permission` VALUES ('16', '用户管理', '/usersPage', '5', '1', '1');
INSERT INTO `quark_permission` VALUES ('17', '启用/禁用用户', '/users/saveUserEnable', '31', '2', '16');
INSERT INTO `quark_permission` VALUES ('18', '帖子管理', '/postsPage', '6', '1', '1');
INSERT INTO `quark_permission` VALUES ('19', '帖子置顶', '/posts/saveTop', '32', '2', '18');
INSERT INTO `quark_permission` VALUES ('20', '帖子加精', '/posts/saveGood', '33', '2', '18');
INSERT INTO `quark_permission` VALUES ('21', '帖子删除', '/posts/delete', '34', '2', '18');
INSERT INTO `quark_permission` VALUES ('22', '回复管理', '/replysPage', '7', '1', '1');
INSERT INTO `quark_permission` VALUES ('24', '删除回复', '/replys/delete', '35', '2', '22');
INSERT INTO `quark_permission` VALUES ('25', '标签管理', '/labelsPage', '8', '1', '1');
INSERT INTO `quark_permission` VALUES ('26', '添加标签', '/labels/add', '36', '2', '25');
INSERT INTO `quark_permission` VALUES ('27', '删除标签', '/labels/delete', '37', '2', '25');
INSERT INTO `quark_permission` VALUES ('28', '修改标签', '/labels/update', '37', '2', '25');

-- ----------------------------
-- Table structure for quark_posts
-- ----------------------------
DROP TABLE IF EXISTS `quark_posts`;
CREATE TABLE `quark_posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `good` bit(1) NOT NULL,
  `init_time` datetime NOT NULL,
  `label_id` int(11) DEFAULT NULL,
  `reply_count` int(11) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `top` bit(1) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_46eh0lt2x9ftqcv9tjs8meqj8` (`title`),
  KEY `FK41ebsa5jpn18egdtjo6uiaugn` (`user_id`),
  KEY `FKnxd3qh1m3c0o6tc3ojfa8fm6o` (`label_id`),
  CONSTRAINT `FK41ebsa5jpn18egdtjo6uiaugn` FOREIGN KEY (`user_id`) REFERENCES `quark_user` (`id`),
  CONSTRAINT `FKnxd3qh1m3c0o6tc3ojfa8fm6o` FOREIGN KEY (`label_id`) REFERENCES `quark_label` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quark_posts
-- ----------------------------
INSERT INTO `quark_posts` VALUES ('1', 'hahaha hello world Java', '\0', '2017-08-26 15:33:39', '1', '32', 'Hello World', '\0', '2');
INSERT INTO `quark_posts` VALUES ('2', 'hahaha hello world Java', '\0', '2017-08-26 15:46:53', '1', '0', 'Hello World Scala', '\0', '2');
INSERT INTO `quark_posts` VALUES ('3', '<p>测试发布内容<img src=\"http://localhost:8082/layui/images/face/39.gif\" alt=\"[鼓掌]\"><img src=\"http://localhost:8082/layui/images/face/39.gif\" alt=\"[鼓掌]\"><img src=\"http://localhost:8082/layui/images/face/39.gif\" alt=\"[鼓掌]\"><img src=\"http://localhost:8082/layui/images/face/39.gif\" alt=\"[鼓掌]\"><img src=\"http://localhost:8082/layui/images/face/39.gif\" alt=\"[鼓掌]\"></p>', '\0', '2017-08-27 15:13:44', '1', '0', '测试发布', '\0', '2');
INSERT INTO `quark_posts` VALUES ('4', '<img src=\"http://127.0.0.1/images/upload/2017-08-27/59111ed9-e43b-4391-9444-e5a2c2e6b85d.jpeg\" alt=\"null\">', '', '2017-08-27 15:49:42', '3', '22', '推荐连续剧《请回答1988》', '', '2');
INSERT INTO `quark_posts` VALUES ('105', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁1', '\0', '2017-08-28 16:03:00', '1', '1', '测试帖子1', '\0', '2');
INSERT INTO `quark_posts` VALUES ('106', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁2', '\0', '2017-08-28 16:03:00', '1', '0', '测试帖子2', '\0', '2');
INSERT INTO `quark_posts` VALUES ('107', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁3', '\0', '2017-08-28 16:03:00', '1', '0', '测试帖子3', '\0', '2');
INSERT INTO `quark_posts` VALUES ('108', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁4', '\0', '2017-08-28 16:03:00', '1', '0', '测试帖子4', '\0', '2');
INSERT INTO `quark_posts` VALUES ('109', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁5', '\0', '2017-08-28 16:03:00', '1', '0', '测试帖子5', '\0', '2');
INSERT INTO `quark_posts` VALUES ('110', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁6', '\0', '2017-08-28 16:03:00', '1', '0', '测试帖子6', '\0', '2');
INSERT INTO `quark_posts` VALUES ('111', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁7', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子7', '\0', '2');
INSERT INTO `quark_posts` VALUES ('112', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁8', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子8', '\0', '2');
INSERT INTO `quark_posts` VALUES ('113', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁9', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子9', '\0', '2');
INSERT INTO `quark_posts` VALUES ('114', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁10', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子10', '\0', '2');
INSERT INTO `quark_posts` VALUES ('115', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁11', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子11', '\0', '2');
INSERT INTO `quark_posts` VALUES ('116', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁12', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子12', '\0', '2');
INSERT INTO `quark_posts` VALUES ('117', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁13', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子13', '\0', '2');
INSERT INTO `quark_posts` VALUES ('118', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁14', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子14', '\0', '2');
INSERT INTO `quark_posts` VALUES ('119', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁15', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子15', '\0', '2');
INSERT INTO `quark_posts` VALUES ('120', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁16', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子16', '\0', '2');
INSERT INTO `quark_posts` VALUES ('121', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁17', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子17', '\0', '2');
INSERT INTO `quark_posts` VALUES ('122', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁18', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子18', '\0', '2');
INSERT INTO `quark_posts` VALUES ('123', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁19', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子19', '\0', '2');
INSERT INTO `quark_posts` VALUES ('124', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁20', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子20', '\0', '2');
INSERT INTO `quark_posts` VALUES ('125', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁21', '\0', '2017-08-28 16:03:01', '1', '0', '测试帖子21', '\0', '2');
INSERT INTO `quark_posts` VALUES ('126', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁22', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子22', '\0', '2');
INSERT INTO `quark_posts` VALUES ('127', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁23', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子23', '\0', '2');
INSERT INTO `quark_posts` VALUES ('128', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁24', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子24', '\0', '2');
INSERT INTO `quark_posts` VALUES ('129', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁25', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子25', '\0', '2');
INSERT INTO `quark_posts` VALUES ('130', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁26', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子26', '\0', '2');
INSERT INTO `quark_posts` VALUES ('131', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁27', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子27', '\0', '2');
INSERT INTO `quark_posts` VALUES ('132', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁28', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子28', '\0', '2');
INSERT INTO `quark_posts` VALUES ('133', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁29', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子29', '\0', '2');
INSERT INTO `quark_posts` VALUES ('134', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁30', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子30', '\0', '2');
INSERT INTO `quark_posts` VALUES ('135', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁31', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子31', '\0', '2');
INSERT INTO `quark_posts` VALUES ('136', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁32', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子32', '\0', '2');
INSERT INTO `quark_posts` VALUES ('137', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁33', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子33', '\0', '2');
INSERT INTO `quark_posts` VALUES ('138', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁34', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子34', '\0', '2');
INSERT INTO `quark_posts` VALUES ('139', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁35', '\0', '2017-08-28 16:03:02', '1', '0', '测试帖子35', '\0', '2');
INSERT INTO `quark_posts` VALUES ('140', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁36', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子36', '\0', '2');
INSERT INTO `quark_posts` VALUES ('141', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁37', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子37', '\0', '2');
INSERT INTO `quark_posts` VALUES ('142', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁38', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子38', '\0', '2');
INSERT INTO `quark_posts` VALUES ('143', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁39', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子39', '\0', '2');
INSERT INTO `quark_posts` VALUES ('144', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁40', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子40', '\0', '2');
INSERT INTO `quark_posts` VALUES ('145', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁41', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子41', '\0', '2');
INSERT INTO `quark_posts` VALUES ('146', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁42', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子42', '\0', '2');
INSERT INTO `quark_posts` VALUES ('147', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁43', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子43', '\0', '2');
INSERT INTO `quark_posts` VALUES ('148', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁44', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子44', '\0', '2');
INSERT INTO `quark_posts` VALUES ('149', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁45', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子45', '\0', '2');
INSERT INTO `quark_posts` VALUES ('150', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁46', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子46', '\0', '2');
INSERT INTO `quark_posts` VALUES ('151', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁47', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子47', '\0', '2');
INSERT INTO `quark_posts` VALUES ('152', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁48', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子48', '\0', '2');
INSERT INTO `quark_posts` VALUES ('153', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁49', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子49', '\0', '2');
INSERT INTO `quark_posts` VALUES ('154', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁50', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子50', '\0', '2');
INSERT INTO `quark_posts` VALUES ('155', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁51', '\0', '2017-08-28 16:03:03', '1', '0', '测试帖子51', '\0', '2');
INSERT INTO `quark_posts` VALUES ('156', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁52', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子52', '\0', '2');
INSERT INTO `quark_posts` VALUES ('157', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁53', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子53', '\0', '2');
INSERT INTO `quark_posts` VALUES ('158', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁54', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子54', '\0', '2');
INSERT INTO `quark_posts` VALUES ('159', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁55', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子55', '\0', '2');
INSERT INTO `quark_posts` VALUES ('160', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁56', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子56', '\0', '2');
INSERT INTO `quark_posts` VALUES ('161', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁57', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子57', '\0', '2');
INSERT INTO `quark_posts` VALUES ('162', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁58', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子58', '\0', '2');
INSERT INTO `quark_posts` VALUES ('163', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁59', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子59', '\0', '2');
INSERT INTO `quark_posts` VALUES ('164', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁60', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子60', '\0', '2');
INSERT INTO `quark_posts` VALUES ('165', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁61', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子61', '\0', '2');
INSERT INTO `quark_posts` VALUES ('166', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁62', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子62', '\0', '2');
INSERT INTO `quark_posts` VALUES ('167', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁63', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子63', '\0', '2');
INSERT INTO `quark_posts` VALUES ('168', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁64', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子64', '\0', '2');
INSERT INTO `quark_posts` VALUES ('169', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁65', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子65', '\0', '2');
INSERT INTO `quark_posts` VALUES ('170', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁66', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子66', '\0', '2');
INSERT INTO `quark_posts` VALUES ('171', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁67', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子67', '\0', '2');
INSERT INTO `quark_posts` VALUES ('172', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁68', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子68', '\0', '2');
INSERT INTO `quark_posts` VALUES ('173', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁69', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子69', '\0', '2');
INSERT INTO `quark_posts` VALUES ('174', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁70', '\0', '2017-08-28 16:03:04', '1', '0', '测试帖子70', '\0', '2');
INSERT INTO `quark_posts` VALUES ('175', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁71', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子71', '\0', '2');
INSERT INTO `quark_posts` VALUES ('176', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁72', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子72', '\0', '2');
INSERT INTO `quark_posts` VALUES ('177', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁73', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子73', '\0', '2');
INSERT INTO `quark_posts` VALUES ('178', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁74', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子74', '\0', '2');
INSERT INTO `quark_posts` VALUES ('179', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁75', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子75', '\0', '2');
INSERT INTO `quark_posts` VALUES ('180', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁76', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子76', '\0', '2');
INSERT INTO `quark_posts` VALUES ('181', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁77', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子77', '\0', '2');
INSERT INTO `quark_posts` VALUES ('182', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁78', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子78', '\0', '2');
INSERT INTO `quark_posts` VALUES ('183', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁79', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子79', '\0', '2');
INSERT INTO `quark_posts` VALUES ('184', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁80', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子80', '\0', '2');
INSERT INTO `quark_posts` VALUES ('185', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁81', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子81', '\0', '2');
INSERT INTO `quark_posts` VALUES ('186', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁82', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子82', '\0', '2');
INSERT INTO `quark_posts` VALUES ('187', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁83', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子83', '\0', '2');
INSERT INTO `quark_posts` VALUES ('188', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁84', '\0', '2017-08-28 16:03:05', '1', '0', '测试帖子84', '\0', '2');
INSERT INTO `quark_posts` VALUES ('189', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁85', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子85', '\0', '2');
INSERT INTO `quark_posts` VALUES ('190', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁86', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子86', '\0', '2');
INSERT INTO `quark_posts` VALUES ('191', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁87', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子87', '\0', '2');
INSERT INTO `quark_posts` VALUES ('192', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁88', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子88', '\0', '2');
INSERT INTO `quark_posts` VALUES ('193', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁89', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子89', '\0', '2');
INSERT INTO `quark_posts` VALUES ('194', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁90', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子90', '\0', '2');
INSERT INTO `quark_posts` VALUES ('195', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁91', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子91', '\0', '2');
INSERT INTO `quark_posts` VALUES ('196', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁92', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子92', '\0', '2');
INSERT INTO `quark_posts` VALUES ('197', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁93', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子93', '\0', '2');
INSERT INTO `quark_posts` VALUES ('198', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁94', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子94', '\0', '2');
INSERT INTO `quark_posts` VALUES ('199', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁95', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子95', '\0', '2');
INSERT INTO `quark_posts` VALUES ('200', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁96', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子96', '\0', '2');
INSERT INTO `quark_posts` VALUES ('201', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁97', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子97', '\0', '2');
INSERT INTO `quark_posts` VALUES ('202', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁98', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子98', '\0', '2');
INSERT INTO `quark_posts` VALUES ('203', '希鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁鲁99', '\0', '2017-08-28 16:03:06', '1', '0', '测试帖子99', '\0', '2');
INSERT INTO `quark_posts` VALUES ('205', '<p>&lt;dependency&gt;</p><p><span>			</span>&lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;</p><p><span>			</span>&lt;artifactId&gt;spring-boot-starter-thymeleaf&lt;/artifactId&gt;</p><p>&lt;/dependency&gt;</p>', '', '2017-08-28 21:52:55', '4', '1', '在Spring boot中配置Thymeleaf', '\0', '2');
INSERT INTO `quark_posts` VALUES ('206', '<p><img src=\"http://127.0.0.1/images/upload/2017-08-28/b8d8e982-0f66-4f19-871b-c7d01af37a9f.png\" alt=\"null\"><br></p>', '', '2017-08-28 22:46:59', '3', '1', 'Class文件装载流程', '\0', '2');
INSERT INTO `quark_posts` VALUES ('208', '<p><img src=\"http://127.0.0.1/images/upload/2017-09-01/23c98bba-5959-4540-ab1d-9d3e7a2ecdec.png\" alt=\"null\"><br></p><p><br></p>', '', '2017-09-01 22:01:27', '3', '2', 'JVM体系', '', '2');
INSERT INTO `quark_posts` VALUES ('209', '<img src=\"http://localhost:8082/layui/images/face/11.gif\" alt=\"[爱你]\">', '\0', '2017-09-03 11:05:25', '5', '2', '探索R闪背后的奥秘', '\0', '71');
INSERT INTO `quark_posts` VALUES ('210', '<p><span lang=\"EN-US\">filterChainDefinitionMap.put(</span><span lang=\"EN-US\">\"/favicon.ico\"</span><span lang=\"EN-US\">,</span><span lang=\"EN-US\">\"anon\"</span><span lang=\"EN-US\">)</span><span lang=\"EN-US\">;</span><span lang=\"EN-US\">//</span><span>解决弹出</span><span lang=\"EN-US\">favicon.ico</span><span>下载</span></p><p><span><img src=\"http://127.0.0.1/images/upload/2017-09-03/ec32b602-f818-4258-86bc-ac247e0c48ec.jpeg\" alt=\"null\"><br></span></p>', '\0', '2017-09-03 11:07:21', '2', '4', 'shiro在登录的时候下载favicon.ico问题', '\0', '71');
INSERT INTO `quark_posts` VALUES ('211', 'view结构<img src=\"http://127.0.0.1/images/upload/2017-09-05/798c1656-3d6d-4f72-8080-a6b0cd75486b.png\" alt=\"null\">', '\0', '2017-09-05 10:38:27', '9', '1', 'Android View机制', '\0', '72');
INSERT INTO `quark_posts` VALUES ('212', '<img src=\"http://localhost:8082/layui/images/face/51.gif\" alt=\"[兔子]\">', '\0', '2017-09-05 10:38:59', '9', '4', '新人报到', '\0', '72');
INSERT INTO `quark_posts` VALUES ('213', '<p><span lang=\"EN-US\">1 </span><span>先来先服务<span lang=\"EN-US\"> - </span>时间片轮转调度<span lang=\"EN-US\"><br>\n</span>这个很简单，就是谁先来，就给谁分配时间片运行，缺点是有些紧急的任务要很久才能得到运行。</span><span lang=\"EN-US\"><o:p></o:p></span></p><p><span lang=\"EN-US\">2.&nbsp;</span><span>优先级调度<span lang=\"EN-US\"><br>\n</span>每个线程有一个优先级，<span lang=\"EN-US\">CPU</span>每次去拿优先级高的运行，优先级低的等等，为了避免优先级低的等太久，每等一定时间，就给优先级低的线程提高一个级别</span><span lang=\"EN-US\"><o:p></o:p></span></p><p><span lang=\"EN-US\">3.</span><span>最短作业优先<span lang=\"EN-US\"><br>\n</span>把线程任务量排序，每次拿处理时间短的线程运行，就像我去银行办业务一样，我的事情很快就处理完了，所以让我插队先办完，后面时间长的人先等等，时间长的人就很难得到响应了。</span><span lang=\"EN-US\"><o:p></o:p></span></p><p><span lang=\"EN-US\">4.&nbsp;</span><span>最高响应比优先<span lang=\"EN-US\"><br>\n</span>用线程的等待时间除以服务时间，得到响应比，响应比小的优先运行。这样不会造成某些任务一直得不到响应。</span><span lang=\"EN-US\"><o:p></o:p></span></p><p>\n\n\n\n\n\n\n\n</p><p><span lang=\"EN-US\">5.</span><span>多级反馈队列调度<span lang=\"EN-US\"><br>\n</span>有多个优先级不同的队列，每个队列里面有多个等待线程。<span lang=\"EN-US\"><br>\nCPU</span>每次从优先级高的遍历到低的，取队首的线程运行，运行完了放回队尾，优先级越高，时间片越短，即响应越快，时间片大小就不是固定的了。<span lang=\"EN-US\"><br>\n</span>每个队列的内部还是用先来先服务的策略。</span><span lang=\"EN-US\"><o:p></o:p></span></p>', '\0', '2017-09-07 10:21:06', '8', '6', '并发的CPU调度策略', '\0', '2');

-- ----------------------------
-- Table structure for quark_reply
-- ----------------------------
DROP TABLE IF EXISTS `quark_reply`;
CREATE TABLE `quark_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `init_time` datetime DEFAULT NULL,
  `up` int(11) NOT NULL,
  `posts_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnr6a7xtk9rm31ptn6gchi9113` (`posts_id`),
  KEY `FKnt12hb9oqfm5eamjpg27bkpvv` (`user_id`),
  CONSTRAINT `FKnr6a7xtk9rm31ptn6gchi9113` FOREIGN KEY (`posts_id`) REFERENCES `quark_posts` (`id`),
  CONSTRAINT `FKnt12hb9oqfm5eamjpg27bkpvv` FOREIGN KEY (`user_id`) REFERENCES `quark_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quark_reply
-- ----------------------------
INSERT INTO `quark_reply` VALUES ('1', '回复', '2017-08-29 10:17:39', '12', '1', '2');
INSERT INTO `quark_reply` VALUES ('2', '回复', '2017-08-29 10:35:46', '0', '1', '5');
INSERT INTO `quark_reply` VALUES ('3', '测试回复1', '2017-08-29 11:36:08', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('4', '测试回复2', '2017-08-29 11:36:08', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('5', '测试回复3', '2017-08-29 11:36:08', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('8', '测试回复6', '2017-08-29 11:36:08', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('9', '测试回复7', '2017-08-29 11:36:08', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('10', '测试回复8', '2017-08-29 11:36:08', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('11', '测试回复9', '2017-08-29 11:36:08', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('12', '测试回复10', '2017-08-29 11:36:08', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('13', '测试回复11', '2017-08-29 11:36:08', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('14', '测试回复12', '2017-08-29 11:36:08', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('15', '测试回复13', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('17', '测试回复15', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('18', '测试回复16', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('19', '测试回复17', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('20', '测试回复18', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('21', '测试回复19', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('22', '测试回复20', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('23', '测试回复21', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('24', '测试回复22', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('25', '测试回复23', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('26', '测试回复24', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('27', '测试回复25', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('28', '测试回复26', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('29', '测试回复27', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('30', '测试回复28', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('31', '测试回复29', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('32', '测试回复30', '2017-08-29 11:36:09', '0', '1', '2');
INSERT INTO `quark_reply` VALUES ('33', '我就试试', '2017-08-30 22:39:07', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('34', '我就试试', '2017-08-30 22:42:07', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('35', '希鲁鲁', '2017-08-30 22:42:38', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('36', '测试', '2017-08-30 22:44:50', '0', '105', '2');
INSERT INTO `quark_reply` VALUES ('37', '不错啊不错哇', '2017-08-30 22:45:31', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('38', '不错啊不错哇', '2017-08-30 22:47:36', '0', '206', '2');
INSERT INTO `quark_reply` VALUES ('39', '好剧，谢谢推荐<img src=\"http://localhost:8082/layui/images/face/47.gif\" alt=\"[心]\">', '2017-08-30 23:20:37', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('40', '<p>垃圾哥乱入<img src=\"http://localhost:8082/layui/images/face/13.gif\" alt=\"[偷笑]\"></p><p><img src=\"http://127.0.0.1/images/upload/2017-08-30/a8570c79-a779-4a11-b162-c38f03f86b3b.jpeg\" alt=\"null\"></p>', '2017-08-30 23:25:33', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('41', '不错', '2017-08-30 23:26:34', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('42', '哈哈', '2017-08-30 23:27:55', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('43', '铁打的父母流水的孩子~<img src=\"http://localhost:8082/layui/images/face/8.gif\" alt=\"[挤眼]\">', '2017-08-30 23:29:51', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('44', '<p><img src=\"http://localhost:8082/layui/images/face/16.gif\" alt=\"[太开心]\"><img src=\"http://localhost:8082/layui/images/face/16.gif\" alt=\"[太开心]\"><img src=\"http://localhost:8082/layui/images/face/16.gif\" alt=\"[太开心]\"><img src=\"http://localhost:8082/layui/images/face/16.gif\" alt=\"[太开心]\">好看</p>', '2017-08-31 14:57:03', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('45', '好好好', '2017-08-31 15:05:10', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('46', '牛逼<img src=\"http://localhost:8082/layui/images/face/0.gif\" alt=\"[微笑]\">', '2017-08-31 15:35:15', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('47', '不错，很清晰<img src=\"http://localhost:8082/layui/images/face/49.gif\" alt=\"[猪头]\">', '2017-09-01 22:01:43', '0', '208', '2');
INSERT INTO `quark_reply` VALUES ('48', '厉害啊<img src=\"http://localhost:8082/layui/images/face/36.gif\" alt=\"[酷]\">', '2017-09-03 11:32:23', '0', '210', '2');
INSERT INTO `quark_reply` VALUES ('49', '<img src=\"http://localhost:8082/layui/images/face/26.gif\" alt=\"[怒]\">', '2017-09-05 10:26:14', '0', '209', '2');
INSERT INTO `quark_reply` VALUES ('50', '有意思<img src=\"http://127.0.0.1/images/upload/2017-09-05/6dbe0e05-22b2-402c-b6be-bf9b4d7ca0db.jpeg\" alt=\"null\">', '2017-09-05 10:32:42', '0', '210', '72');
INSERT INTO `quark_reply` VALUES ('51', '很清晰<img src=\"http://localhost:8082/layui/images/face/11.gif\" alt=\"[爱你]\">', '2017-09-05 10:39:18', '0', '211', '2');
INSERT INTO `quark_reply` VALUES ('52', '?', '2017-09-06 15:43:18', '0', '212', '2');
INSERT INTO `quark_reply` VALUES ('53', '不错嘛<img src=\"http://127.0.0.1/images/upload/2017-09-06/0b41e3fe-1023-4030-a166-832497589086.jpeg\" alt=\"null\">', '2017-09-06 15:45:58', '0', '205', '2');
INSERT INTO `quark_reply` VALUES ('54', '新人爆照<img src=\"http://localhost:8082/layui/images/face/30.gif\" alt=\"[思考]\">', '2017-09-06 16:27:28', '0', '212', '2');
INSERT INTO `quark_reply` VALUES ('55', '希鲁鲁鲁<img src=\"http://localhost:8082/layui/images/face/36.gif\" alt=\"[酷]\">', '2017-09-06 16:36:25', '0', '212', '71');
INSERT INTO `quark_reply` VALUES ('56', '希鲁鲁鲁<img src=\"http://localhost:8082/layui/images/face/36.gif\" alt=\"[酷]\">', '2017-09-06 16:36:26', '0', '212', '71');
INSERT INTO `quark_reply` VALUES ('57', '不错嘛<br>', '2017-09-06 20:22:16', '0', '4', '71');
INSERT INTO `quark_reply` VALUES ('58', '对啊', '2017-09-06 20:28:36', '0', '210', '2');
INSERT INTO `quark_reply` VALUES ('59', '不错', '2017-09-06 20:36:17', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('60', '居然自问自答，呵呵<br>', '2017-09-06 20:36:32', '0', '4', '71');
INSERT INTO `quark_reply` VALUES ('61', '再看看自问自答是否修复了', '2017-09-06 20:41:01', '0', '4', '2');
INSERT INTO `quark_reply` VALUES ('62', '很强势啊，试试推送功能', '2017-09-06 20:41:42', '0', '208', '71');
INSERT INTO `quark_reply` VALUES ('63', '测试推送？<img src=\"http://localhost:8082/layui/images/face/0.gif\" alt=\"[微笑]\">', '2017-09-07 10:03:03', '0', '210', '2');
INSERT INTO `quark_reply` VALUES ('64', '很实用<br>', '2017-09-07 10:21:23', '0', '213', '71');
INSERT INTO `quark_reply` VALUES ('65', '简直无解<img src=\"http://localhost:8082/layui/images/face/16.gif\" alt=\"[太开心]\"><img src=\"http://localhost:8082/layui/images/face/16.gif\" alt=\"[太开心]\"><img src=\"http://localhost:8082/layui/images/face/25.gif\" alt=\"[抱抱]\">', '2017-09-07 10:21:47', '0', '213', '71');
INSERT INTO `quark_reply` VALUES ('66', '很好', '2017-09-07 10:22:19', '0', '213', '71');
INSERT INTO `quark_reply` VALUES ('67', '测试推送', '2017-09-07 10:24:43', '0', '213', '71');
INSERT INTO `quark_reply` VALUES ('68', '测试测试测试', '2017-09-07 10:25:30', '0', '213', '71');
INSERT INTO `quark_reply` VALUES ('69', '测试推送', '2017-09-07 10:27:51', '0', '213', '71');
INSERT INTO `quark_reply` VALUES ('70', '<img src=\"http://localhost:8082/layui/images/face/42.gif\" alt=\"[抓狂]\">', '2017-09-07 10:39:33', '0', '209', '2');
INSERT INTO `quark_reply` VALUES ('71', '测试推送<br>', '2017-09-07 11:02:23', '0', '4', '71');
INSERT INTO `quark_reply` VALUES ('72', '测试推送', '2017-09-07 11:04:15', '0', '4', '71');
INSERT INTO `quark_reply` VALUES ('73', '测试推送<br>', '2017-09-07 11:12:22', '0', '4', '71');
INSERT INTO `quark_reply` VALUES ('74', '我再测试推送', '2017-09-07 11:18:50', '0', '4', '71');
INSERT INTO `quark_reply` VALUES ('75', '最后一次测试推送了', '2017-09-07 11:19:01', '0', '4', '71');
INSERT INTO `quark_reply` VALUES ('76', '真.最后一次测试了', '2017-09-07 11:22:23', '0', '4', '71');

-- ----------------------------
-- Table structure for quark_role
-- ----------------------------
DROP TABLE IF EXISTS `quark_role`;
CREATE TABLE `quark_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ff572j64wr6y4taed1c27vfo6` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quark_role
-- ----------------------------
INSERT INTO `quark_role` VALUES ('1', '超级权限', '超级管理员');
INSERT INTO `quark_role` VALUES ('6', '管理用户', '用户管理员');

-- ----------------------------
-- Table structure for quark_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `quark_role_permission`;
CREATE TABLE `quark_role_permission` (
  `role_id` int(11) NOT NULL,
  `permissions_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permissions_id`),
  KEY `FKlsdkf5vtsq5qvepw45r0y89jw` (`permissions_id`),
  CONSTRAINT `FK763ep4ix05naeoliv21sm6m81` FOREIGN KEY (`role_id`) REFERENCES `quark_role` (`id`),
  CONSTRAINT `FKlsdkf5vtsq5qvepw45r0y89jw` FOREIGN KEY (`permissions_id`) REFERENCES `quark_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quark_role_permission
-- ----------------------------
INSERT INTO `quark_role_permission` VALUES ('1', '1');
INSERT INTO `quark_role_permission` VALUES ('6', '1');
INSERT INTO `quark_role_permission` VALUES ('1', '2');
INSERT INTO `quark_role_permission` VALUES ('1', '3');
INSERT INTO `quark_role_permission` VALUES ('1', '4');
INSERT INTO `quark_role_permission` VALUES ('1', '5');
INSERT INTO `quark_role_permission` VALUES ('1', '6');
INSERT INTO `quark_role_permission` VALUES ('1', '7');
INSERT INTO `quark_role_permission` VALUES ('1', '8');
INSERT INTO `quark_role_permission` VALUES ('1', '9');
INSERT INTO `quark_role_permission` VALUES ('1', '10');
INSERT INTO `quark_role_permission` VALUES ('1', '11');
INSERT INTO `quark_role_permission` VALUES ('1', '12');
INSERT INTO `quark_role_permission` VALUES ('1', '13');
INSERT INTO `quark_role_permission` VALUES ('1', '16');
INSERT INTO `quark_role_permission` VALUES ('6', '16');
INSERT INTO `quark_role_permission` VALUES ('1', '17');
INSERT INTO `quark_role_permission` VALUES ('6', '17');
INSERT INTO `quark_role_permission` VALUES ('1', '18');
INSERT INTO `quark_role_permission` VALUES ('1', '19');
INSERT INTO `quark_role_permission` VALUES ('1', '20');
INSERT INTO `quark_role_permission` VALUES ('1', '21');
INSERT INTO `quark_role_permission` VALUES ('1', '22');
INSERT INTO `quark_role_permission` VALUES ('1', '24');
INSERT INTO `quark_role_permission` VALUES ('1', '25');
INSERT INTO `quark_role_permission` VALUES ('1', '26');
INSERT INTO `quark_role_permission` VALUES ('1', '27');
INSERT INTO `quark_role_permission` VALUES ('1', '28');

-- ----------------------------
-- Table structure for quark_user
-- ----------------------------
DROP TABLE IF EXISTS `quark_user`;
CREATE TABLE `quark_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) NOT NULL,
  `enable` int(11) NOT NULL,
  `init_time` datetime NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex` int(11) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2uqfoo05i1p5qm4ntysj2ivbs` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quark_user
-- ----------------------------
INSERT INTO `quark_user` VALUES ('2', 'http://127.0.0.1/images/upload/2017-09-06/70e5b07c-4609-4925-bb1f-e965daa65899.jpeg', '1', '2017-08-22 23:10:17', '25d55ad283aa400af464c76d713c07ad', '0', 'Java从入门到弃坑', 'lhr1988', '1420432344@qq.com');
INSERT INTO `quark_user` VALUES ('5', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 10:37:28', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '丁三石', '163@163.com');
INSERT INTO `quark_user` VALUES ('6', 'http://127.0.0.1/images/upload/default.png', '0', '2017-08-25 11:56:38', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '马化腾', 'qq@qq.com');
INSERT INTO `quark_user` VALUES ('7', 'http://127.0.0.1/images/upload/default.png', '0', '2017-08-25 20:51:23', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵0号', '0tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('8', 'http://127.0.0.1/images/upload/default.png', '0', '2017-08-25 20:51:23', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵1号', '1tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('9', 'http://127.0.0.1/images/upload/default.png', '0', '2017-08-25 20:51:23', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵2号', '2tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('10', 'http://127.0.0.1/images/upload/default.png', '0', '2017-08-25 20:51:23', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵3号', '3tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('11', 'http://127.0.0.1/images/upload/default.png', '0', '2017-08-25 20:51:23', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵4号', '4tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('12', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:23', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵5号', '5tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('13', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵6号', '6tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('14', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵7号', '7tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('15', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵8号', '8tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('16', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵9号', '9tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('17', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵10号', '10tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('18', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵11号', '11tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('19', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵12号', '12tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('20', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵13号', '13tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('21', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵14号', '14tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('22', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵15号', '15tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('23', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵16号', '16tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('24', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵17号', '17tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('25', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵18号', '18tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('26', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵19号', '19tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('27', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵20号', '20tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('28', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵21号', '21tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('29', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵22号', '22tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('30', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵23号', '23tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('31', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵24号', '24tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('32', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵25号', '25tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('33', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵26号', '26tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('34', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵27号', '27tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('35', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:24', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵28号', '28tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('36', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:51:25', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵29号', '29tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('37', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:18', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵30号', '30tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('38', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:18', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵31号', '31tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('39', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:18', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵32号', '32tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('40', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:18', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵33号', '33tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('41', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:18', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵34号', '34tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('42', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:18', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵35号', '35tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('43', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:18', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵36号', '36tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('44', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵37号', '37tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('45', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵38号', '38tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('46', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵39号', '39tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('47', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵40号', '40tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('48', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵41号', '41tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('49', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵42号', '42tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('50', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵43号', '43tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('51', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵44号', '44tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('52', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵45号', '45tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('53', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵46号', '46tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('54', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵47号', '47tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('55', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵48号', '48tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('56', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵49号', '49tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('57', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵50号', '50tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('58', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵51号', '51tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('59', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵52号', '52tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('60', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵53号', '53tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('61', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:19', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵54号', '54tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('62', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:20', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵55号', '55tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('63', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:20', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵56号', '56tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('64', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:20', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵57号', '57tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('65', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:20', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵58号', '58tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('66', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-25 20:52:20', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '图灵59号', '59tulin@gmail.com');
INSERT INTO `quark_user` VALUES ('67', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-26 15:03:21', 'f379eaf3c831b04de153469d1bec345e', '0', null, '拉里·佩奇', 'gmail@gmail.com');
INSERT INTO `quark_user` VALUES ('68', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-26 22:29:11', 'e10adc3949ba59abbe56e057f20f883e', '0', null, '比尔盖茨', 'Microsoft@Outlook.com');
INSERT INTO `quark_user` VALUES ('69', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-27 10:35:14', 'c8837b23ff8aaa8a2dde915473ce0991', '0', null, 'cc', '111@cc.com');
INSERT INTO `quark_user` VALUES ('70', 'http://127.0.0.1/images/upload/default.png', '1', '2017-08-31 12:13:54', 'e10adc3949ba59abbe56e057f20f883e', '0', null, 'CCTV旗下品牌', 'ccav@china.com');
INSERT INTO `quark_user` VALUES ('71', 'http://127.0.0.1/images/upload/2017-09-03/51fb238d-00a3-49d2-91c5-25d1e54fff3e.jpeg', '1', '2017-09-03 11:04:03', 'e10adc3949ba59abbe56e057f20f883e', '1', '一库~~~~', '盲僧李青', 'lol@lol.com');
INSERT INTO `quark_user` VALUES ('72', 'http://127.0.0.1/images/upload/2017-09-05/679e2736-54b5-4d73-b38f-b7b7ab61e3c1.jpeg', '1', '2017-09-05 10:31:27', 'e10adc3949ba59abbe56e057f20f883e', '1', '汪汪汪~~~', '萨摩王~', 'sa@sa.com');
