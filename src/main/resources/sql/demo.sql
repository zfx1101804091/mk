/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-11-08 23:43:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for one_a
-- ----------------------------
DROP TABLE IF EXISTS `one_a`;
CREATE TABLE `one_a` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name_z` varchar(255) DEFAULT NULL,
  `name_x` varchar(255) DEFAULT NULL,
  `value_z` int(11) DEFAULT NULL,
  `name_c` varchar(255) DEFAULT NULL,
  `name_v` varchar(255) DEFAULT NULL,
  `value_x` int(11) DEFAULT NULL,
  `name_b` varchar(255) DEFAULT NULL,
  `name_n` varchar(255) DEFAULT NULL,
  `value_c` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of one_a
-- ----------------------------
INSERT INTO `one_a` VALUES ('0000000001', '0岁以下', '0岁以下', '1', '电子商务', '电子商务', '5', '汽车', '汽车', '10');
INSERT INTO `one_a` VALUES ('0000000002', '20-29岁', '20-29岁', '4', '教育', '教育', '1', '旅游', '旅游', '3');
INSERT INTO `one_a` VALUES ('0000000003', '30-39岁', '30-39岁', '2', 'IT/互联网', 'IT/互联网', '6', '财经', '财经', '1');
INSERT INTO `one_a` VALUES ('0000000004', '40-49岁', '40-49岁', '1', '金融', '金融', '2', '教育', '教育', '4');
INSERT INTO `one_a` VALUES ('0000000005', '50岁以上', '50岁以上', '1', '学生', '学生', '1', '软件', '软件', '8');
INSERT INTO `one_a` VALUES ('0000000006', '其他', '其他', '1', '其他', '其他', '1', '其他', '其他', '2');

-- ----------------------------
-- Table structure for one_q
-- ----------------------------
DROP TABLE IF EXISTS `one_q`;
CREATE TABLE `one_q` (
  `id` int(11) NOT NULL,
  `name_q` varchar(255) DEFAULT NULL COMMENT 'name_q的值和name_Q的值相同',
  `name_y` varchar(255) DEFAULT NULL COMMENT 'name_q的值和name_Q的值相同',
  `value_q` varchar(255) DEFAULT NULL,
  `value_y` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of one_q
-- ----------------------------
INSERT INTO `one_q` VALUES ('1', '浙江', '浙江', '80', '20');
INSERT INTO `one_q` VALUES ('2', '上海', '上海', '70', '30');
INSERT INTO `one_q` VALUES ('3', '广东', '广东', '65', '35');
INSERT INTO `one_q` VALUES ('4', '北京', '北京', '60', '40');
INSERT INTO `one_q` VALUES ('5', '深圳', '深圳', '50', '50');

-- ----------------------------
-- Table structure for one_s
-- ----------------------------
DROP TABLE IF EXISTS `one_s`;
CREATE TABLE `one_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_s` varchar(255) DEFAULT NULL,
  `value_s` int(11) DEFAULT NULL,
  `long_s` varchar(11) DEFAULT NULL,
  `city_s` varchar(20) DEFAULT NULL,
  `city_value` int(20) DEFAULT NULL,
  `city_sa` varchar(20) DEFAULT NULL,
  `city_valuea` int(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of one_s
-- ----------------------------
INSERT INTO `one_s` VALUES ('1', '商超门店', '1200', '12', '浙江', '1500', '浙江', '5');
INSERT INTO `one_s` VALUES ('2', '教育培训', '1515', '110', '北京', '1100', '上海', '44');
INSERT INTO `one_s` VALUES ('3', '房地产', '424', '12', '上海', '8000', '广东', '52');
INSERT INTO `one_s` VALUES ('4', '生活服务', '1200', '120', '江苏', '4242', '深圳', '52');
INSERT INTO `one_s` VALUES ('5', '汽车销售', '1100', '25', '安徽', '2000', '四川', '554');
INSERT INTO `one_s` VALUES ('6', '旅游酒店', '1500', '11', '广东', '1500', '北京', '7');
INSERT INTO `one_s` VALUES ('7', '五金建材', '1200', '221', '河南', '1200', '江苏', '45');

-- ----------------------------
-- Table structure for one_z
-- ----------------------------
DROP TABLE IF EXISTS `one_z`;
CREATE TABLE `one_z` (
  `id` varchar(255) NOT NULL,
  `wave_X` varchar(255) DEFAULT NULL,
  `wave_Y` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of one_z
-- ----------------------------
INSERT INTO `one_z` VALUES ('1', '1', '4');
INSERT INTO `one_z` VALUES ('2', '8', '4');
INSERT INTO `one_z` VALUES ('3', '2', '8');
INSERT INTO `one_z` VALUES ('4', '5', '7');
INSERT INTO `one_z` VALUES ('5', '7', '7');
INSERT INTO `one_z` VALUES ('6', '3', '3');
INSERT INTO `one_z` VALUES ('7', '6', '3');
INSERT INTO `one_z` VALUES ('8', '4', '1');
INSERT INTO `one_z` VALUES ('9', '1', '3');
INSERT INTO `one_z` VALUES ('10', '1', '5');
INSERT INTO `one_z` VALUES ('11', '2', '8');
INSERT INTO `one_z` VALUES ('12', '3', '7');
INSERT INTO `one_z` VALUES ('13', '3', '2');
INSERT INTO `one_z` VALUES ('14', '2', '3');
INSERT INTO `one_z` VALUES ('15', '3', '7');
INSERT INTO `one_z` VALUES ('16', '8', '3');
INSERT INTO `one_z` VALUES ('17', '7', '6');
INSERT INTO `one_z` VALUES ('18', '7', '3');
INSERT INTO `one_z` VALUES ('19', '7', '6');
INSERT INTO `one_z` VALUES ('20', '1', '5');
INSERT INTO `one_z` VALUES ('21', '5', '3');
INSERT INTO `one_z` VALUES ('22', '9', '6');
INSERT INTO `one_z` VALUES ('23', '3', '8');
INSERT INTO `one_z` VALUES ('24', '1', '5');

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
