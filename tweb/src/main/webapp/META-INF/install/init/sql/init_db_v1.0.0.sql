/*
Navicat MySQL Data Transfer

Source Server         : mysql_local
Source Server Version : 50630
Source Host           : localhost:3306
Source Database       : tworld

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2018-10-24 17:51:51
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
  `config_type` varchar(64) DEFAULT NULL COMMENT '配置类型',
  `config_key` varchar(64) DEFAULT NULL COMMENT '配置键',
  `config_name` varchar(64) DEFAULT NULL COMMENT '配置名称',
  `config_value` varchar(64) DEFAULT NULL COMMENT '配置值',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_config
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_department
-- ----------------------------
INSERT INTO `base_department` VALUES ('1', '1', '2018-10-23 17:16:29', '2018-10-23 17:16:33', '1', null, null, null);

-- ----------------------------
-- Table structure for base_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `base_dictionary`;
CREATE TABLE `base_dictionary` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` int(2) NOT NULL COMMENT '逻辑删除位，0删除，1未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `dictionary_type` varchar(64) DEFAULT NULL COMMENT '字典类型',
  `dictionary_key` varchar(64) DEFAULT NULL COMMENT '字典键',
  `dictionary_name` varchar(64) DEFAULT NULL COMMENT '字典名称',
  `dictionary_value` varchar(64) DEFAULT NULL COMMENT '字典值',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_dictionary
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_organization
-- ----------------------------
INSERT INTO `base_organization` VALUES ('1', '1', '2018-10-23 17:16:01', '2018-10-23 17:16:04', '1', null, null, null, null);

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_permission
-- ----------------------------
INSERT INTO `base_permission` VALUES ('1', '1', '2018-10-24 11:22:57', '2018-10-24 11:22:59', '1', '00000000', '首页', 'index', null);
INSERT INTO `base_permission` VALUES ('2', '1', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01001001', '配置列表页面', 'base:config:tolist', null);
INSERT INTO `base_permission` VALUES ('3', '1', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01001002', '配置新增页面', 'base:config:toadd', null);
INSERT INTO `base_permission` VALUES ('4', '1', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01001003', '配置编辑页面', 'base:config:toedit', null);
INSERT INTO `base_permission` VALUES ('5', '1', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01001004', '配置查看页面', 'base:config:toview', null);
INSERT INTO `base_permission` VALUES ('6', '1', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01002001', '字典列表页面', 'base:dict:tolist', '');
INSERT INTO `base_permission` VALUES ('7', '1', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01002002', '字典新增页面', 'base:dict:toadd', '');
INSERT INTO `base_permission` VALUES ('8', '1', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01002003', '字典编辑页面', 'base:dict:toedit', '');
INSERT INTO `base_permission` VALUES ('9', '1', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01002004', '字典查看页面', 'base:dict:toview', '');
INSERT INTO `base_permission` VALUES ('10', '1', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01003001', '机构列表页面', 'base:org:tolist', '');
INSERT INTO `base_permission` VALUES ('11', '1', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01003002', '机构新增页面', 'base:org:toadd', '');
INSERT INTO `base_permission` VALUES ('12', '1', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01003003', '机构编辑页面', 'base:org:toedit', '');
INSERT INTO `base_permission` VALUES ('13', '1', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01003004', '机构查看页面', 'base:org:toview', '');
INSERT INTO `base_permission` VALUES ('14', '1', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01004001', '部门列表页面', 'base:dept:tolist', '');
INSERT INTO `base_permission` VALUES ('15', '1', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01004002', '部门新增页面', 'base:dept:toadd', '');
INSERT INTO `base_permission` VALUES ('16', '1', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01004003', '部门编辑页面', 'base:dept:toedit', '');
INSERT INTO `base_permission` VALUES ('17', '1', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01004004', '部门查看页面', 'base:dept:toview', '');
INSERT INTO `base_permission` VALUES ('18', '1', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01005001', '角色列表页面', 'base:role:tolist', '');
INSERT INTO `base_permission` VALUES ('19', '1', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01005002', '角色新增页面', 'base:role:toadd', '');
INSERT INTO `base_permission` VALUES ('20', '1', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01005003', '角色编辑页面', 'base:role:toedit', '');
INSERT INTO `base_permission` VALUES ('21', '1', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01005004', '角色查看页面', 'base:role:toview', '');
INSERT INTO `base_permission` VALUES ('22', '1', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01006001', '用户列表页面', 'base:user:tolist', '');
INSERT INTO `base_permission` VALUES ('23', '1', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01006002', '用户新增页面', 'base:user:toadd', '');
INSERT INTO `base_permission` VALUES ('24', '1', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01006003', '用户编辑页面', 'base:user:toedit', '');
INSERT INTO `base_permission` VALUES ('25', '1', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01006004', '用户查看页面', 'base:user:toview', '');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES ('1', '1', '2018-10-23 17:14:46', '2018-10-23 17:14:48', '1', 'admin', '系统管理员', null);

-- ----------------------------
-- Table structure for base_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_role_permission`;
CREATE TABLE `base_role_permission` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(32) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_role_permission
-- ----------------------------
INSERT INTO `base_role_permission` VALUES ('1', '1', '1');
INSERT INTO `base_role_permission` VALUES ('2', '1', '2');
INSERT INTO `base_role_permission` VALUES ('3', '1', '3');
INSERT INTO `base_role_permission` VALUES ('4', '1', '4');
INSERT INTO `base_role_permission` VALUES ('5', '1', '5');
INSERT INTO `base_role_permission` VALUES ('6', '1', '6');
INSERT INTO `base_role_permission` VALUES ('7', '1', '7');
INSERT INTO `base_role_permission` VALUES ('8', '1', '8');
INSERT INTO `base_role_permission` VALUES ('9', '1', '9');
INSERT INTO `base_role_permission` VALUES ('10', '1', '10');
INSERT INTO `base_role_permission` VALUES ('11', '1', '11');
INSERT INTO `base_role_permission` VALUES ('12', '1', '12');
INSERT INTO `base_role_permission` VALUES ('13', '1', '13');
INSERT INTO `base_role_permission` VALUES ('14', '1', '14');
INSERT INTO `base_role_permission` VALUES ('15', '1', '15');
INSERT INTO `base_role_permission` VALUES ('16', '1', '16');
INSERT INTO `base_role_permission` VALUES ('17', '1', '17');
INSERT INTO `base_role_permission` VALUES ('18', '1', '18');
INSERT INTO `base_role_permission` VALUES ('19', '1', '19');
INSERT INTO `base_role_permission` VALUES ('20', '1', '20');
INSERT INTO `base_role_permission` VALUES ('21', '1', '21');
INSERT INTO `base_role_permission` VALUES ('22', '1', '22');
INSERT INTO `base_role_permission` VALUES ('23', '1', '23');
INSERT INTO `base_role_permission` VALUES ('24', '1', '24');
INSERT INTO `base_role_permission` VALUES ('25', '1', '25');

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
INSERT INTO `base_user` VALUES ('1', '1', '2018-10-19 23:35:57', '2018-10-19 23:36:00', '1', 'admin', '管理员', '407ec58e5b61475836123dbac712ec68', '1', '1', null, '2018-10-24 17:51:29', '0');

-- ----------------------------
-- Table structure for base_user_role
-- ----------------------------
DROP TABLE IF EXISTS `base_user_role`;
CREATE TABLE `base_user_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user_role
-- ----------------------------
INSERT INTO `base_user_role` VALUES ('1', '1', '1');
