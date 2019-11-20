/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50716
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-02-27 17:46:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `address` varchar(255) CHARACTER SET cp1250 DEFAULT NULL COMMENT '地址',
  `first_name` varchar(255) DEFAULT NULL COMMENT '第一名称',
  `order_id` bigint(19) DEFAULT NULL,
  `orderPrice` decimal(10,2) DEFAULT NULL,
  `mgr` int(10) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('1', 'zhang', 'bj', 'zhang', '9', '23.30', '2', null);
SET FOREIGN_KEY_CHECKS=1;
