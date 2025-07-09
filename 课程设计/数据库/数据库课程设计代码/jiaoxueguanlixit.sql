/*
 Navicat Premium Data Transfer

 Source Server         : MySql5.5
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : jiaoxueguanlixit

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 06/01/2024 15:30:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `courseid` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `coursename` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `beforecourse` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `teachername` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `xuefen` int(11) NULL DEFAULT NULL,
  `xueshi` int(11) NULL DEFAULT NULL,
  `didian` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `shijian` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `leixing` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`courseid`, `teachername`, `leixing`) USING BTREE,
  INDEX `fk_t`(`teachername`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('711', '高等代数', '712', '陈二', 4, 64, '八教301', '周一1,2节', '3');
INSERT INTO `course` VALUES ('712', '数学分析', '713', '陈二', 3, 48, '八教501', '周三3,4节', '3');
INSERT INTO `course` VALUES ('713', '概率论', '714', '赵六', 3, 48, '八教402', '周二3,4节', '3');
INSERT INTO `course` VALUES ('714', '数理逻辑', '715', '陈二', 5, 80, '八教303', '周五3,4节', '3');
INSERT INTO `course` VALUES ('721', '高级英语', '720', '李四', 3, 48, '一教301', '周三5,6节', '3');
INSERT INTO `course` VALUES ('731', '分析化学', '733', '王五', 4, 64, '一教501', '周四3,4节', '3');
INSERT INTO `course` VALUES ('733', '有机化学', '735', '吴九', 3, 48, '一教103', '周五1,2节', '3');
INSERT INTO `course` VALUES ('741', '量子力学', '742', '周八', 5, 80, '四教107', '周一1,2节', '3');
INSERT INTO `course` VALUES ('742', '理论力学', '740', '周八', 4, 64, '四教401', '周四7,8节', '3');
INSERT INTO `course` VALUES ('751', '数据结构', '750', '刘一', 5, 80, '一教201', '周一7,8节', '3');
INSERT INTO `course` VALUES ('752', '操作系统', '751', '张三', 3, 48, '一教402', '周四7,8节', '3');
INSERT INTO `course` VALUES ('753', '计算机网络', '752', '刘一', 4, 64, '一教303', '周二1,2节', '3');
INSERT INTO `course` VALUES ('754', '算法分析', '751', '刘一', 3, 48, '一教204', '周一3,4节', '3');
INSERT INTO `course` VALUES ('755', '软件工程', '751', '张三', 3, 48, '一教501', '周四1,2节', '3');
INSERT INTO `course` VALUES ('757', '密码学', '752', '孙七', 4, 64, '一教205', '周三5,6节', '3');
INSERT INTO `course` VALUES ('777', '数据库', '', '张三', 3, 48, '七教101', '周五1,2节', '5');

-- ----------------------------
-- Table structure for coursestate
-- ----------------------------
DROP TABLE IF EXISTS `coursestate`;
CREATE TABLE `coursestate`  (
  `code` int(11) NOT NULL,
  `describestate` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of coursestate
-- ----------------------------
INSERT INTO `coursestate` VALUES (0, '申请增加新课程');
INSERT INTO `coursestate` VALUES (1, '申请删除旧课程');
INSERT INTO `coursestate` VALUES (2, '申请修改课程');
INSERT INTO `coursestate` VALUES (3, '可选');
INSERT INTO `coursestate` VALUES (4, '等待课程安排');
INSERT INTO `coursestate` VALUES (5, '已删除');
INSERT INTO `coursestate` VALUES (6, '拒绝申请');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `studentid` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL DEFAULT '',
  `studentname` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `courseid` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL DEFAULT '',
  `coursename` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `score` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`studentid`, `courseid`) USING BTREE,
  INDEX `fk_score2`(`courseid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('21001', '王杰', '711', '高等代数', 89);
INSERT INTO `score` VALUES ('21001', '王杰', '712', '数学分析', 92);
INSERT INTO `score` VALUES ('21002', '李俊', '721', '高级英语', 90);
INSERT INTO `score` VALUES ('21003', '杜康', '731', '分析化学', 78);
INSERT INTO `score` VALUES ('21003', '杜康', '733', '有机化学', 82);
INSERT INTO `score` VALUES ('21004', '王月', '711', '高等代数', 95);
INSERT INTO `score` VALUES ('21004', '王月', '713', '概率论', 100);
INSERT INTO `score` VALUES ('21004', '王月', '714', '数理逻辑', 96);
INSERT INTO `score` VALUES ('21005', '李文', '751', '数据结构', 85);
INSERT INTO `score` VALUES ('21005', '李文', '752', '操作系统', 89);
INSERT INTO `score` VALUES ('21005', '李文', '753', '计算机网络', 96);
INSERT INTO `score` VALUES ('21006', '刘娟', '751', '数据结构', 98);
INSERT INTO `score` VALUES ('21006', '刘娟', '753', '计算机网络', 92);
INSERT INTO `score` VALUES ('21006', '刘娟', '757', '密码学', 94);
INSERT INTO `score` VALUES ('21007', '李华', '741', '量子力学', 60);
INSERT INTO `score` VALUES ('21007', '李华', '742', '理论力学', 58);
INSERT INTO `score` VALUES ('21008', '张伟', '751', '数据结构', 74);
INSERT INTO `score` VALUES ('21008', '张伟', '752', '操作系统', 86);
INSERT INTO `score` VALUES ('21009', '张军', '751', '数据结构', 85);
INSERT INTO `score` VALUES ('21009', '张军', '755', '软件工程', 82);
INSERT INTO `score` VALUES ('21010', '吴涛', '711', '高等代数', 89);
INSERT INTO `score` VALUES ('21010', '吴涛', '712', '数学分析', 92);
INSERT INTO `score` VALUES ('21010', '吴涛', '713', '概率论', 90);
INSERT INTO `score` VALUES ('21011', '周婷', '751', '数据结构', 83);
INSERT INTO `score` VALUES ('21012', '刘雪', '751', '数据结构', 96);
INSERT INTO `score` VALUES ('21012', '刘雪', '754', '算法分析', 92);
INSERT INTO `score` VALUES ('21013', '陈丽', '741', '量子力学', 78);
INSERT INTO `score` VALUES ('21013', '陈丽', '742', '理论力学', 94);
INSERT INTO `score` VALUES ('21014', '杨柳', '721', '高级英语', 95);

-- ----------------------------
-- Table structure for selectcourse
-- ----------------------------
DROP TABLE IF EXISTS `selectcourse`;
CREATE TABLE `selectcourse`  (
  `studentid` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL DEFAULT '',
  `studentname` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `courseid` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL DEFAULT '',
  `coursename` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  PRIMARY KEY (`studentid`, `courseid`) USING BTREE,
  INDEX `fk_sc1`(`courseid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of selectcourse
-- ----------------------------
INSERT INTO `selectcourse` VALUES ('21001', '王杰', '711', '高等代数');
INSERT INTO `selectcourse` VALUES ('21001', '王杰', '712', '数学分析');
INSERT INTO `selectcourse` VALUES ('21001', '王杰', '751', '数据结构');
INSERT INTO `selectcourse` VALUES ('21002', '李俊', '721', '高级英语');
INSERT INTO `selectcourse` VALUES ('21003', '杜康', '731', '分析化学');
INSERT INTO `selectcourse` VALUES ('21003', '杜康', '733', '有机化学');
INSERT INTO `selectcourse` VALUES ('21004', '王月', '711', '高等代数');
INSERT INTO `selectcourse` VALUES ('21004', '王月', '713', '概率论');
INSERT INTO `selectcourse` VALUES ('21004', '王月', '714', '数理逻辑');
INSERT INTO `selectcourse` VALUES ('21005', '李文', '751', '数据结构');
INSERT INTO `selectcourse` VALUES ('21005', '李文', '752', '操作系统');
INSERT INTO `selectcourse` VALUES ('21005', '李文', '753', '计算机网络');
INSERT INTO `selectcourse` VALUES ('21006', '刘娟', '751', '数据结构');
INSERT INTO `selectcourse` VALUES ('21006', '刘娟', '753', '计算机网络');
INSERT INTO `selectcourse` VALUES ('21006', '刘娟', '757', '密码学');
INSERT INTO `selectcourse` VALUES ('21007', '李华', '741', '量子力学');
INSERT INTO `selectcourse` VALUES ('21007', '李华', '742', '理论力学');
INSERT INTO `selectcourse` VALUES ('21008', '张伟', '751', '数据结构');
INSERT INTO `selectcourse` VALUES ('21008', '张伟', '752', '操作系统');
INSERT INTO `selectcourse` VALUES ('21009', '张军', '751', '数据结构');
INSERT INTO `selectcourse` VALUES ('21009', '张军', '755', '软件工程');
INSERT INTO `selectcourse` VALUES ('21010', '吴涛', '711', '高等代数');
INSERT INTO `selectcourse` VALUES ('21010', '吴涛', '712', '数学分析');
INSERT INTO `selectcourse` VALUES ('21010', '吴涛', '713', '概率论');
INSERT INTO `selectcourse` VALUES ('21011', '周婷', '751', '数据结构');
INSERT INTO `selectcourse` VALUES ('21012', '刘雪', '751', '数据结构');
INSERT INTO `selectcourse` VALUES ('21012', '刘雪', '754', '算法分析');
INSERT INTO `selectcourse` VALUES ('21013', '陈丽', '741', '量子力学');
INSERT INTO `selectcourse` VALUES ('21013', '陈丽', '742', '理论力学');
INSERT INTO `selectcourse` VALUES ('21014', '杨柳', '721', '高级英语');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `studentid` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `studentname` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `class` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`studentid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('21001', '王杰', '数学1班');
INSERT INTO `student` VALUES ('21002', '李俊', '英语4班');
INSERT INTO `student` VALUES ('21003', '杜康', '化学2班');
INSERT INTO `student` VALUES ('21004', '王月', '数学2班');
INSERT INTO `student` VALUES ('21005', '李文', '计科7班');
INSERT INTO `student` VALUES ('21006', '刘娟', '信安2班');
INSERT INTO `student` VALUES ('21007', '李华', '物理3班');
INSERT INTO `student` VALUES ('21008', '张伟', '计科1班');
INSERT INTO `student` VALUES ('21009', '张军', '软件1班');
INSERT INTO `student` VALUES ('21010', '吴涛', '数学2班');
INSERT INTO `student` VALUES ('21011', '周婷', '计科6班');
INSERT INTO `student` VALUES ('21012', '刘雪', '大数据2班');
INSERT INTO `student` VALUES ('21013', '陈丽', '物理1班');
INSERT INTO `student` VALUES ('21014', '杨柳', '英语3班');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacherid` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL DEFAULT '',
  `teachername` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `teacherdepartment` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`teacherid`) USING BTREE,
  INDEX `teachername`(`teachername`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('10001', '刘一', '计算机系');
INSERT INTO `teacher` VALUES ('10002', '陈二', '数学系');
INSERT INTO `teacher` VALUES ('10003', '张三', '计算机系');
INSERT INTO `teacher` VALUES ('10004', '李四', '英语系');
INSERT INTO `teacher` VALUES ('10005', '王五', '化学系');
INSERT INTO `teacher` VALUES ('10006', '赵六', '数学系');
INSERT INTO `teacher` VALUES ('10007', '孙七', '计算机系');
INSERT INTO `teacher` VALUES ('10008', '周八', '物理系');
INSERT INTO `teacher` VALUES ('10009', '吴九', '化学系');

-- ----------------------------
-- Table structure for userpass
-- ----------------------------
DROP TABLE IF EXISTS `userpass`;
CREATE TABLE `userpass`  (
  `useraccount` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `userpass` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`useraccount`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userpass
-- ----------------------------
INSERT INTO `userpass` VALUES ('10001', '10001', 2);
INSERT INTO `userpass` VALUES ('10002', '10002', 2);
INSERT INTO `userpass` VALUES ('10003', '10003', 2);
INSERT INTO `userpass` VALUES ('10004', '10004', 2);
INSERT INTO `userpass` VALUES ('10005', '10005', 2);
INSERT INTO `userpass` VALUES ('10006', '10006', 2);
INSERT INTO `userpass` VALUES ('10007', '10007', 2);
INSERT INTO `userpass` VALUES ('10008', '10008', 2);
INSERT INTO `userpass` VALUES ('10009', '10009', 2);
INSERT INTO `userpass` VALUES ('21001', '21001', 1);
INSERT INTO `userpass` VALUES ('21002', '21002', 1);
INSERT INTO `userpass` VALUES ('21003', '21003', 1);
INSERT INTO `userpass` VALUES ('21004', '21004', 1);
INSERT INTO `userpass` VALUES ('21005', '21005', 1);
INSERT INTO `userpass` VALUES ('21006', '21006', 1);
INSERT INTO `userpass` VALUES ('21007', '21007', 1);
INSERT INTO `userpass` VALUES ('21008', '21008', 1);
INSERT INTO `userpass` VALUES ('21009', '21009', 1);
INSERT INTO `userpass` VALUES ('21010', '21010', 1);
INSERT INTO `userpass` VALUES ('21011', '21011', 1);
INSERT INTO `userpass` VALUES ('21012', '21012', 1);
INSERT INTO `userpass` VALUES ('21013', '21013', 1);
INSERT INTO `userpass` VALUES ('21014', '21014', 1);
INSERT INTO `userpass` VALUES ('7', '7777777', 3);

SET FOREIGN_KEY_CHECKS = 1;
