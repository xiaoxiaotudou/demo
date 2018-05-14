/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2018-05-14 22:45:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for advertisement
-- ----------------------------
DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement` (
  `pkId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryId` int(11) NOT NULL,
  `description` text NOT NULL,
  `detail` text NOT NULL,
  `weight` double NOT NULL,
  `deleted` bit(1) NOT NULL,
  UNIQUE KEY `UQ_pkId` (`pkId`),
  KEY `FK_CategoryId` (`categoryId`),
  CONSTRAINT `FK_CategoryId` FOREIGN KEY (`categoryId`) REFERENCES `advertisement_category` (`pkId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advertisement
-- ----------------------------

-- ----------------------------
-- Table structure for advertisement_category
-- ----------------------------
DROP TABLE IF EXISTS `advertisement_category`;
CREATE TABLE `advertisement_category` (
  `pkId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) NOT NULL,
  `deleted` bit(1) DEFAULT NULL,
  UNIQUE KEY `UQ_pkId` (`pkId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advertisement_category
-- ----------------------------

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `pkId` int(11) NOT NULL AUTO_INCREMENT,
  `dishName` text NOT NULL,
  `dishPrice` decimal(10,0) NOT NULL,
  `image` text NOT NULL,
  `deleted` bit(1) NOT NULL,
  UNIQUE KEY `UQ_pkId` (`pkId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dish
-- ----------------------------

-- ----------------------------
-- Table structure for restaurant
-- ----------------------------
DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE `restaurant` (
  `pkId` int(11) NOT NULL,
  `restaurantName` text NOT NULL,
  `description` text NOT NULL,
  `image` text NOT NULL,
  `deleted` bit(1) NOT NULL,
  KEY `pkId` (`pkId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of restaurant
-- ----------------------------

-- ----------------------------
-- Table structure for restaurant_dish
-- ----------------------------
DROP TABLE IF EXISTS `restaurant_dish`;
CREATE TABLE `restaurant_dish` (
  `pkId` int(11) NOT NULL AUTO_INCREMENT,
  `restaurantId` int(11) NOT NULL,
  `dishId` int(11) NOT NULL,
  UNIQUE KEY `UQ_pkId` (`pkId`),
  KEY `FK_restaurantId` (`restaurantId`),
  KEY `FK_dishId` (`dishId`),
  CONSTRAINT `FK_dishId` FOREIGN KEY (`dishId`) REFERENCES `dish` (`pkId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_restaurantId` FOREIGN KEY (`restaurantId`) REFERENCES `restaurant` (`pkId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of restaurant_dish
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `pkId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  UNIQUE KEY `uq_pkid` (`pkId`),
  UNIQUE KEY `uq_username` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '471290430@qq.com', '123456');
