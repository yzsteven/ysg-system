/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50614
Source Host           : 127.0.0.1:3306
Source Database       : ysg

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-02-07 00:22:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_company
-- ----------------------------
DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `corporation_name` varchar(20) DEFAULT NULL COMMENT '法人姓名',
  `corporation_id` varchar(35) DEFAULT NULL COMMENT '法人身份证',
  `register_num` varchar(50) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '1,个人 2，企业',
  `isdel` int(1) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_by` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_company
-- ----------------------------
INSERT INTO `sys_company` VALUES ('1', 'reqtq1', 'reqtqy1', 'rtetreq', 'tret', '1', '0', '2018-01-27 15:57:36', 'rag', '2018-01-27 15:57:40', 'dasgag');
INSERT INTO `sys_company` VALUES ('2', 'reqtq2', 'reqtqy2', 'rtetreq', 'tret', '1', '0', '2018-01-27 15:57:36', 'rag', '2018-01-27 15:57:40', 'dasgag');
INSERT INTO `sys_company` VALUES ('3', 'reqtq3', 'reqtqy3', 'rtetreq', 'tret', '1', '0', '2018-01-27 15:57:36', 'rag', '2018-01-27 15:57:40', 'dasgag');

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `company` int(10) DEFAULT NULL,
  `isdel` int(1) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_by` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', 'FAGEG1', '1', '0', '2018-01-27 16:05:04', 'FDSAG', '2018-01-27 16:05:39', 'FDAGA');
INSERT INTO `sys_department` VALUES ('2', 'FAGEG2', '1', '0', '2018-01-27 16:05:04', 'FDSAG', '2018-01-27 16:05:39', 'FDAGA');
INSERT INTO `sys_department` VALUES ('3', 'FAGEG3', '1', '0', '2018-01-27 16:05:04', 'FDSAG', '2018-01-27 16:05:39', 'FDAGA');
INSERT INTO `sys_department` VALUES ('4', 'FAGEG4', '2', '0', '2018-01-27 16:05:04', 'FDSAG', '2018-01-27 16:05:39', 'FDAGA');
INSERT INTO `sys_department` VALUES ('5', 'FAGEG5', '2', '0', '2018-01-27 16:05:04', 'FDSAG', '2018-01-27 16:05:39', 'FDAGA');
INSERT INTO `sys_department` VALUES ('6', 'FAGEG6', '2', '0', '2018-01-27 16:05:04', 'FDSAG', '2018-01-27 16:05:39', 'FDAGA');

-- ----------------------------
-- Table structure for sys_position
-- ----------------------------
DROP TABLE IF EXISTS `sys_position`;
CREATE TABLE `sys_position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `company` int(10) DEFAULT NULL,
  `isdel` int(1) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_by` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_position
-- ----------------------------
INSERT INTO `sys_position` VALUES ('1', 'test1', '1', '0', '2018-01-28 14:47:49', 'fdsag', '2018-01-28 14:47:46', 'fdagag');
INSERT INTO `sys_position` VALUES ('2', 'test2', '1', '0', '2018-01-28 14:47:49', 'fdsag', '2018-01-28 14:47:46', 'fdagag');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `discription` varchar(100) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('11', '用户管理', null, 'user:*', '1');
INSERT INTO `sys_resource` VALUES ('12', '用户新增', null, 'user:create', '1');
INSERT INTO `sys_resource` VALUES ('13', '用户修改', null, 'user:update', '1');
INSERT INTO `sys_resource` VALUES ('14', '用户删除', null, 'user:delete', '1');
INSERT INTO `sys_resource` VALUES ('15', '用户查看', null, 'user:view', '1');
INSERT INTO `sys_resource` VALUES ('21', '角色管理', null, 'role:*', '1');
INSERT INTO `sys_resource` VALUES ('22', '角色新增', null, 'role:create', '1');
INSERT INTO `sys_resource` VALUES ('23', '角色修改', null, 'role:update', '1');
INSERT INTO `sys_resource` VALUES ('24', '角色删除', null, 'role:delete', '1');
INSERT INTO `sys_resource` VALUES ('25', '角色查看', null, 'role:view', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `company` int(10) DEFAULT NULL,
  `resource_ids` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sys_role_resource_ids` (`resource_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '超级管理员', '1', '11,21', '0');
INSERT INTO `sys_role` VALUES ('2', 'admin2', '超级管理员2', '1', '11,21', '0');
INSERT INTO `sys_role` VALUES ('3', 'admin3', '超级管理员3', '1', '11,21', '0');
INSERT INTO `sys_role` VALUES ('4', 'admin4', '超级管理员4', '1', '11,21', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `role_ids` varchar(100) DEFAULT NULL,
  `realname` varchar(20) DEFAULT NULL,
  `cardid` varchar(35) DEFAULT NULL,
  `position` varchar(30) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `age` int(5) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  `contactname` varchar(30) DEFAULT NULL,
  `contactphone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3257 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '', '1', '天天', null, '系统管理员', null, null, null, null, null, null, '0', null, null);
INSERT INTO `sys_user` VALUES ('3253', 'seven', null, null, '2,3', '周洋', '320482199210020512', '1', '1', '1', '风格化', '26', '2', '15151955305', '1', '方大国1', '1514894872');
INSERT INTO `sys_user` VALUES ('3254', '范德萨个', null, null, '1,2,3', 'fda噶1', '111111111111111111', '1', '1', '1', '嘎哥让1', '26', '1', '1111111', '0', '法国1', '51515481');
INSERT INTO `sys_user` VALUES ('3255', 'sdsfsa', null, null, '1', 'fdsaga', '431513531461', '1', '1', '1', 'fsadg', '12', '2', '431151', '0', 'fasg', '14321412');
INSERT INTO `sys_user` VALUES ('3256', 'fsdagag', null, null, '1', '12312', '12313', '1', '1', '1', '13141', '12', '1', '1231', '0', '123124', '1231412');
