/*
Navicat MySQL Data Transfer

Source Server         : mysql_local
Source Server Version : 50630
Source Host           : localhost:3306
Source Database       : tworld

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2018-09-21 14:40:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_config
-- ----------------------------
DROP TABLE IF EXISTS `base_config`;
CREATE TABLE `base_config` (
  `ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `STATUS` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `CONFIG_NAME` varchar(64) DEFAULT NULL COMMENT '配置名称',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_organization
-- ----------------------------
DROP TABLE IF EXISTS `base_organization`;
CREATE TABLE `base_organization` (
  `ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ORG_NAME` varchar(64) DEFAULT NULL COMMENT '机构名称',
  `STATUS` int(2) DEFAULT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_power
-- ----------------------------
DROP TABLE IF EXISTS `base_power`;
CREATE TABLE `base_power` (
  `ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `POWER_NAME` varchar(64) DEFAULT NULL COMMENT '权限名称',
  `STATUS` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `STATUS` int(2) DEFAULT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `ROLE_NAME` varchar(64) DEFAULT NULL COMMENT '角色名',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_role_power
-- ----------------------------
DROP TABLE IF EXISTS `base_role_power`;
CREATE TABLE `base_role_power` (
  `ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_ID` int(32) DEFAULT NULL COMMENT '角色id',
  `POWER_ID` int(32) DEFAULT NULL COMMENT '权限id',
  `STATUS` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `STATUS` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `LOGIN_NAME` varchar(64) NOT NULL COMMENT '登录名',
  `USER_NAME` varchar(64) DEFAULT NULL COMMENT '名称',
  `PASSWORD` varchar(64) NOT NULL COMMENT '密码',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ORG_ID` int(32) DEFAULT NULL COMMENT '所属机构id',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_LOGIN_NAME` (`LOGIN_NAME`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_user_role
-- ----------------------------
DROP TABLE IF EXISTS `base_user_role`;
CREATE TABLE `base_user_role` (
  `ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` int(32) DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` int(32) DEFAULT NULL COMMENT '角色ID',
  `STATUS` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
