# MySQL-Front 5.1  (Build 4.13)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: renshi
# ------------------------------------------------------
# Server version 5.1.62-community

DROP DATABASE IF EXISTS `renshi`;
CREATE DATABASE `renshi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `renshi`;

#
# Source for table t_bumen
#

DROP TABLE IF EXISTS `t_bumen`;
CREATE TABLE `t_bumen` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `deletestatus` int(11) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `jibengongzi` varchar(255) DEFAULT NULL COMMENT '基本工资',
  `name` varchar(255) DEFAULT NULL COMMENT '部门名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

#
# Dumping data for table t_bumen
#

LOCK TABLES `t_bumen` WRITE;
/*!40000 ALTER TABLE `t_bumen` DISABLE KEYS */;
INSERT INTO `t_bumen` VALUES (9,0,'3500','人事部');
INSERT INTO `t_bumen` VALUES (10,0,'4000','开发部');
INSERT INTO `t_bumen` VALUES (11,0,'3000','财务部');
INSERT INTO `t_bumen` VALUES (12,1,'5000','经理办公室');
/*!40000 ALTER TABLE `t_bumen` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_jiangjin
#

DROP TABLE IF EXISTS `t_jiangjin`;
CREATE TABLE `t_jiangjin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL COMMENT '奖金事由',
  `createtime` datetime DEFAULT NULL COMMENT '添加时间',
  `deletestatus` int(11) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `riqi` varchar(255) DEFAULT NULL COMMENT '日期',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `FK1BF6547F142D988B` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

#
# Dumping data for table t_jiangjin
#

LOCK TABLES `t_jiangjin` WRITE;
/*!40000 ALTER TABLE `t_jiangjin` DISABLE KEYS */;
INSERT INTO `t_jiangjin` VALUES (9,'加班两天','2019-11-29 21:13:19',0,'2019-11-30',7);
/*!40000 ALTER TABLE `t_jiangjin` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_kaoqin
#

DROP TABLE IF EXISTS `t_kaoqin`;
CREATE TABLE `t_kaoqin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `beizhu` varchar(255) DEFAULT NULL COMMENT '备注',
  `createtime` datetime DEFAULT NULL COMMENT '添加时间',
  `deletestatus` int(11) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `leixing` varchar(255) DEFAULT NULL COMMENT '类型',
  `riqi` varchar(255) DEFAULT NULL COMMENT '日期',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `FK38FEDB28142D988B` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

#
# Dumping data for table t_kaoqin
#

LOCK TABLES `t_kaoqin` WRITE;
/*!40000 ALTER TABLE `t_kaoqin` DISABLE KEYS */;
INSERT INTO `t_kaoqin` VALUES (14,'请假',NULL,0,'请假',NULL,7);
INSERT INTO `t_kaoqin` VALUES (15,'请假',NULL,0,'请假',NULL,7);
INSERT INTO `t_kaoqin` VALUES (16,'早退30分钟','2019-11-11',0,'早退',NULL,7);
/*!40000 ALTER TABLE `t_kaoqin` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_peixun
#

DROP TABLE IF EXISTS `t_peixun`;
CREATE TABLE `t_peixun` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL COMMENT '添加时间',
  `deletestatus` int(11) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `peixundidian` varchar(255) DEFAULT NULL COMMENT '培训地点',
  `peixunjihua` varchar(255) DEFAULT NULL COMMENT '培训计划',
  `peixunneirong` varchar(255) DEFAULT NULL COMMENT '培训内容',
  `peixunzhouqi` varchar(255) DEFAULT NULL COMMENT '培训周期',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `FK41BCD5C8142D988B` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Dumping data for table t_peixun
#

LOCK TABLES `t_peixun` WRITE;
/*!40000 ALTER TABLE `t_peixun` DISABLE KEYS */;
INSERT INTO `t_peixun` VALUES (7,NULL,0,'办公室101','学习开发','springboot 、java','两周',7);
/*!40000 ALTER TABLE `t_peixun` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_qingjia
#

DROP TABLE IF EXISTS `t_qingjia`;
CREATE TABLE `t_qingjia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL COMMENT '请假原因',
  `createtime` datetime DEFAULT NULL COMMENT '添加时间',
  `deletestatus` int(11) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `qingjiariqi` varchar(255) DEFAULT NULL COMMENT '日期',
  `shenhe` varchar(255) DEFAULT NULL COMMENT '审核',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `FK31D612A6142D988B` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Dumping data for table t_qingjia
#

LOCK TABLES `t_qingjia` WRITE;
/*!40000 ALTER TABLE `t_qingjia` DISABLE KEYS */;
INSERT INTO `t_qingjia` VALUES (3,'请假','2019-11-29 20:56:09',0,'2019-11-29','审核通过',7);
INSERT INTO `t_qingjia` VALUES (4,'请假','2019-11-29 21:01:16',0,'2019-11-30','审核通过',7);
INSERT INTO `t_qingjia` VALUES (5,'早退','2019-11-29 21:01:42',1,'2019-11-28','审核未通过',7);
/*!40000 ALTER TABLE `t_qingjia` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_user
#

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL COMMENT '创建日期',
  `deletestatus` int(11) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `dizhi` varchar(255) DEFAULT NULL COMMENT '地址',
  `jiguan` varchar(255) DEFAULT NULL COMMENT '籍贯',
  `lianxifangshi` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `role` int(11) NOT NULL DEFAULT '0' COMMENT '用户角色',
  `ruzhishijian` varchar(255) DEFAULT NULL COMMENT '入职时间',
  `truename` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `wenhuachengdu` varchar(255) DEFAULT NULL COMMENT '文化程度',
  `xingbie` varchar(255) DEFAULT NULL COMMENT '性别',
  `zhengzhimianmao` varchar(255) DEFAULT NULL COMMENT '政治面貌',
  `zhiwu` varchar(255) DEFAULT NULL COMMENT '职务',
  `bumenid` varchar(255) DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`),
  KEY `FKCB5540D68738F247` (`bumenid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Dumping data for table t_user
#

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (6,NULL,0,NULL,NULL,NULL,'111111',1,NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `t_user` VALUES (7,'2019-11-29 20:41:00',0,'内蒙古大学北校区','内蒙古呼和浩特市','15628302471','1',0,'2019-11-29 20:41:00','李华','sonya','本科','女','党员','人事助理','9');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

#
#  Foreign keys for table t_jiangjin
#

ALTER TABLE `t_jiangjin`
ADD CONSTRAINT `FK1BF6547F142D988B` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`);

#
#  Foreign keys for table t_kaoqin
#

ALTER TABLE `t_kaoqin`
ADD CONSTRAINT `FK38FEDB28142D988B` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`);

#
#  Foreign keys for table t_peixun
#

ALTER TABLE `t_peixun`
ADD CONSTRAINT `FK41BCD5C8142D988B` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`);

#
#  Foreign keys for table t_qingjia
#

ALTER TABLE `t_qingjia`
ADD CONSTRAINT `FK31D612A6142D988B` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`);


/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
