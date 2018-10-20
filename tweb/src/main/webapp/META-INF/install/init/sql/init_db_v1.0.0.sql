/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50630
Source Host           : localhost:3306
Source Database       : tworld

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2018-10-20 22:37:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_config
-- ----------------------------
DROP TABLE IF EXISTS `base_config`;
CREATE TABLE `base_config` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `config_code` varchar(64) DEFAULT NULL COMMENT '配置编码',
  `config_name` varchar(64) DEFAULT NULL COMMENT '配置名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_config
-- ----------------------------

-- ----------------------------
-- Table structure for base_constant
-- ----------------------------
DROP TABLE IF EXISTS `base_constant`;
CREATE TABLE `base_constant` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `constant_type` varchar(64) DEFAULT NULL COMMENT '常量类型',
  `constant_key` varchar(64) DEFAULT NULL COMMENT '常量键',
  `constant_value` varchar(64) DEFAULT NULL COMMENT '常量值',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_constant
-- ----------------------------

-- ----------------------------
-- Table structure for base_department
-- ----------------------------
DROP TABLE IF EXISTS `base_department`;
CREATE TABLE `base_department` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `dept_code` varchar(64) DEFAULT NULL COMMENT '部门编码',
  `dept_name` varchar(64) DEFAULT NULL COMMENT '部门名称',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_department
-- ----------------------------

-- ----------------------------
-- Table structure for base_organization
-- ----------------------------
DROP TABLE IF EXISTS `base_organization`;
CREATE TABLE `base_organization` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `org_code` varchar(64) DEFAULT NULL COMMENT '机构编码',
  `org_name` varchar(64) DEFAULT NULL COMMENT '机构名称',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `org_path` varchar(64) DEFAULT NULL COMMENT '机构路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_CODE` (`org_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_organization
-- ----------------------------

-- ----------------------------
-- Table structure for base_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_permission`;
CREATE TABLE `base_permission` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `permission_code` varchar(64) DEFAULT NULL COMMENT '权限编码',
  `permission_name` varchar(64) DEFAULT NULL COMMENT '权限名称',
  `permission_value` varchar(64) DEFAULT NULL COMMENT '权限值',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_CODE` (`permission_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_permission
-- ----------------------------

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `role_code` varchar(64) DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_CODE` (`role_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_role
-- ----------------------------

-- ----------------------------
-- Table structure for base_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_role_permission`;
CREATE TABLE `base_role_permission` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(32) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `user_code` varchar(64) DEFAULT NULL COMMENT '用户编码，登录名',
  `user_name` varchar(64) DEFAULT NULL COMMENT '名称',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `org_id` bigint(32) DEFAULT NULL COMMENT '所属机构id',
  `dept_id` bigint(32) DEFAULT NULL COMMENT '所属部门id',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `user_status` int(4) DEFAULT NULL COMMENT '用户状态，0正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_LOGIN_CODE` (`user_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES ('1', '1', '2018-10-19 23:35:57', '2018-10-19 23:36:00', null, 'admin', '管理员', '407ec58e5b61475836123dbac712ec68', '1', null, null, '2018-10-20 22:19:00', '0');

-- ----------------------------
-- Table structure for base_user_role
-- ----------------------------
DROP TABLE IF EXISTS `base_user_role`;
CREATE TABLE `base_user_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user_role
-- ----------------------------
