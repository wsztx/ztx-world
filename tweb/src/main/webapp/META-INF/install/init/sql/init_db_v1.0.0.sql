/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50630
Source Host           : localhost:3306
Source Database       : tworld

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2018-11-16 21:01:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_config
-- ----------------------------
DROP TABLE IF EXISTS `base_config`;
CREATE TABLE `base_config` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `config_type` varchar(64) NOT NULL COMMENT '配置类型',
  `config_key` varchar(64) NOT NULL COMMENT '配置键',
  `config_name` varchar(64) DEFAULT NULL COMMENT '配置名称',
  `config_value` varchar(64) DEFAULT NULL COMMENT '配置值',
  `use_status` int(2) DEFAULT NULL COMMENT '是否启用,1启用,0禁用',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_CONFIG` (`status`,`config_type`,`config_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_config
-- ----------------------------
INSERT INTO `base_config` VALUES ('1', '0', '2018-10-24 18:26:26', '2018-10-24 18:26:28', '1', 'user.login', 'online.max', '用户同时最大在线数', '1', '1', '');

-- ----------------------------
-- Table structure for base_department
-- ----------------------------
DROP TABLE IF EXISTS `base_department`;
CREATE TABLE `base_department` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `dept_code` varchar(64) NOT NULL COMMENT '部门编码',
  `dept_name` varchar(64) DEFAULT NULL COMMENT '部门名称',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `parent_id` bigint(32) NOT NULL COMMENT '上级机构id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_DEPT_CODE` (`status`,`dept_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_department
-- ----------------------------

-- ----------------------------
-- Table structure for base_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `base_dictionary`;
CREATE TABLE `base_dictionary` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `dictionary_type` varchar(64) NOT NULL COMMENT '字典类型',
  `dictionary_key` varchar(64) NOT NULL COMMENT '字典键',
  `dictionary_name` varchar(64) DEFAULT NULL COMMENT '字典名称',
  `dictionary_value` varchar(64) DEFAULT NULL COMMENT '字典值',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_DICTIONARY` (`status`,`dictionary_type`,`dictionary_key`) USING BTREE
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
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `org_code` varchar(64) NOT NULL COMMENT '机构编码',
  `org_name` varchar(64) DEFAULT NULL COMMENT '机构名称',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `org_path` varchar(64) DEFAULT NULL COMMENT '机构路径',
  `parent_id` bigint(32) NOT NULL COMMENT '上级机构id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ORG_CODE` (`status`,`org_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_organization
-- ----------------------------
INSERT INTO `base_organization` VALUES ('1', '0', '2018-10-23 17:16:01', '2018-10-23 17:16:04', '1', '00000000', '主控中心', null, ',1,', '0');

-- ----------------------------
-- Table structure for base_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_permission`;
CREATE TABLE `base_permission` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `permission_code` varchar(64) NOT NULL COMMENT '权限编码',
  `permission_name` varchar(64) DEFAULT NULL COMMENT '权限名称',
  `permission_value` varchar(64) DEFAULT NULL COMMENT '权限值',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_PERMISSION_CODE` (`status`,`permission_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_permission
-- ----------------------------
INSERT INTO `base_permission` VALUES ('1', '0', '2018-10-24 11:22:57', '2018-10-24 11:22:59', '1', '00000000', '首页', 'index', null);
INSERT INTO `base_permission` VALUES ('2', '0', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01001001', '配置列表页面', 'base:config:tolist', null);
INSERT INTO `base_permission` VALUES ('3', '0', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01001002', '配置新增页面', 'base:config:toadd', null);
INSERT INTO `base_permission` VALUES ('4', '0', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01001003', '配置编辑页面', 'base:config:toedit', null);
INSERT INTO `base_permission` VALUES ('5', '0', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01001004', '配置查看页面', 'base:config:toview', null);
INSERT INTO `base_permission` VALUES ('6', '0', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01001005', '配置查询', 'base:config:page', '');
INSERT INTO `base_permission` VALUES ('7', '0', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01001006', '配置新增', 'base:config:save', '');
INSERT INTO `base_permission` VALUES ('8', '0', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01001007', '配置修改', 'base:config:update', '');
INSERT INTO `base_permission` VALUES ('9', '0', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01001008', '配置删除', 'base:config:delete', '');
INSERT INTO `base_permission` VALUES ('10', '0', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01002001', '字典列表页面', 'base:dictionary:tolist', '');
INSERT INTO `base_permission` VALUES ('11', '0', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01002002', '字典新增页面', 'base:dictionary:toadd', '');
INSERT INTO `base_permission` VALUES ('12', '0', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01002003', '字典编辑页面', 'base:dictionary:toedit', '');
INSERT INTO `base_permission` VALUES ('13', '0', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01002004', '字典查看页面', 'base:dictionary:toview', '');
INSERT INTO `base_permission` VALUES ('14', '0', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01002005', '字典查询', 'base:dictionary:page', '');
INSERT INTO `base_permission` VALUES ('15', '0', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01002006', '字典新增', 'base:dictionary:save', '');
INSERT INTO `base_permission` VALUES ('16', '0', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01002007', '字典修改', 'base:dictionary:update', '');
INSERT INTO `base_permission` VALUES ('17', '0', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01002008', '字典删除', 'base:dictionary:delete', '');
INSERT INTO `base_permission` VALUES ('18', '0', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01003001', '机构列表页面', 'base:organization:tolist', '');
INSERT INTO `base_permission` VALUES ('19', '0', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01003002', '机构新增页面', 'base:organization:toadd', '');
INSERT INTO `base_permission` VALUES ('20', '0', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01003003', '机构编辑页面', 'base:organization:toedit', '');
INSERT INTO `base_permission` VALUES ('21', '0', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01003004', '机构查看页面', 'base:organization:toview', '');
INSERT INTO `base_permission` VALUES ('22', '0', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01003005', '机构查询', 'base:organization:page', '');
INSERT INTO `base_permission` VALUES ('23', '0', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01003006', '机构新增', 'base:organization:save', '');
INSERT INTO `base_permission` VALUES ('24', '0', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01003007', '机构修改', 'base:organization:update', '');
INSERT INTO `base_permission` VALUES ('25', '0', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01003008', '机构删除', 'base:organization:delete', '');
INSERT INTO `base_permission` VALUES ('26', '0', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01004001', '部门列表页面', 'base:department:tolist', '');
INSERT INTO `base_permission` VALUES ('27', '0', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01004002', '部门新增页面', 'base:department:toadd', '');
INSERT INTO `base_permission` VALUES ('28', '0', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01004003', '部门编辑页面', 'base:department:toedit', '');
INSERT INTO `base_permission` VALUES ('29', '0', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01004004', '部门查看页面', 'base:department:toview', '');
INSERT INTO `base_permission` VALUES ('30', '0', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01004005', '部门查询', 'base:department:page', '');
INSERT INTO `base_permission` VALUES ('31', '0', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01004006', '部门新增', 'base:department:save', '');
INSERT INTO `base_permission` VALUES ('32', '0', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01004007', '部门修改', 'base:department:update', '');
INSERT INTO `base_permission` VALUES ('33', '0', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01004008', '部门删除', 'base:department:delete', '');
INSERT INTO `base_permission` VALUES ('34', '0', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01005001', '角色列表页面', 'base:role:tolist', '');
INSERT INTO `base_permission` VALUES ('35', '0', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01005002', '角色新增页面', 'base:role:toadd', '');
INSERT INTO `base_permission` VALUES ('36', '0', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01005003', '角色编辑页面', 'base:role:toedit', '');
INSERT INTO `base_permission` VALUES ('37', '0', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01005004', '角色查看页面', 'base:role:toview', '');
INSERT INTO `base_permission` VALUES ('38', '0', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01005005', '角色查询', 'base:role:page', '');
INSERT INTO `base_permission` VALUES ('39', '0', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01005006', '角色新增', 'base:role:save', '');
INSERT INTO `base_permission` VALUES ('40', '0', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01005007', '角色修改', 'base:role:update', '');
INSERT INTO `base_permission` VALUES ('41', '0', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01005008', '角色删除', 'base:role:delete', '');
INSERT INTO `base_permission` VALUES ('42', '0', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01006001', '用户列表页面', 'base:user:tolist', '');
INSERT INTO `base_permission` VALUES ('43', '0', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01006002', '用户新增页面', 'base:user:toadd', '');
INSERT INTO `base_permission` VALUES ('44', '0', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01006003', '用户编辑页面', 'base:user:toedit', '');
INSERT INTO `base_permission` VALUES ('45', '0', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01006004', '用户查看页面', 'base:user:toview', '');
INSERT INTO `base_permission` VALUES ('46', '0', '2018-10-23 17:59:55', '2018-10-23 17:59:58', '1', '01006005', '用户查询', 'base:user:page', '');
INSERT INTO `base_permission` VALUES ('47', '0', '2018-10-23 19:00:09', '2018-10-23 19:00:10', '1', '01006006', '用户新增', 'base:user:save', '');
INSERT INTO `base_permission` VALUES ('48', '0', '2018-10-23 19:02:31', '2018-10-23 19:02:33', '1', '01006007', '用户修改', 'base:user:update', '');
INSERT INTO `base_permission` VALUES ('49', '0', '2018-10-23 19:02:40', '2018-10-23 19:02:42', '1', '01006008', '用户删除', 'base:user:delete', '');
INSERT INTO `base_permission` VALUES ('50', '0', '2018-11-16 18:20:58', '2018-11-16 18:21:00', '1', '01006009', '用户分配角色', 'base:user:distributerole', null);
INSERT INTO `base_permission` VALUES ('51', '0', '2018-11-16 18:23:25', '2018-11-16 18:23:26', '1', '01006010', '用户修改密码', 'base:user:modifypassword', null);
INSERT INTO `base_permission` VALUES ('52', '0', '2018-11-16 18:24:27', '2018-11-16 18:24:29', '1', '01006011', '用户重置密码', 'base:user:resetpassword', null);

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `role_code` varchar(64) NOT NULL COMMENT '角色编码',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ROLE_CODE` (`status`,`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES ('1', '0', '2018-10-23 17:14:46', '2018-10-23 17:14:48', '1', 'SuperAdmin', '超级管理员', null);
INSERT INTO `base_role` VALUES ('2', '0', '2018-11-04 12:49:55', '2018-11-04 12:49:57', '1', 'SystemAdmin', '系统管理员', null);
INSERT INTO `base_role` VALUES ('3', '0', '2018-11-04 12:51:02', '2018-11-04 12:51:04', '1', 'BussinessAdmin', '业务管理员', null);
INSERT INTO `base_role` VALUES ('4', '0', '2018-11-04 12:52:56', '2018-11-04 12:52:59', '1', 'OrdinaryUser', '普通用户', null);

-- ----------------------------
-- Table structure for base_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_role_permission`;
CREATE TABLE `base_role_permission` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(32) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

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
INSERT INTO `base_role_permission` VALUES ('26', '1', '26');
INSERT INTO `base_role_permission` VALUES ('27', '1', '27');
INSERT INTO `base_role_permission` VALUES ('28', '1', '28');
INSERT INTO `base_role_permission` VALUES ('29', '1', '29');
INSERT INTO `base_role_permission` VALUES ('30', '1', '30');
INSERT INTO `base_role_permission` VALUES ('31', '1', '31');
INSERT INTO `base_role_permission` VALUES ('32', '1', '32');
INSERT INTO `base_role_permission` VALUES ('33', '1', '33');
INSERT INTO `base_role_permission` VALUES ('34', '1', '34');
INSERT INTO `base_role_permission` VALUES ('35', '1', '35');
INSERT INTO `base_role_permission` VALUES ('36', '1', '36');
INSERT INTO `base_role_permission` VALUES ('37', '1', '37');
INSERT INTO `base_role_permission` VALUES ('38', '1', '38');
INSERT INTO `base_role_permission` VALUES ('39', '1', '39');
INSERT INTO `base_role_permission` VALUES ('40', '1', '40');
INSERT INTO `base_role_permission` VALUES ('41', '1', '41');
INSERT INTO `base_role_permission` VALUES ('42', '1', '42');
INSERT INTO `base_role_permission` VALUES ('43', '1', '43');
INSERT INTO `base_role_permission` VALUES ('44', '1', '44');
INSERT INTO `base_role_permission` VALUES ('45', '1', '45');
INSERT INTO `base_role_permission` VALUES ('46', '1', '46');
INSERT INTO `base_role_permission` VALUES ('47', '1', '47');
INSERT INTO `base_role_permission` VALUES ('48', '1', '48');
INSERT INTO `base_role_permission` VALUES ('49', '1', '49');
INSERT INTO `base_role_permission` VALUES ('50', '1', '50');
INSERT INTO `base_role_permission` VALUES ('51', '1', '51');
INSERT INTO `base_role_permission` VALUES ('52', '1', '52');

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `user_code` varchar(64) NOT NULL COMMENT '用户编码，登录名',
  `user_name` varchar(64) DEFAULT NULL COMMENT '名称',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `org_id` bigint(32) DEFAULT NULL COMMENT '所属机构id',
  `dept_id` bigint(32) DEFAULT NULL COMMENT '所属部门id',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `user_status` int(4) DEFAULT NULL COMMENT '用户状态，0正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USER_CODE` (`status`,`user_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES ('1', '0', '2018-10-19 23:35:57', '2018-10-19 23:36:00', '1', 'SuperAdmin', '超级管理员', '407ec58e5b61475836123dbac712ec68', '1', '1', null, '2018-11-16 19:59:53', '0');

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
