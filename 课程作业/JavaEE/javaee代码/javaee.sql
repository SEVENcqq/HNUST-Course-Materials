/*
 Navicat Premium Data Transfer

 Source Server         : MySql8.0
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:13306
 Source Schema         : javaee

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 06/01/2024 15:47:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_course
-- ----------------------------
DROP TABLE IF EXISTS `c_course`;
CREATE TABLE `c_course`  (
  `cid` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `hours` int NULL DEFAULT NULL,
  `schools` int NULL DEFAULT NULL,
  `cpath` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `cinf` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_course
-- ----------------------------
INSERT INTO `c_course` VALUES (1, 'C语言程序设计', 64, 1, '5866e464-2a24-48f3-b315-40fcd25e23d7.png', 'C语言是一门面向过程的、抽象化的通用程序设计语言，广泛应用于底层开发。C语言能以简易的方式编译、处理低级存储器。');
INSERT INTO `c_course` VALUES (2, 'Python程序设计', 70, 1, '310da3a0-de32-4ae5-a81c-4ebe7d012f75.png', 'Python提供了高效的高级数据结构，还能简单有效地面向对象编程。Python语法和动态类型，以及解释型语言的本质，使它成为多数平台上写脚本和快速开发应用的编程语言。');
INSERT INTO `c_course` VALUES (3, '大学英语', 96, 2, NULL, '大学英语是以英语语言知识与应用技能、学习策略和跨文化交际为主要内容，以外语教学理论为指导，以现代教育技术和信息技术为支撑，集多种教学模式和教学手段为一体，实施开放式、交互型、立体化的教学体系。');
INSERT INTO `c_course` VALUES (4, '高级Web技术', 40, 1, 'fce35b6a-914f-4fb6-9edd-2989892f0115.png', 'Web客户端的主要任务是展现信息内容。Web客户端设计技术主要包括：HTML语言、Java Applets、脚本程序、CSS、DHTML、插件技术以及VRML技术。');
INSERT INTO `c_course` VALUES (5, '大数据存储', 32, 1, '2320caf8-74d2-41c1-827f-5305303e5dab.png', '“大数据” 通常指的是那些数量巨大、难于收集、处理、分析的数据集，亦指那些在传统基础设施中长期保存的数据。大数据存储是将这些数据集持久化到计算机中。');
INSERT INTO `c_course` VALUES (6, '高等代数', 64, 3, NULL, '《高等代数》以其追求内容结构的清晰刻画和作为数学应用的基础，是大学数学各个专业的主干基础课程。');
INSERT INTO `c_course` VALUES (7, '数学分析', 48, 3, NULL, '又称高级微积分，分析学中最古老、最基本的分支。一般指以微积分学和无穷级数一般理论为主要内容，并包括它们的理论基础（实数、函数和极限的基本理论的一个较为完整的数学学科。');
INSERT INTO `c_course` VALUES (8, '概率论', 48, 3, NULL, '概率论，是研究随机现象数量规律的数学分支。随机现象是相对于决定性现象而言的，在一定条件下必然发生某一结果的现象称为决定性现象。');
INSERT INTO `c_course` VALUES (9, '数理逻辑', 64, 3, '', NULL);
INSERT INTO `c_course` VALUES (10, '高级英语', 40, 2, '', NULL);
INSERT INTO `c_course` VALUES (11, '分析化学', 48, 4, NULL, NULL);
INSERT INTO `c_course` VALUES (12, '有机化学', 64, 4, '', NULL);
INSERT INTO `c_course` VALUES (13, '量子力学', 72, 5, NULL, NULL);
INSERT INTO `c_course` VALUES (14, '理论力学', 64, 5, NULL, NULL);
INSERT INTO `c_course` VALUES (15, '数据结构', 80, 1, 'da204d06-cbb0-416d-89c0-4582ba106535.png', '数据结构是计算机科学与技术、网络工程、软件工程、信息安全等专业的重要基础课，是这些专业的核心课程之一，是一门集技术性、理论性和实践性于一体的课程。');
INSERT INTO `c_course` VALUES (16, '操作系统', 64, 1, 'bf0889a2-6a17-4c4b-8803-58c1c1062aec.png', '');
INSERT INTO `c_course` VALUES (17, '计算机网络', 72, 1, '19061bd1-22ae-44ca-8a8a-1ff674089779.png', '');
INSERT INTO `c_course` VALUES (18, '算法分析', 48, 1, '436fe1e1-ee79-4154-be2d-7d7a6c995082.png', '课程的内容分成两大部分：算法的基础知识和通用算法设计技术与分析方法。课程主要内容涉及：面对实际问题建立数学模型、设计正确的求解算法、算法的效率估计等。');
INSERT INTO `c_course` VALUES (19, '软件工程', 36, 1, NULL, NULL);
INSERT INTO `c_course` VALUES (20, '数据库', 48, 1, NULL, NULL);
INSERT INTO `c_course` VALUES (22, '密码学', 48, 1, 'd6e88938-c600-47f2-876e-e5baa97929e4.png', '');
INSERT INTO `c_course` VALUES (28, '大学生职业素养', 36, 6, NULL, NULL);
INSERT INTO `c_course` VALUES (29, '数据通信', 64, 6, NULL, NULL);
INSERT INTO `c_course` VALUES (37, '人工智能导论', 36, 1, '123fc02a-62c9-4341-8a40-cc42e399d10e.png', '介绍了人工智能的孕育、人工智能的诞生、人工智能的复苏、人工智能的高速发展、人工智能的应用分支和哲学与思考等方面');
INSERT INTO `c_course` VALUES (38, '数字逻辑电路', 48, 1, '098675e6-d6b2-4e1e-aa4a-a6ca2f268ddc.png', '主要内容包括：数字和逻辑基础、门电路、组合逻辑电路、触发器、时序逻辑电路、脉冲波形的产生与整形、数模转换和模数转换电路、半导体存储器、可编程逻辑器件简介、数字逻辑电路简单应用与知识扩展。');

-- ----------------------------
-- Table structure for cinfo
-- ----------------------------
DROP TABLE IF EXISTS `cinfo`;
CREATE TABLE `cinfo`  (
  `cid` int NOT NULL AUTO_INCREMENT,
  `cpath` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `cinf` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cinfo
-- ----------------------------
INSERT INTO `cinfo` VALUES (1, '6115b5a5-2ed8-42a1-aef8-f89f82f5efe9.png', '123456');
INSERT INTO `cinfo` VALUES (2, '1e78f41e-b6ad-4bde-b1bd-13854f00e658.jpg', '132');
INSERT INTO `cinfo` VALUES (3, NULL, NULL);
INSERT INTO `cinfo` VALUES (4, '8e79a6b6-a213-43f8-94ad-4122c0176f78.jpg', '123456789');
INSERT INTO `cinfo` VALUES (5, '', '');
INSERT INTO `cinfo` VALUES (6, NULL, NULL);
INSERT INTO `cinfo` VALUES (7, NULL, NULL);
INSERT INTO `cinfo` VALUES (8, NULL, NULL);
INSERT INTO `cinfo` VALUES (9, NULL, NULL);
INSERT INTO `cinfo` VALUES (10, NULL, NULL);
INSERT INTO `cinfo` VALUES (11, NULL, NULL);
INSERT INTO `cinfo` VALUES (12, NULL, NULL);
INSERT INTO `cinfo` VALUES (13, NULL, NULL);
INSERT INTO `cinfo` VALUES (14, NULL, NULL);
INSERT INTO `cinfo` VALUES (15, '', '');
INSERT INTO `cinfo` VALUES (16, '', '');
INSERT INTO `cinfo` VALUES (17, 'e919cd52-bcff-48f2-83e6-406fe82c9d3e.jpg', '');
INSERT INTO `cinfo` VALUES (18, '', '');
INSERT INTO `cinfo` VALUES (19, '', '');
INSERT INTO `cinfo` VALUES (20, NULL, NULL);
INSERT INTO `cinfo` VALUES (21, NULL, NULL);
INSERT INTO `cinfo` VALUES (22, NULL, NULL);
INSERT INTO `cinfo` VALUES (23, NULL, NULL);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `hours` int NULL DEFAULT NULL,
  `school` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `cpath` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `cinf` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`cname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('C语言程序设计', 70, '计算机科学与工程学院', NULL, NULL);
INSERT INTO `course` VALUES ('Python程序设计', 70, '计算机科学与工程学院', NULL, NULL);
INSERT INTO `course` VALUES ('土木概论', 32, '土木工程学院', NULL, NULL);
INSERT INTO `course` VALUES ('有机化学', 32, '化学化工学院', NULL, NULL);
INSERT INTO `course` VALUES ('高级Web技术', 32, '计算机科学与工程学院', NULL, NULL);

-- ----------------------------
-- Table structure for s_school
-- ----------------------------
DROP TABLE IF EXISTS `s_school`;
CREATE TABLE `s_school`  (
  `sid` int NOT NULL AUTO_INCREMENT,
  `school_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_school
-- ----------------------------
INSERT INTO `s_school` VALUES (1, '计算机科学与工程学院');
INSERT INTO `s_school` VALUES (2, '外国语学院');
INSERT INTO `s_school` VALUES (3, '数学与计算科学学院');
INSERT INTO `s_school` VALUES (4, '化学与化工学院');
INSERT INTO `s_school` VALUES (5, '物理学院');
INSERT INTO `s_school` VALUES (6, '信息与电气工程学院');
INSERT INTO `s_school` VALUES (7, '机电工程学院');
INSERT INTO `s_school` VALUES (8, '土木工程学院');
INSERT INTO `s_school` VALUES (9, '能源与安全工程学院');
INSERT INTO `s_school` VALUES (10, '生命科学学院');
INSERT INTO `s_school` VALUES (11, '建筑与城乡规划学院');
INSERT INTO `s_school` VALUES (12, '人文学院');
INSERT INTO `s_school` VALUES (13, '马克思主义学院');
INSERT INTO `s_school` VALUES (14, '教育学院');
INSERT INTO `s_school` VALUES (15, '商学院');
INSERT INTO `s_school` VALUES (16, '艺术学院');
INSERT INTO `s_school` VALUES (17, '体育学院');
INSERT INTO `s_school` VALUES (18, '管理学院');
INSERT INTO `s_school` VALUES (19, '法学院');

-- ----------------------------
-- Table structure for t_uinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_uinfo`;
CREATE TABLE `t_uinfo`  (
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `place` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `upath` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_uinfo
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('12345678@qq.com', '123456', '12345678');
INSERT INTO `t_user` VALUES ('123456@qq.com', '123456', '123456');
INSERT INTO `t_user` VALUES ('12345@qq.com', '123456', '12345');
INSERT INTO `t_user` VALUES ('1234@qq.com', '123456', '1234');
INSERT INTO `t_user` VALUES ('1302466947@qq.com', '7777777', 'cqq');
INSERT INTO `t_user` VALUES ('lisi@qq.com', '123456', 'lisi');
INSERT INTO `t_user` VALUES ('mazi@qq.com', '123456', '123456');
INSERT INTO `t_user` VALUES ('wangwu@qq.com', '123456', 'wangwu');
INSERT INTO `t_user` VALUES ('zhangsan@qq.com', '123456', 'zhangsan');
INSERT INTO `t_user` VALUES ('zhouba@qq.com', '123456', 'zhouba');

SET FOREIGN_KEY_CHECKS = 1;
