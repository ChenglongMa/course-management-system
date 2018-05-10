/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : sef

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-05-10 11:12:44
*/
-- -----------------------------------------------------
-- Schema sef
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sef` ;
USE `sef` ;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `user_id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for coordinator
-- ----------------------------
DROP TABLE IF EXISTS `coordinator`;
CREATE TABLE `coordinator` (
  `user_id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `code` varchar(255) NOT NULL,
  `main_topic` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for courseoffering
-- ----------------------------
DROP TABLE IF EXISTS `courseoffering`;
CREATE TABLE `courseoffering` (
  `offering_id` int(11) NOT NULL AUTO_INCREMENT,
  `capacity` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `sch_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`offering_id`),
  KEY `FKd700n5sghcbcbys423re3b6k7` (`code`),
  KEY `FKfrc32xf4y6xvvg88e76coy269` (`user_id`),
  KEY `FKe1ce3q02f4udi229d9i8ph5kg` (`sch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course_prerequisites
-- ----------------------------
DROP TABLE IF EXISTS `course_prerequisites`;
CREATE TABLE `course_prerequisites` (
  `course_code` varchar(255) NOT NULL,
  `prerequisites_code` varchar(255) NOT NULL,
  KEY `FK1co8h3wleinvdtq51x3toq67a` (`prerequisites_code`),
  KEY `FK1nyrwerwqy7wrlhc1t6xtyrcw` (`course_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for enrolment
-- ----------------------------
DROP TABLE IF EXISTS `enrolment`;
CREATE TABLE `enrolment` (
  `enrol_id` int(11) NOT NULL AUTO_INCREMENT,
  `result` varchar(255) DEFAULT NULL,
  `offering_id` int(11) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`enrol_id`),
  KEY `FKkxfmcfsh5d9vf63er0epg2wh9` (`offering_id`),
  KEY `FKt00h72ar6f5gonac02apjgqwi` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lecturer
-- ----------------------------
DROP TABLE IF EXISTS `lecturer`;
CREATE TABLE `lecturer` (
  `user_id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `sch_id` int(11) NOT NULL AUTO_INCREMENT,
  `semester` int(11) DEFAULT NULL,
  `week` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`sch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `user_id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `max_electives` int(11) DEFAULT NULL,
  `max_load` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
