/*
SQLyog Ultimate v12.09 (32 bit)
MySQL - 5.5.47 : Database - facedata
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`facedata` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `facedata`;

/*Table structure for table `attend` */

DROP TABLE IF EXISTS `attend`;

CREATE TABLE `attend` (
  `attend_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考勤id',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `grade_id` int(11) DEFAULT NULL COMMENT '班级id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `student_image` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '学生图片',
  PRIMARY KEY (`attend_id`),
  KEY `fk_atten_stuedntid` (`student_id`),
  KEY `fk_atten_gradeid` (`grade_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `attend` */

/*Table structure for table `grade` */

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `grade_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级',
  `name` varchar(15) COLLATE utf8_bin NOT NULL DEFAULT '""' COMMENT '姓名',
  `teacher_id` int(11) NOT NULL COMMENT '教师id',
  `number` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '人数',
  `hour` int(11) DEFAULT NULL,
  PRIMARY KEY (`grade_id`),
  KEY `fk_grade_teacherid` (`teacher_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `grade` */

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(11) COLLATE utf8_bin NOT NULL DEFAULT '""',
  `sex` int(1) DEFAULT '0' COMMENT '0:男 1:女',
  `age` int(3) DEFAULT NULL,
  `department` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `student_image` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `id` (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `student` */

insert  into `student`(`student_id`,`name`,`sex`,`age`,`department`,`student_image`) values (1,'杨城欢',0,24,'计算机系',NULL),(2,'陈泽荣',0,24,'软件系',NULL),(3,'马延浩',0,23,'国贸系',NULL);

/*Table structure for table `student_grade` */

DROP TABLE IF EXISTS `student_grade`;

CREATE TABLE `student_grade` (
  `student_grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `grade_id` int(11) NOT NULL,
  `signnumber` int(2) DEFAULT NULL COMMENT '签到次数',
  PRIMARY KEY (`student_grade_id`),
  KEY `fk_student_grade_gradeid` (`grade_id`),
  KEY `fk_student_grade_studentid` (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `student_grade` */

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(11) COLLATE utf8_bin DEFAULT '""',
  `sex` int(2) DEFAULT NULL COMMENT '0:男 1:女',
  `department` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `teacher_image` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `teacher` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
