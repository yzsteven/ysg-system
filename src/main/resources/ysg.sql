/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50614
Source Host           : 127.0.0.1:3306
Source Database       : ysg

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-04-02 23:32:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_banner
-- ----------------------------
DROP TABLE IF EXISTS `sys_banner`;
CREATE TABLE `sys_banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `imgurl` varchar(100) DEFAULT NULL,
  `linkurl` varchar(100) DEFAULT NULL,
  `type` varchar(1) DEFAULT NULL COMMENT '1，主页 2，新品 3，推荐 4，精选',
  `order` int(3) DEFAULT NULL,
  `cid` bigint(25) DEFAULT NULL COMMENT '店铺id',
  `isdel` int(1) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_by` varchar(25) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_banner
-- ----------------------------
INSERT INTO `sys_banner` VALUES ('1', 'test1', 'fsdagf', '123ewrq', 'rewqt', '1', '1', '1', '0', '2018-02-27 13:26:51', 'zy', '2018-02-27 13:26:51', null);
INSERT INTO `sys_banner` VALUES ('2', 'test2', 'fdsag', 'fdsafga', 'fdasgf', '1', '2', '1', '0', '2018-02-27 13:27:50', 'zy', '2018-02-27 13:27:50', null);
INSERT INTO `sys_banner` VALUES ('3', 'test3', 'fdsage', 'fdsqgq', 'fdswqg', '1', '3', '1', '0', '2018-02-27 13:28:20', 'zy', '2018-02-27 13:28:20', null);
INSERT INTO `sys_banner` VALUES ('4', 'test4', 'ssssss', 'ssss', 'ssss', '2', '1', '1', '0', '2018-02-28 15:10:26', null, '2018-02-28 15:10:26', null);

-- ----------------------------
-- Table structure for sys_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `banner` varchar(100) DEFAULT NULL,
  `cid` bigint(25) DEFAULT NULL,
  `order` int(5) DEFAULT NULL,
  `isdel` int(1) DEFAULT NULL,
  `create_by` varchar(25) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(25) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_category
-- ----------------------------
INSERT INTO `sys_category` VALUES ('1', '粮食', 'liangshi', null, '试试', '1', '1', '0', null, null, null, null);
INSERT INTO `sys_category` VALUES ('2', '玩具', 'wanju', null, '试试2', '1', '2', '0', null, null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_company
-- ----------------------------
INSERT INTO `sys_company` VALUES ('1', 'ysg', 'ysg', 'rtetreq', 'tret', '1', '0', '2018-01-27 15:57:36', 'zy', '2018-01-27 15:57:40', 'dasgag');

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
-- Table structure for sys_good
-- ----------------------------
DROP TABLE IF EXISTS `sys_good`;
CREATE TABLE `sys_good` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `coverImg` varchar(5) DEFAULT NULL COMMENT '商品封面图片',
  `images` varchar(50) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL COMMENT '商品详情',
  `parameter` varchar(255) DEFAULT NULL COMMENT '产品参数',
  `service` varchar(255) DEFAULT NULL COMMENT '售后保障',
  `stock` int(3) DEFAULT NULL COMMENT '库存',
  `categoryId` bigint(20) DEFAULT NULL,
  `isNew` int(1) DEFAULT NULL,
  `isRecommend` int(1) DEFAULT NULL,
  `isSelected` int(1) DEFAULT NULL,
  `isdel` int(1) DEFAULT NULL,
  `cid` bigint(20) DEFAULT NULL COMMENT '商铺编号',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_good
-- ----------------------------
INSERT INTO `sys_good` VALUES ('1', '大米', null, '1,2,3', null, null, null, null, '1', '1', '1', '1', '0', '1', null, null, null, null);
INSERT INTO `sys_good` VALUES ('2', '油', null, null, null, null, null, null, '1', '1', '1', '1', '0', '1', null, null, null, null);
INSERT INTO `sys_good` VALUES ('3', '玉米', null, null, null, null, null, null, '1', '1', '1', '1', '0', '1', null, null, null, null);
INSERT INTO `sys_good` VALUES ('4', '汽车', null, '1,2,3', null, null, null, null, '2', '1', '1', '1', '0', '1', null, null, null, null);

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order` (
  `id` bigint(25) NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(50) DEFAULT NULL,
  `goodsInfo` varchar(50) DEFAULT NULL,
  `cid` bigint(25) DEFAULT NULL,
  `totalPrice` decimal(10,2) DEFAULT NULL,
  `payPrice` decimal(10,2) DEFAULT NULL,
  `payNo` varchar(50) DEFAULT NULL,
  `paytime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `paytype` int(1) DEFAULT NULL,
  `expressFee` decimal(10,0) DEFAULT NULL,
  `contactName` varchar(50) DEFAULT NULL,
  `contactPhone` varchar(11) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `isdel` int(1) DEFAULT '0',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_order
-- ----------------------------
INSERT INTO `sys_order` VALUES ('1', '53f97653-7dc6-4f25-97d8-029897f1bef4', null, '1', '100.00', null, null, '2018-03-08 01:31:28', null, '0', '1', '12', '11', '0', 'api', '2018-03-08 01:30:45', null, '2018-03-08 01:30:45');
INSERT INTO `sys_order` VALUES ('2', 'c0fc1c53-e270-40ce-b09b-d760a1385d59', null, '1', '100.00', null, null, null, null, '0', '1', '12', '11', '0', 'api', '2018-03-08 01:32:03', null, '2018-03-08 01:32:03');
INSERT INTO `sys_order` VALUES ('3', '29506437-8300-454a-ba42-2c0c06e87ae0', null, '1', '100.00', null, null, null, null, '0', '1', '12', '11', '0', 'api', '2018-03-08 01:34:09', null, '2018-03-08 01:34:09');
INSERT INTO `sys_order` VALUES ('4', '18030801490001', null, '1', '100.00', null, null, null, null, '0', '1', '12', '11', '0', 'api', '2018-03-08 01:49:29', null, '2018-03-08 01:49:29');

-- ----------------------------
-- Table structure for sys_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_goods`;
CREATE TABLE `sys_order_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) DEFAULT NULL,
  `gid` bigint(20) DEFAULT NULL,
  `spec` varchar(20) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `num` int(5) DEFAULT NULL,
  `isdel` int(1) DEFAULT '0',
  `create_by` varchar(25) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(25) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_order_goods
-- ----------------------------
INSERT INTO `sys_order_goods` VALUES ('1', '1', '1', '大', '100.00', '1', '0', null, '2018-03-08 01:30:45', null, '2018-03-08 01:30:45');
INSERT INTO `sys_order_goods` VALUES ('2', '2', '1', '大', '100.00', '1', '0', null, '2018-03-08 01:32:35', null, '2018-03-08 01:32:35');
INSERT INTO `sys_order_goods` VALUES ('3', '3', '1', '大', '100.00', '1', '0', 'api', '2018-03-08 01:34:09', null, '2018-03-08 01:34:09');
INSERT INTO `sys_order_goods` VALUES ('4', '4', '1', '大', '100.00', '1', '0', 'api', '2018-03-08 01:49:29', null, '2018-03-08 01:49:29');

-- ----------------------------
-- Table structure for sys_picture
-- ----------------------------
DROP TABLE IF EXISTS `sys_picture`;
CREATE TABLE `sys_picture` (
  `id` bigint(25) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `gid` bigint(25) DEFAULT NULL,
  `isdel` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(25) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_picture
-- ----------------------------
INSERT INTO `sys_picture` VALUES ('1', 'test1', 'test1', 'faga1', '1', '0', '2018-03-01 14:25:05', null, '2018-03-01 14:25:05', null);
INSERT INTO `sys_picture` VALUES ('2', 'test2', 'test2', 'faga2', '1', '0', '2018-03-01 14:25:02', null, '2018-03-01 14:25:02', null);
INSERT INTO `sys_picture` VALUES ('3', 'test3', 'test3', 'faga3', '1', '0', '2018-03-01 14:25:03', null, '2018-03-01 14:25:03', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '超级管理员', '1', '11,21', '0', null, null, null, null);
INSERT INTO `sys_role` VALUES ('2', '哈哈', '小管理', '1', '11,21', '0', null, null, null, null);

-- ----------------------------
-- Table structure for sys_spec
-- ----------------------------
DROP TABLE IF EXISTS `sys_spec`;
CREATE TABLE `sys_spec` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `order` varchar(1) DEFAULT NULL COMMENT '顺序',
  `gid` bigint(20) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `isdel` int(1) DEFAULT NULL,
  `create_by` varchar(25) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(25) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_spec
-- ----------------------------
INSERT INTO `sys_spec` VALUES ('1', '小', '1', '1', '10.00', '0', null, null, null, null);
INSERT INTO `sys_spec` VALUES ('2', '大', '2', '1', '20.00', '0', null, null, null, null);
INSERT INTO `sys_spec` VALUES ('3', '100', '1', '2', '10.00', '0', '', '2018-02-28 00:26:59', '', '2018-02-28 00:27:04');
INSERT INTO `sys_spec` VALUES ('4', '200', '2', '2', '20.00', '0', '', '2018-02-28 00:27:01', '', '2018-02-28 00:27:06');
INSERT INTO `sys_spec` VALUES ('5', '365', '1', '3', '15.00', '0', null, null, null, null);
INSERT INTO `sys_spec` VALUES ('6', '500', '2', '3', '25.00', '0', null, null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '582bb853bf3cd0447ba4945bd51521a5', '867fe6308aed551c6e1321f529fa9e07', '1', '哈哈', null, '1', '1', '1', null, null, null, null, '0', null, null, null, '2018-02-09 14:00:52', null, '2018-02-09 14:00:52', null);
INSERT INTO `sys_user` VALUES ('13', 'zy', 'b03df910354cb041af53a0cee1636f7a', '1f64d5d8b0ec5c4a6df138dae52a26af', '5', '', '', '1', '1', '1', '', '11', '1', '', '1', '', '', '0', '2018-02-13 15:49:14', 'admin', '2018-02-13 15:49:14', null);
INSERT INTO `sys_user` VALUES ('15', 'zy1', '69acdc6710a03f8355602972f9068f25', 'd57c424591ff2fc67835730f9dbde531', '1', '', '213415', '1', '1', '1', '', '11', '1', '4215315', '1', '', '', '0', '2018-02-24 10:52:54', 'admin', '2018-02-24 10:52:54', null);
INSERT INTO `sys_user` VALUES ('17', 'zy3', 'a20c15572b3a0da263a716f1a9131723', '2d75b1631cd91dc0708ec5a7a1d0eb94', '1', '', '', '1', '1', '1', '', '12', '1', '1231', '1', '', '', '0', '2018-02-26 00:51:40', 'admin', '2018-02-26 00:51:40', null);
