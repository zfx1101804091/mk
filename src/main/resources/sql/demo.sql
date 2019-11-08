/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50722
Source Host           : 127.0.0.1:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-11-08 12:59:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for one_s
-- ----------------------------
DROP TABLE IF EXISTS `one_s`;
CREATE TABLE `one_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_s` varchar(255) DEFAULT NULL,
  `value_s` int(11) DEFAULT NULL,
  `long_s` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of one_s
-- ----------------------------
INSERT INTO `one_s` VALUES ('1', '商超门店', '1200', '12');
INSERT INTO `one_s` VALUES ('2', '教育培训', '1515', '12');
INSERT INTO `one_s` VALUES ('3', '房地产', '424', '12');
INSERT INTO `one_s` VALUES ('4', '生活服务', '1200', '12');
INSERT INTO `one_s` VALUES ('5', '汽车销售', '1100', '2');
INSERT INTO `one_s` VALUES ('6', '旅游酒店', '1500', '11');
INSERT INTO `one_s` VALUES ('7', '五金建材', '1200', '221');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `login_name` varchar(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `state` int(11) DEFAULT '1',
  `phone` varchar(60) DEFAULT NULL,
  `code` varchar(6) DEFAULT NULL,
  `operator_id` int(11) DEFAULT NULL,
  `editflag` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('37', '2', 'zs', '张三', '123', '1', null, 'HETI', null, '2019-11-08 12:49:51');
