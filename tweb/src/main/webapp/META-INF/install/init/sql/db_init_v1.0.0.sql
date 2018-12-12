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
-- Table structure for table `base_config`
--

DROP TABLE IF EXISTS `base_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_config` (
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
-- Dumping data for table `base_config`
--

LOCK TABLES `base_config` WRITE;
/*!40000 ALTER TABLE `base_config` DISABLE KEYS */;
INSERT INTO `base_config` VALUES (1,0,'2018-10-24 18:26:26','2018-10-24 18:26:28',1,'User.Login','Online.Max','同一用户最大在线数','1',1,'');
/*!40000 ALTER TABLE `base_config` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `base_department`
--

LOCK TABLES `base_department` WRITE;
/*!40000 ALTER TABLE `base_department` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_dictionary`
--

DROP TABLE IF EXISTS `base_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_dictionary` (
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
-- Dumping data for table `base_dictionary`
--

LOCK TABLES `base_dictionary` WRITE;
/*!40000 ALTER TABLE `base_dictionary` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_log`
--

DROP TABLE IF EXISTS `base_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_log` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` bigint(32) NOT NULL COMMENT '逻辑删除位，0未删除',
  `operate_time` datetime NOT NULL COMMENT '操作时间',
  `operate_user_id` bigint(32) DEFAULT NULL COMMENT '操作人id',
  `model_type` varchar(96) DEFAULT NULL COMMENT '模块类型',
  `operate_type` varchar(96) DEFAULT NULL COMMENT '操作类型',
  `operate_object` varchar(3840) DEFAULT NULL COMMENT '操作对象',
  `operate_result` varchar(3840) DEFAULT NULL COMMENT '操作结果',
  `time_span` bigint(32) DEFAULT NULL COMMENT '持续时间',
  `operate_ip` varchar(96) DEFAULT NULL COMMENT '操作IP地址',
  `operate_mac` varchar(96) DEFAULT NULL COMMENT '操作mac地址',
  `description` varchar(384) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_log`
--

LOCK TABLES `base_log` WRITE;
/*!40000 ALTER TABLE `base_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_log` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `base_organization`
--

LOCK TABLES `base_organization` WRITE;
/*!40000 ALTER TABLE `base_organization` DISABLE KEYS */;
INSERT INTO `base_organization` VALUES (1,0,'2018-10-23 17:16:01','2018-10-23 17:16:04',1,'00000000','主控中心',NULL,',1,',0);
/*!40000 ALTER TABLE `base_organization` ENABLE KEYS */;
UNLOCK TABLES;

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
  `permission_name` varchar(96) DEFAULT NULL COMMENT '权限名称',
  `permission_value` varchar(96) DEFAULT NULL COMMENT '权限值',
  `description` varchar(384) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_PERMISSION_CODE` (`status`,`permission_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_permission`
--

LOCK TABLES `base_permission` WRITE;
/*!40000 ALTER TABLE `base_permission` DISABLE KEYS */;
INSERT INTO `base_permission` VALUES (1,0,'2018-10-24 11:22:57','2018-10-24 11:22:59',1,'00000000','首页','index',NULL),(2,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'01001001','配置列表页面','base:config:tolist',NULL),(3,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'01001002','配置新增页面','base:config:toadd',NULL),(4,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'01001003','配置编辑页面','base:config:toedit',NULL),(5,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'01001004','配置查看页面','base:config:toview',NULL),(6,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'01001005','配置查询','base:config:page',''),(7,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'01001006','配置新增','base:config:save',''),(8,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'01001007','配置修改','base:config:update',''),(9,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'01001008','配置删除','base:config:delete',''),(10,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'01002001','字典列表页面','base:dictionary:tolist',''),(11,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'01002002','字典新增页面','base:dictionary:toadd',''),(12,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'01002003','字典编辑页面','base:dictionary:toedit',''),(13,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'01002004','字典查看页面','base:dictionary:toview',''),(14,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'01002005','字典查询','base:dictionary:page',''),(15,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'01002006','字典新增','base:dictionary:save',''),(16,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'01002007','字典修改','base:dictionary:update',''),(17,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'01002008','字典删除','base:dictionary:delete',''),(18,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'01003001','机构列表页面','base:organization:tolist',''),(19,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'01003002','机构新增页面','base:organization:toadd',''),(20,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'01003003','机构编辑页面','base:organization:toedit',''),(21,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'01003004','机构查看页面','base:organization:toview',''),(22,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'01003005','机构查询','base:organization:page',''),(23,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'01003006','机构新增','base:organization:save',''),(24,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'01003007','机构修改','base:organization:update',''),(25,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'01003008','机构删除','base:organization:delete',''),(26,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'01004001','部门列表页面','base:department:tolist',''),(27,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'01004002','部门新增页面','base:department:toadd',''),(28,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'01004003','部门编辑页面','base:department:toedit',''),(29,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'01004004','部门查看页面','base:department:toview',''),(30,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'01004005','部门查询','base:department:page',''),(31,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'01004006','部门新增','base:department:save',''),(32,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'01004007','部门修改','base:department:update',''),(33,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'01004008','部门删除','base:department:delete',''),(34,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'01005001','角色列表页面','base:role:tolist',''),(35,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'01005002','角色新增页面','base:role:toadd',''),(36,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'01005003','角色编辑页面','base:role:toedit',''),(37,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'01005004','角色查看页面','base:role:toview',''),(38,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'01005005','角色查询','base:role:page',''),(39,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'01005006','角色新增','base:role:save',''),(40,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'01005007','角色修改','base:role:update',''),(41,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'01005008','角色删除','base:role:delete',''),(42,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'01006001','用户列表页面','base:user:tolist',''),(43,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'01006002','用户新增页面','base:user:toadd',''),(44,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'01006003','用户编辑页面','base:user:toedit',''),(45,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'01006004','用户查看页面','base:user:toview',''),(46,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'01006005','用户查询','base:user:page',''),(47,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'01006006','用户新增','base:user:save',''),(48,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'01006007','用户修改','base:user:update',''),(49,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'01006008','用户删除','base:user:delete',''),(50,0,'2018-11-16 18:20:58','2018-11-16 18:21:00',1,'01006009','用户分配角色','base:user:saveuserrole',NULL),(51,0,'2018-11-16 18:23:25','2018-11-16 18:23:26',1,'01006010','用户修改密码','base:user:updatepassword',NULL),(52,0,'2018-11-16 18:24:27','2018-11-16 18:24:29',1,'01006011','用户重置密码','base:user:resetpassword',NULL),(53,0,'2018-11-19 19:36:55','2018-11-19 19:36:57',1,'01006012','强制下线用户','base:user:downline',NULL),(54,0,'2018-11-18 19:42:34','2018-11-18 19:42:36',1,'01007001','缓存清空','base:cache:clear',NULL);
/*!40000 ALTER TABLE `base_permission` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `base_role`
--

LOCK TABLES `base_role` WRITE;
/*!40000 ALTER TABLE `base_role` DISABLE KEYS */;
INSERT INTO `base_role` VALUES (1,0,'2018-10-23 17:14:46','2018-10-23 17:14:48',1,'SuperAdmin','超级管理员',NULL),(2,0,'2018-11-04 12:49:55','2018-11-04 12:49:57',1,'SystemAdmin','系统管理员',NULL),(3,0,'2018-11-04 12:51:02','2018-11-04 12:51:04',1,'BussinessAdmin','业务管理员',NULL),(4,0,'2018-11-04 12:52:56','2018-11-04 12:52:59',1,'OrdinaryUser','普通用户',NULL);
/*!40000 ALTER TABLE `base_role` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_role_permission`
--

LOCK TABLES `base_role_permission` WRITE;
/*!40000 ALTER TABLE `base_role_permission` DISABLE KEYS */;
INSERT INTO `base_role_permission` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,1,21),(22,1,22),(23,1,23),(24,1,24),(25,1,25),(26,1,26),(27,1,27),(28,1,28),(29,1,29),(30,1,30),(31,1,31),(32,1,32),(33,1,33),(34,1,34),(35,1,35),(36,1,36),(37,1,37),(38,1,38),(39,1,39),(40,1,40),(41,1,41),(42,1,42),(43,1,43),(44,1,44),(45,1,45),(46,1,46),(47,1,47),(48,1,48),(49,1,49),(50,1,50),(51,1,51),(52,1,52),(53,1,53);
/*!40000 ALTER TABLE `base_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `base_user`
--

LOCK TABLES `base_user` WRITE;
/*!40000 ALTER TABLE `base_user` DISABLE KEYS */;
INSERT INTO `base_user` VALUES (1,0,'2018-10-19 23:35:57','2018-10-19 23:36:00',1,'SuperAdmin','超级管理员','1ac534761ddc61971850b795f078719b',1,1,NULL,'2018-12-07 19:45:10',0,1542558231967);
/*!40000 ALTER TABLE `base_user` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `base_user_role`
--

LOCK TABLES `base_user_role` WRITE;
/*!40000 ALTER TABLE `base_user_role` DISABLE KEYS */;
INSERT INTO `base_user_role` VALUES (1,1,1);
/*!40000 ALTER TABLE `base_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-12 20:14:38
