/*
Navicat MySQL Data Transfer

Source Server         : demo
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : ysg

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2018-02-13 15:50:30
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
  `available` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('11', '用户管理', null, 'user:*', '0');
INSERT INTO `sys_resource` VALUES ('12', '用户新增', null, 'user:create', '0');
INSERT INTO `sys_resource` VALUES ('13', '用户修改', null, 'user:update', '0');
INSERT INTO `sys_resource` VALUES ('14', '用户删除', null, 'user:delete', '0');
INSERT INTO `sys_resource` VALUES ('15', '用户查看', null, 'user:view', '0');
INSERT INTO `sys_resource` VALUES ('21', '角色管理', null, 'role:*', '0');
INSERT INTO `sys_resource` VALUES ('22', '角色新增', null, 'role:create', '0');
INSERT INTO `sys_resource` VALUES ('23', '角色修改', null, 'role:update', '0');
INSERT INTO `sys_resource` VALUES ('24', '角色删除', null, 'role:delete', '0');
INSERT INTO `sys_resource` VALUES ('25', '角色查看', null, 'role:view', '0');

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
  `create_by` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_role_resource_ids` (`resource_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '超级管理员', '1', '11,21', '0', null, null, null, null);

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
  `type` int(1) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '默认系统当前时间\r\n',
  `create_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '582bb853bf3cd0447ba4945bd51521a5', '867fe6308aed551c6e1321f529fa9e07', '1', '哈哈', null, '1', '1', '1', null, null, null, null, '0', null, null, null, '2018-02-09 14:00:52', null, '2018-02-09 14:00:52', null);
INSERT INTO `sys_user` VALUES ('13', 'zy', 'b03df910354cb041af53a0cee1636f7a', '1f64d5d8b0ec5c4a6df138dae52a26af', '5', '', '', '1', '1', '1', '', '11', '1', '', '0', '', '', '1', '2018-02-13 15:49:14', 'admin', '2018-02-13 15:49:14', null);
