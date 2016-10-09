/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - student
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`student` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `student`;

/*Table structure for table `attend` */

DROP TABLE IF EXISTS `attend`;

CREATE TABLE `attend` (
  `attendid` int(11) NOT NULL AUTO_INCREMENT COMMENT '考勤id',
  `studentid` char(11) NOT NULL COMMENT '学生id',
  `courseid` char(11)  NOT NULL COMMENT '班级id',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `imagepath` varchar(50) DEFAULT NULL COMMENT '学生图片',
  PRIMARY KEY (`attendid`),
  CONSTRAINT `FK_course_student` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`),
  CONSTRAINT `FK_course_course` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `attend` */

 /*insert  into `attend`(`attendid`,`studentid`,`courseid`,`status`,`imagepath`) values (1,'081101','101',1,NULL);*/

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `courseid` char(11) NOT NULL DEFAULT '',
  `coursename` varchar(50) DEFAULT NULL,
  `semester` tinyint(3) DEFAULT NULL,
  `hours` int(11) DEFAULT '0',
  `number` int(4) DEFAULT '0',
  `credit` int(6) DEFAULT '0',
  `teacherid` char(11) DEFAULT '1',
  PRIMARY KEY (`courseid`),
  KEY `FK_course_teacherid` (`teacherid`),
  CONSTRAINT `FK_course_teacher` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`courseid`,`coursename`,`semester`,`hours`,`number`,`credit`,`teacherid`) values ('101','计算机基础',1,80,50,5,'1'),('102','程序设计语言',2,68,45,4,'1'),('103','JAVA程序设计',4,82,50,4,'2'),('104','软件工程导论',4,82,50,3,'1'),('105','AJAX',4,80,50,3,'1'),('206','离散数学',4,68,60,4,'1'),('208','数据结构',5,68,53,4,'1'),('209','操作系统',6,68,55,4,'1'),('210','计算机原理',5,85,23,5,'1'),('212','数据库原理',7,68,33,4,'1'),('301','计算机网络',7,51,52,3,'2'),('302','软件工程',7,51,56,3,'2');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `teacherid` char(11) NOT NULL,
  `name` varchar(15) NOT NULL DEFAULT '',
  `password` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`teacherid`),
  CONSTRAINT `FK_login_teacherid` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `login` */

insert  into `login`(`teacherid`,`name`,`password`) values ('1','081101','123'),('2','081102','123');

/*Table structure for table `major` */

DROP TABLE IF EXISTS `major`;

CREATE TABLE `major` (
  `majorid` int(11) NOT NULL AUTO_INCREMENT,
  `majorname` varchar(50) NOT NULL DEFAULT '',
  `number` int(11) DEFAULT '0',
  `teacherid` char(11) DEFAULT '0',
  PRIMARY KEY (`majorid`),
  KEY `FK_major_teacherid` (`teacherid`),
  CONSTRAINT `FK_major_teacher` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `major` */

insert  into `major`(`majorid`,`majorname`,`number`,`teacherid`) values (1,'软件系',150,'1'),(2,'通信工程',131,'2');

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `studentid` char(11) NOT NULL DEFAULT '',
  `courseid` char(11) NOT NULL DEFAULT '',
  `score` float(6,1) DEFAULT '0.0',
  `credit` int(6) DEFAULT '0',
  PRIMARY KEY (`studentid`,`courseid`),
  KEY `FK_score_courseid` (`courseid`),
  CONSTRAINT `FK_score_course` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`),
  CONSTRAINT `FK_score_student` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `score` */

insert  into `score`(`studentid`,`courseid`,`score`,`credit`) values ('081101','101',80.0,5),('081101','102',78.0,4),('081101','206',76.0,4),('081101','210',86.0,5),('081102','101',60.0,5),('081102','102',58.0,0),('081103','101',62.0,5),('081103','102',70.0,4),('081103','206',81.0,4),('081106','206',86.0,4),('081201','206',60.0,4);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `studentid` char(11) NOT NULL DEFAULT '',
  `studentname` varchar(50) NOT NULL DEFAULT '',
  `sex` int(2) DEFAULT '0',
  `datatime` datetime DEFAULT NULL,
  `majorid` int(11) NOT NULL DEFAULT '0',
  `score` int(11) DEFAULT '0',
  `remark` varchar(500) DEFAULT NULL,
  `imagepath` varchar(50) DEFAULT '',
  PRIMARY KEY (`studentid`),
  KEY `majorid` (`majorid`),
  CONSTRAINT `FK_student_major` FOREIGN KEY (`majorid`) REFERENCES `major` (`majorid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`studentid`,`studentname`,`sex`,`datatime`,`majorid`,`score`,`remark`,`imagepath`) values ('081101','王林',0,'1990-02-10 00:00:00',1,50,NULL,''),('081102','程明',0,'1991-02-01 00:00:00',1,50,NULL,''),('081103','王燕',1,'1989-10-06 00:00:00',1,50,NULL,''),('081104','韦严平',1,'1990-08-26 00:00:00',1,50,NULL,''),('081105','谢霆锋',0,'1981-12-09 00:00:00',1,60,'','');

/*Table structure for table `student_course` */

DROP TABLE IF EXISTS `student_course`;

CREATE TABLE `student_course` (
  `studentid` char(11) NOT NULL DEFAULT '',
  `courseid` char(11) NOT NULL DEFAULT '',
  PRIMARY KEY (`studentid`,`courseid`),
  KEY `FK_studentcourse_courseid` (`courseid`),
  CONSTRAINT `FK_studentcourse_course` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`),
  CONSTRAINT `FK_studentcourse_student` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student_course` */

insert  into `student_course`(`studentid`,`courseid`) values ('081101','101');

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `teacherid` char(11) NOT NULL,
  `teachername` varchar(50) NOT NULL DEFAULT '',
  `sex` int(2) DEFAULT '0',
  `department` varchar(50) DEFAULT '',
  `imagepath` varchar(50) DEFAULT '',
  PRIMARY KEY (`teacherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`teacherid`,`teachername`,`sex`,`department`,`imagepath`) values ('1001','黄目升',0,'计算机',''),('1002','赵红',1,'通信工程','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
