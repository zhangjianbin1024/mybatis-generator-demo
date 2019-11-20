/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50716
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-03-03 21:51:20
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
  `order_id` bigint(19) DEFAULT NULL COMMENT '订单id',
  `orderPrice` decimal(10,2) DEFAULT NULL COMMENT '订单价格',
  `mgr` int(10) DEFAULT NULL COMMENT '管理者',
  `status` tinyint(2) DEFAULT NULL COMMENT '逻辑删除',
  `version` bigint(10) unsigned DEFAULT '0' COMMENT '版本',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('1', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('2', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('3', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('4', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('5', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('6', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('7', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('8', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('9', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('10', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('11', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('12', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('13', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('14', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('15', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('16', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('17', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('18', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('19', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
INSERT INTO `t_user_info` VALUES ('20', 'zhangjianbin', 'bj', 'zhang', '0', '0.00', '0', '1', '0', '2019-03-03 20:17:30', '2019-03-03 20:17:30');
