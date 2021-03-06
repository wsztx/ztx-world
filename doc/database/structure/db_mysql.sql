-- MySQL dump 10.13  Distrib 5.6.30, for Win64 (x86_64)
--
-- Host: localhost    Database: tworld
-- ------------------------------------------------------
-- Server version	5.6.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `base_department`
--

DROP TABLE IF EXISTS `base_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_department` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `dept_code` varchar(96) NOT NULL COMMENT '部门编码',
  `dept_name` varchar(96) DEFAULT NULL COMMENT '部门名称',
  `description` varchar(384) DEFAULT NULL COMMENT '描述',
  `parent_id` bigint(32) NOT NULL COMMENT '上级机构id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_DEPT_CODE` (`status`,`dept_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `base_organization`
--

DROP TABLE IF EXISTS `base_organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_organization` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `org_code` varchar(96) NOT NULL COMMENT '机构编码',
  `org_name` varchar(96) DEFAULT NULL COMMENT '机构名称',
  `description` varchar(384) DEFAULT NULL COMMENT '描述',
  `org_path` varchar(96) DEFAULT NULL COMMENT '机构路径',
  `parent_id` bigint(32) NOT NULL COMMENT '上级机构id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ORG_CODE` (`status`,`org_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `base_permission`
--

DROP TABLE IF EXISTS `base_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_permission` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `permission_code` varchar(96) NOT NULL COMMENT '权限编码',
  `parent_code` varchar(96) NOT NULL COMMENT '父权限编码',
  `permission_name` varchar(96) DEFAULT NULL COMMENT '权限名称',
  `permission_value` varchar(96) DEFAULT NULL COMMENT '权限值',
  `description` varchar(384) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_PERMISSION_CODE` (`status`,`permission_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `base_role`
--

DROP TABLE IF EXISTS `base_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `role_code` varchar(96) NOT NULL COMMENT '角色编码',
  `role_name` varchar(96) DEFAULT NULL COMMENT '角色名',
  `description` varchar(384) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ROLE_CODE` (`status`,`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `base_role_permission`
--

DROP TABLE IF EXISTS `base_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_role_permission` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(32) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `base_user`
--

DROP TABLE IF EXISTS `base_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `user_code` varchar(96) NOT NULL COMMENT '用户编码，登录名',
  `user_name` varchar(96) DEFAULT NULL COMMENT '名称',
  `password` varchar(96) DEFAULT NULL COMMENT '密码',
  `org_id` bigint(32) DEFAULT NULL COMMENT '所属机构id',
  `dept_id` bigint(32) DEFAULT NULL COMMENT '所属部门id',
  `description` varchar(384) DEFAULT NULL COMMENT '描述',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `user_status` int(4) DEFAULT NULL COMMENT '用户状态，0正常',
  `session_version` bigint(32) NOT NULL COMMENT 'session版本号，时间戳',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USER_CODE` (`status`,`user_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `base_user_role`
--

DROP TABLE IF EXISTS `base_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_user_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `system_config`
--

DROP TABLE IF EXISTS `system_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_config` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `config_type` varchar(96) NOT NULL COMMENT '配置类型',
  `config_key` varchar(96) NOT NULL COMMENT '配置键',
  `config_name` varchar(96) DEFAULT NULL COMMENT '配置名称',
  `config_value` varchar(96) DEFAULT NULL COMMENT '配置值',
  `use_status` int(2) DEFAULT NULL COMMENT '是否启用,1启用,0禁用',
  `description` varchar(384) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_CONFIG` (`status`,`config_type`,`config_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `system_dictionary`
--

DROP TABLE IF EXISTS `system_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_dictionary` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `dictionary_type` varchar(96) NOT NULL COMMENT '字典类型',
  `dictionary_key` varchar(96) NOT NULL COMMENT '字典键',
  `dictionary_name` varchar(96) DEFAULT NULL COMMENT '字典名称',
  `dictionary_value` varchar(96) DEFAULT NULL COMMENT '字典值',
  `description` varchar(384) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_DICTIONARY` (`status`,`dictionary_type`,`dictionary_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `system_log`
--

DROP TABLE IF EXISTS `system_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_log` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `operate_time` datetime NOT NULL COMMENT '操作时间',
  `operate_user_id` bigint(32) DEFAULT NULL COMMENT '操作人id',
  `model_type` varchar(96) DEFAULT NULL COMMENT '模块类型',
  `operate_type` varchar(96) DEFAULT NULL COMMENT '操作类型',
  `operate_object` text COMMENT '操作对象',
  `operate_result` text COMMENT '操作结果',
  `time_span` bigint(32) DEFAULT NULL COMMENT '持续时间',
  `operate_ip` varchar(96) DEFAULT NULL COMMENT '操作IP地址',
  `operate_mac` varchar(96) DEFAULT NULL COMMENT '操作mac地址',
  `description` varchar(384) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-10 21:33:52
