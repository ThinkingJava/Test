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
  `studentid` char(11) NOT NULL DEFAULT '' COMMENT '学生id',
  `courseid` char(11) NOT NULL DEFAULT '' COMMENT '班级id',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `imagepath` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '学生图片',
  `datatime` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`attendid`),
  KEY `FK_attend_stuedntid` (`studentid`),
  KEY `FK_attend_courseid` (`courseid`),
  CONSTRAINT `FK_attend_course` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`),
  CONSTRAINT `FK_attend_student` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `attend` */

insert  into `attend`(`attendid`,`studentid`,`courseid`,`status`,`imagepath`,`datatime`) values (15,'081101','101',0,'/upload/student//student_081101/201611141018200824.jpg','2016-11-14 10:18:22'),(16,'081101','101',0,NULL,'2016-11-14 10:29:15'),(17,'081108','101',0,NULL,'2016-11-14 10:33:03'),(18,'081106','101',0,'/upload/student//student_081106/201611141038570007.jpg','2016-11-14 10:38:57'),(19,'081106','101',1,NULL,'2016-11-14 10:43:02'),(20,'081101','104',0,NULL,'2016-11-14 18:50:50'),(21,'081101','102',1,NULL,'2016-11-14 19:00:09'),(22,'081101','101',1,NULL,'2016-11-15 14:45:25'),(23,'081106','101',1,NULL,'2016-11-23 21:15:12'),(24,'081101','210',0,NULL,'2016-11-23 21:17:57'),(26,'081101','210',0,'/upload/student//student_081101/201611232121150482.jpg','2016-11-23 21:21:15'),(27,'081101','101',0,'/upload/student//student_081101/201611261719040876.jpg','2016-11-26 17:19:04'),(28,'081101','101',0,NULL,'2016-11-27 19:17:10'),(30,'081101','101',1,'/upload/student//student_081101/201611272326360166.jpg','2016-11-27 23:26:36'),(31,'081101','212',1,NULL,'2016-12-02 14:50:34'),(32,'081104','101',1,NULL,'2016-12-02 21:53:06'),(33,'081230','105',0,NULL,'2016-12-02 22:53:44');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `courseid` char(11) NOT NULL DEFAULT '' COMMENT '课程号',
  `coursename` varchar(50) DEFAULT NULL COMMENT '课程名',
  `semester` tinyint(3) DEFAULT NULL COMMENT '学期',
  `hours` int(11) DEFAULT '0' COMMENT '学时',
  `number` int(4) DEFAULT '0' COMMENT '人数',
  `credit` int(6) DEFAULT '0' COMMENT '学分',
  `teacherid` char(11) DEFAULT '1' COMMENT '教师',
  PRIMARY KEY (`courseid`),
  KEY `FK_course_teacherid` (`teacherid`),
  CONSTRAINT `FK_course_teacher` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`courseid`,`coursename`,`semester`,`hours`,`number`,`credit`,`teacherid`) values ('101','计算机基础',1,80,0,5,'1001'),('102','程序设计语言',2,68,45,4,'1001'),('103','JAVA程序设计',4,82,50,4,'1002'),('104','软件工程导论',4,82,50,3,'1001'),('105','AJAX',4,80,50,3,'1001'),('206','离散数学',4,68,60,4,'1002'),('208','数据结构',5,68,53,4,'1001'),('209','操作系统',6,68,55,4,'1002'),('210','计算机原理',5,85,23,5,'1001'),('212','数据库原理',7,68,33,4,'1001'),('301','计算机网络',7,51,52,3,'1002'),('302','软件工程',7,51,56,3,'1002');

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

insert  into `login`(`teacherid`,`name`,`password`) values ('1001','081101','123'),('1002','081102','123');

/*Table structure for table `major` */

DROP TABLE IF EXISTS `major`;

CREATE TABLE `major` (
  `majorid` int(11) NOT NULL AUTO_INCREMENT COMMENT '专业id',
  `majorname` varchar(50) NOT NULL DEFAULT '' COMMENT '专业名',
  `number` int(11) DEFAULT '0' COMMENT '专业人数',
  `teacherid` char(11) DEFAULT '0' COMMENT '主任',
  PRIMARY KEY (`majorid`),
  KEY `FK_major_teacherid` (`teacherid`),
  CONSTRAINT `FK_major_teacher` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `major` */

insert  into `major`(`majorid`,`majorname`,`number`,`teacherid`) values (1,'软件系',150,'1001'),(2,'通信工程',131,'1002');

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `studentid` char(11) NOT NULL DEFAULT '' COMMENT '学生号',
  `courseid` char(11) NOT NULL DEFAULT '' COMMENT '课程号',
  `score` float(6,1) DEFAULT '0.0' COMMENT '分数',
  `credit` int(6) DEFAULT '0' COMMENT '积分',
  PRIMARY KEY (`studentid`,`courseid`),
  KEY `FK_score_courseid` (`courseid`),
  CONSTRAINT `FK_score_course` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`),
  CONSTRAINT `FK_score_student` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `score` */

insert  into `score`(`studentid`,`courseid`,`score`,`credit`) values ('081102','101',60.0,5),('081102','102',58.0,0),('081103','101',62.0,5),('081103','102',70.0,4),('081103','206',81.0,4),('081106','206',86.0,4),('081201','206',60.0,4);

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
  `imagepath` varchar(128) DEFAULT '',
  PRIMARY KEY (`studentid`),
  KEY `majorid` (`majorid`),
  CONSTRAINT `FK_student_major` FOREIGN KEY (`majorid`) REFERENCES `major` (`majorid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`studentid`,`studentname`,`sex`,`datatime`,`majorid`,`score`,`remark`,`imagepath`) values ('081101','王林',1,'2016-11-07 20:10:17',1,51,'移动创建','/upload/studentbase/student_081101/201612021435130191.png'),('081102','程明',1,'1991-02-01 00:00:00',1,50,'2222','/upload/studentbase/student_081102/201611272135070390.png'),('081103','王燕',0,'1989-10-06 00:00:00',2,50,'好好','/upload/studentbase/student_081103/201611031135170809.png'),('081104','韦严平',1,'1990-08-26 00:00:00',1,50,'2222',''),('081105','谢霆锋',0,'1981-12-09 00:00:00',2,61,'好学生','/upload/studentbase/student_081105/201611261717300249.png'),('081106','李白',1,'1994-01-10 19:51:11',2,50,'','/upload/studentbase/student_李白/201610102239540828.png'),('081107','陈责任',0,'2016-10-17 21:28:46',1,50,NULL,''),('081108','杨城欢',0,'2016-10-17 21:29:28',1,51,NULL,''),('081201','杨过',0,'1994-01-11 20:36:02',2,52,'',''),('081222','22222',1,'2016-11-01 18:38:03',1,NULL,'移动创建','/upload/studentbase/student_081222/201611011837460013.png'),('081223','杨城',1,'2016-11-04 11:25:33',1,50,'移动创建','/upload/studentbase/student_081223/201611041125330544.png'),('081224','陈总',1,'2016-11-04 17:16:48',1,50,'移动创建','/upload/studentbase/student_081224/201611041716480457.png'),('081225','杨洋',1,'2016-11-04 17:27:39',1,50,'移动创建','/upload/studentbase/student_081225/201611041727390010.png'),('081226','谢娜',1,'2016-11-04 23:10:50',1,50,'移动创建','/upload/studentbase/student_081226/201611042310490951.png'),('081228','莫言',1,'2016-11-06 22:43:59',1,50,'移动创建','/upload/studentbase/student_081228/201611062243590118.png'),('081230','陈广纳',1,'2016-11-07 20:18:45',1,50,'移动创建','/upload/studentbase/student_081230/201611072018450366.png'),('081231','赞同',1,'2016-11-07 22:12:23',1,50,'移动创建','/upload/studentbase/student_081231/201611072212220760.png'),('081232','杨泽',0,'2016-11-08 11:27:30',1,50,'移动创建','/upload/studentbase/student_081232/201611081127300179.png');

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

insert  into `student_course`(`studentid`,`courseid`) values ('081101','101'),('081102','101'),('081104','101'),('081104','104'),('081105','104'),('081101','105'),('081230','105'),('081231','209'),('081101','210'),('081101','212');

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

insert  into `teacher`(`teacherid`,`teachername`,`sex`,`department`,`imagepath`) values ('1001','黄升',1,'计算机','/upload/teacher/201611261716590471.png'),('1002','赵红',1,'通信工程','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
