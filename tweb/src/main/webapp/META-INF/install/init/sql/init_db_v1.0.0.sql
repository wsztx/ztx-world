/*
Navicat MySQL Data Transfer

Source Server         : mysql_local
Source Server Version : 50630
Source Host           : localhost:3306
Source Database       : tworld

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2018-09-21 16:15:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_config
-- ----------------------------
DROP TABLE IF EXISTS `base_config`;
CREATE TABLE `base_config` (
  `ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CODE` varchar(64) NOT NULL COMMENT '配置编码',
  `STATUS` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `CONFIG_NAME` varchar(64) DEFAULT NULL COMMENT '配置名称',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_function
-- ----------------------------
DROP TABLE IF EXISTS `base_function`;
CREATE TABLE `base_function` (
  `ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CODE` varchar(64) NOT NULL COMMENT '权限编码',
  `NAME` varchar(64) DEFAULT NULL COMMENT '权限名称',
  `STATUS` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_CODE` (`CODE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_organization
-- ----------------------------
DROP TABLE IF EXISTS `base_organization`;
CREATE TABLE `base_organization` (
  `ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CODE` varchar(64) NOT NULL COMMENT '机构编码',
  `NAME` varchar(64) DEFAULT NULL COMMENT '机构名称',
  `STATUS` int(2) DEFAULT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_CODE` (`CODE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CODE` varchar(64) NOT NULL COMMENT '角色编码',
  `STATUS` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `NAME` varchar(64) DEFAULT NULL COMMENT '角色名',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_CODE` (`CODE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_role_function
-- ----------------------------
DROP TABLE IF EXISTS `base_role_function`;
CREATE TABLE `base_role_function` (
  `ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_ID` int(32) DEFAULT NULL COMMENT '角色id',
  `FUNCTION_ID` int(32) DEFAULT NULL COMMENT '权限id',
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
  `CODE` varchar(64) NOT NULL COMMENT '用户编码，登录名',
  `NAME` varchar(64) DEFAULT NULL COMMENT '名称',
  `PASSWORD` varchar(64) NOT NULL COMMENT '密码',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ORG_ID` int(32) DEFAULT NULL COMMENT '所属机构id',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_LOGIN_NAME` (`CODE`) USING BTREE
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
