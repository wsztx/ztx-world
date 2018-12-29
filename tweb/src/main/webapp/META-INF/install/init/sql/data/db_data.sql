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
-- Dumping data for table `base_config`
--

LOCK TABLES `base_config` WRITE;
/*!40000 ALTER TABLE `base_config` DISABLE KEYS */;
INSERT INTO `base_config` VALUES (1,0,'2018-10-24 18:26:26','2018-10-24 18:26:28',1,'User.Login','Online.Max','同一用户最大在线数','1',1,'');
/*!40000 ALTER TABLE `base_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `base_department`
--

LOCK TABLES `base_department` WRITE;
/*!40000 ALTER TABLE `base_department` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `base_dictionary`
--

LOCK TABLES `base_dictionary` WRITE;
/*!40000 ALTER TABLE `base_dictionary` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `base_log`
--

LOCK TABLES `base_log` WRITE;
/*!40000 ALTER TABLE `base_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `base_organization`
--

LOCK TABLES `base_organization` WRITE;
/*!40000 ALTER TABLE `base_organization` DISABLE KEYS */;
INSERT INTO `base_organization` VALUES (1,0,'2018-10-23 17:16:01','2018-10-23 17:16:04',1,'00000000','主控中心',NULL,',1,',0);
/*!40000 ALTER TABLE `base_organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `base_permission`
--

LOCK TABLES `base_permission` WRITE;
/*!40000 ALTER TABLE `base_permission` DISABLE KEYS */;
INSERT INTO `base_permission` VALUES (1,0,'2018-10-24 11:22:57','2018-10-24 11:22:59',1,'01000000','-1','首页模块','index',NULL),(2,0,'2018-10-24 11:22:57','2018-10-24 11:22:59',1,'02000000','-1','基础模块','base',NULL),(3,0,'2018-10-24 11:22:57','2018-10-24 11:22:59',1,'02001000','02000000','配置管理','base:config',NULL),(4,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02001001','02001000','配置列表页面','base:config:tolist',NULL),(5,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'02001002','02001000','配置新增页面','base:config:toadd',NULL),(6,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'02001003','02001000','配置编辑页面','base:config:toedit',NULL),(7,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'02001004','02001000','配置查看页面','base:config:toview',NULL),(8,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02001005','02001000','配置查询','base:config:page',NULL),(9,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'02001006','02001000','配置新增','base:config:save',NULL),(10,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'02001007','02001000','配置修改','base:config:update',NULL),(11,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'02001008','02001000','配置删除','base:config:delete',NULL),(12,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02002000','02000000','字典管理','base:dictionary',NULL),(13,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02002001','02002000','字典列表页面','base:dictionary:tolist',NULL),(14,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'02002002','02002000','字典新增页面','base:dictionary:toadd',NULL),(15,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'02002003','02002000','字典编辑页面','base:dictionary:toedit',NULL),(16,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'02002004','02002000','字典查看页面','base:dictionary:toview',NULL),(17,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02002005','02002000','字典查询','base:dictionary:page',NULL),(18,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'02002006','02002000','字典新增','base:dictionary:save',NULL),(19,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'02002007','02002000','字典修改','base:dictionary:update',NULL),(20,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'02002008','02002000','字典删除','base:dictionary:delete',NULL),(21,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02003000','02000000','机构管理','base:organization',NULL),(22,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02003001','02003000','机构列表页面','base:organization:tolist',NULL),(23,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'02003002','02003000','机构新增页面','base:organization:toadd',NULL),(24,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'02003003','02003000','机构编辑页面','base:organization:toedit',NULL),(25,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'02003004','02003000','机构查看页面','base:organization:toview',NULL),(26,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02003005','02003000','机构查询','base:organization:page',NULL),(27,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'02003006','02003000','机构新增','base:organization:save',NULL),(28,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'02003007','02003000','机构修改','base:organization:update',NULL),(29,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'02003008','02003000','机构删除','base:organization:delete',NULL),(30,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02004000','02000000','部门管理','base:department',NULL),(31,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02004001','02004000','部门列表页面','base:department:tolist',NULL),(32,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'02004002','02004000','部门新增页面','base:department:toadd',NULL),(33,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'02004003','02004000','部门编辑页面','base:department:toedit',NULL),(34,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'02004004','02004000','部门查看页面','base:department:toview',NULL),(35,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02004005','02004000','部门查询','base:department:page',NULL),(36,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'02004006','02004000','部门新增','base:department:save',NULL),(37,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'02004007','02004000','部门修改','base:department:update',NULL),(38,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'02004008','02004000','部门删除','base:department:delete',NULL),(39,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02005000','02000000','角色管理','base:role',NULL),(40,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02005001','02005000','角色列表页面','base:role:tolist',NULL),(41,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'02005002','02005000','角色新增页面','base:role:toadd',NULL),(42,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'02005003','02005000','角色编辑页面','base:role:toedit',NULL),(43,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'02005004','02005000','角色查看页面','base:role:toview',NULL),(44,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02005005','02005000','角色查询','base:role:page',NULL),(45,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'02005006','02005000','角色新增','base:role:save',NULL),(46,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'02005007','02005000','角色修改','base:role:update',NULL),(47,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'02005008','02005000','角色删除','base:role:delete',NULL),(48,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02006000','02000000','用户管理','base:user',NULL),(49,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02006001','02006000','用户列表页面','base:user:tolist',NULL),(50,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'02006002','02006000','用户新增页面','base:user:toadd',NULL),(51,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'02006003','02006000','用户编辑页面','base:user:toedit',NULL),(52,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'02006004','02006000','用户查看页面','base:user:toview',NULL),(53,0,'2018-10-23 17:59:55','2018-10-23 17:59:58',1,'02006005','02006000','用户查询','base:user:page',NULL),(54,0,'2018-10-23 19:00:09','2018-10-23 19:00:10',1,'02006006','02006000','用户新增','base:user:save',NULL),(55,0,'2018-10-23 19:02:31','2018-10-23 19:02:33',1,'02006007','02006000','用户修改','base:user:update',NULL),(56,0,'2018-10-23 19:02:40','2018-10-23 19:02:42',1,'02006008','02006000','用户删除','base:user:delete',NULL),(57,0,'2018-11-16 18:20:58','2018-11-16 18:21:00',1,'02006009','02006000','用户分配角色','base:user:saveuserrole',NULL),(58,0,'2018-11-16 18:23:25','2018-11-16 18:23:26',1,'02006010','02006000','用户修改密码','base:user:updatepassword',NULL),(59,0,'2018-11-16 18:24:27','2018-11-16 18:24:29',1,'02006011','02006000','用户重置密码','base:user:resetpassword',NULL),(60,0,'2018-11-19 19:36:55','2018-11-19 19:36:57',1,'02006012','02006000','强制下线用户','base:user:downline',NULL),(61,0,'2018-11-18 19:42:34','2018-11-18 19:42:36',1,'02007000','02000000','缓存管理','base:cache',NULL),(62,0,'2018-11-18 19:42:34','2018-11-18 19:42:36',1,'02007001','02007000','缓存清空','base:cache:clear',NULL);
/*!40000 ALTER TABLE `base_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `base_role`
--

LOCK TABLES `base_role` WRITE;
/*!40000 ALTER TABLE `base_role` DISABLE KEYS */;
INSERT INTO `base_role` VALUES (1,0,'2018-10-23 17:14:46','2018-10-23 17:14:48',1,'SuperAdmin','超级管理员',NULL),(2,0,'2018-11-04 12:49:55','2018-11-04 12:49:57',1,'SystemAdmin','系统管理员',NULL),(3,0,'2018-11-04 12:51:02','2018-11-04 12:51:04',1,'BussinessAdmin','业务管理员',NULL),(4,0,'2018-11-04 12:52:56','2018-11-04 12:52:59',1,'OrdinaryUser','普通用户',NULL);
/*!40000 ALTER TABLE `base_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `base_role_permission`
--

LOCK TABLES `base_role_permission` WRITE;
/*!40000 ALTER TABLE `base_role_permission` DISABLE KEYS */;
INSERT INTO `base_role_permission` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,1,21),(22,1,22),(23,1,23),(24,1,24),(25,1,25),(26,1,26),(27,1,27),(28,1,28),(29,1,29),(30,1,30),(31,1,31),(32,1,32),(33,1,33),(34,1,34),(35,1,35),(36,1,36),(37,1,37),(38,1,38),(39,1,39),(40,1,40),(41,1,41),(42,1,42),(43,1,43),(44,1,44),(45,1,45),(46,1,46),(47,1,47),(48,1,48),(49,1,49),(50,1,50),(51,1,51),(52,1,52),(53,1,53),(54,1,54),(55,1,55),(56,1,56),(57,1,57),(58,1,58),(59,1,59),(60,1,60),(61,1,61),(62,1,62);
/*!40000 ALTER TABLE `base_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `base_user`
--

LOCK TABLES `base_user` WRITE;
/*!40000 ALTER TABLE `base_user` DISABLE KEYS */;
INSERT INTO `base_user` VALUES (1,0,'2018-10-19 23:35:57','2018-10-19 23:36:00',1,'SuperAdmin','超级管理员','1ac534761ddc61971850b795f078719b',1,1,NULL,'2018-12-28 22:25:24',0,1542558231967);
/*!40000 ALTER TABLE `base_user` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2018-12-29 21:05:42
