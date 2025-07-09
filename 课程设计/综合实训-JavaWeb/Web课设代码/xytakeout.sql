/*
 Navicat Premium Data Transfer

 Source Server         : MySql8.0
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:13306
 Source Schema         : xytakeout

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 06/01/2024 14:50:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address_book
-- ----------------------------
DROP TABLE IF EXISTS `address_book`;
CREATE TABLE `address_book`  (
  `id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `consignee` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '收货人',
  `sex` tinyint NOT NULL COMMENT '性别 0 女 1 男',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `province_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省级区划编号',
  `province_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省级名称',
  `city_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市级区划编号',
  `city_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市级名称',
  `district_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区级区划编号',
  `district_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区级名称',
  `detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT '默认 0 否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '地址管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address_book
-- ----------------------------
INSERT INTO `address_book` VALUES (1662704856772657153, 1662703442851794946, 'wangwu', 1, '13297325886', NULL, NULL, NULL, NULL, NULL, NULL, 'hnust', '学校', 1, '2023-05-28 14:18:24', '2023-05-28 14:18:28', 1662703442851794946, 1662703442851794946, 0);
INSERT INTO `address_book` VALUES (1662705062163529729, 1662703442851794946, 'zhangsan', 1, '13258869732', NULL, NULL, NULL, NULL, NULL, NULL, '湖南长沙雨花区', '家', 0, '2023-05-28 14:19:13', '2023-05-28 14:19:13', 1662703442851794946, 1662703442851794946, 0);
INSERT INTO `address_book` VALUES (1662706004665581570, 1661662755305832450, 'cqq', 0, '13297325886', NULL, NULL, NULL, NULL, NULL, NULL, '湖南科技大学八区', '学校', 0, '2023-05-28 14:22:58', '2023-06-03 11:14:50', 1661662755305832450, 1661662755305832450, 0);
INSERT INTO `address_book` VALUES (1662706137000067073, 1661662755305832450, 'cqq', 0, '13297325886', NULL, NULL, NULL, NULL, NULL, NULL, '湖南长沙雨花区', '家', 1, '2023-05-28 14:23:29', '2023-06-03 11:14:52', 1661662755305832450, 1661662755305832450, 0);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint NOT NULL COMMENT '主键',
  `type` int NULL DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `sort` int NOT NULL DEFAULT 0 COMMENT '顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_category_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜品及套餐分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1662654163084996609, 1, '汉堡', 1, '2023-05-28 10:56:58', '2023-05-28 10:56:58', 1, 1, NULL);
INSERT INTO `category` VALUES (1662654238490193922, 1, '小食', 2, '2023-05-28 10:57:16', '2023-05-28 10:57:16', 1, 1, NULL);
INSERT INTO `category` VALUES (1662654270777946113, 1, '饮品', 3, '2023-05-28 10:57:23', '2023-05-28 10:57:23', 1, 1, NULL);
INSERT INTO `category` VALUES (1662654359579750401, 2, '单人餐', 11, '2023-05-28 10:57:45', '2023-05-28 10:57:45', 1, 1, NULL);
INSERT INTO `category` VALUES (1662658432617943041, 1, '甜品', 4, '2023-05-28 11:13:56', '2023-05-28 11:13:56', 1, 1, NULL);
INSERT INTO `category` VALUES (1662697124178956289, 2, '双人餐', 12, '2023-05-28 13:47:40', '2023-05-28 13:47:40', 1, 1, NULL);
INSERT INTO `category` VALUES (1662697170144333826, 2, '多人餐', 13, '2023-05-28 13:47:51', '2023-05-28 13:47:51', 1, 1, NULL);

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish`  (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜品名称',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '菜品价格',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品码',
  `image` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '图片',
  `description` varchar(400) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述信息',
  `status` int NOT NULL DEFAULT 1 COMMENT '0 停售 1 起售',
  `sort` int NOT NULL DEFAULT 0 COMMENT '顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_dish_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜品管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES (1662655110213365761, '3层芝士牛堡', 1662654163084996609, 2500.00, '', '04a99fcc-1e15-4f72-97f1-28329ad7bf6c.png', '', 1, 0, '2023-05-28 11:00:44', '2023-05-28 13:29:51', 1, 1, 0);
INSERT INTO `dish` VALUES (1662655304355115009, '可乐(小)', 1662654270777946113, 600.00, '', '880285a6-ac4b-43e9-a422-27053f12bc3d.png', '', 1, 0, '2023-05-28 11:01:30', '2023-05-28 12:00:51', 1, 1, 0);
INSERT INTO `dish` VALUES (1662658014915596290, '霸王鸡盒', 1662654238490193922, 2900.00, '', 'e612a966-35d1-46e7-b2f0-b738be6b4c52.png', '', 1, 0, '2023-05-28 11:12:16', '2023-05-28 13:28:52', 1, 1, 0);
INSERT INTO `dish` VALUES (1662658147107475457, '霸王鸡条', 1662654238490193922, 900.00, '', '125ef291-9e5e-499f-ac25-0788e7127e16.png', '', 1, 0, '2023-05-28 11:12:48', '2023-05-28 13:28:31', 1, 1, 0);
INSERT INTO `dish` VALUES (1662658633806123010, '北海道风味华夫筒', 1662658432617943041, 300.00, '', 'e71aeeba-634b-4106-b8aa-72631c2febe8.jpg', '', 1, 0, '2023-05-28 11:14:44', '2023-05-28 11:14:44', 1, 1, 0);
INSERT INTO `dish` VALUES (1662658887116918785, '大嘴安格斯', 1662654163084996609, 2800.00, '', '14b3306b-4455-4f7b-a7e1-2a155f33b9d9.png', '', 1, 0, '2023-05-28 11:15:44', '2023-05-28 13:28:04', 1, 1, 0);
INSERT INTO `dish` VALUES (1662659057049145345, '皇堡', 1662654163084996609, 1900.00, '', '1cf7475d-8e60-420e-98a7-c81bf467f289.png', '', 1, 0, '2023-05-28 11:16:25', '2023-05-28 13:27:43', 1, 1, 0);
INSERT INTO `dish` VALUES (1662659267162804226, '双层脆鸡堡', 1662654163084996609, 2200.00, '', '1a8909b7-29fb-45ab-b616-282a21155dd7.png', '', 1, 0, '2023-05-28 11:17:15', '2023-05-28 11:17:15', 1, 1, 0);
INSERT INTO `dish` VALUES (1662659441935257601, '王道川蜀鸡翅', 1662654238490193922, 900.00, '', '1888e0b3-5fcd-4cc7-9af4-098a46b17e45.png', '', 1, 0, '2023-05-28 11:17:56', '2023-05-28 13:27:08', 1, 1, 0);
INSERT INTO `dish` VALUES (1662659687469813761, '果木风味鸡腿堡', 1662654163084996609, 1900.00, '', '891cf849-6e76-458f-ac17-cd2bebdf49e8.png', '', 1, 0, '2023-05-28 11:18:55', '2023-05-28 13:26:51', 1, 1, 0);
INSERT INTO `dish` VALUES (1662660253109456897, '薯条(小)', 1662654238490193922, 800.00, '', '9d4cb9f4-aaec-47a3-bc53-799a54da70c5.png', '', 1, 0, '2023-05-28 11:21:10', '2023-05-28 13:26:26', 1, 1, 0);
INSERT INTO `dish` VALUES (1662660338060890113, '薯条(中)', 1662654238490193922, 1000.00, '', '9de9361f-dd8b-4ac7-b415-3f83cec6e0f0.png', '', 1, 0, '2023-05-28 11:21:30', '2023-05-28 13:25:50', 1, 1, 0);
INSERT INTO `dish` VALUES (1662660410743984130, '薯条(大)', 1662654238490193922, 1200.00, '', '46b2d52f-88f5-4d78-a889-184a5bb600f2.png', '', 1, 0, '2023-05-28 11:21:47', '2023-05-28 13:25:33', 1, 1, 0);
INSERT INTO `dish` VALUES (1662660475105579009, '鸡块', 1662654238490193922, 900.00, '', 'd2c6472a-763c-45c3-9cae-572129576296.png', '', 1, 0, '2023-05-28 11:22:03', '2023-05-28 13:25:14', 1, 1, 0);
INSERT INTO `dish` VALUES (1662660656983183362, '王道椒香鸡腿', 1662654238490193922, 900.00, '', '1de0a2df-b62d-41d5-bf35-d64fae56eb3e.png', '', 1, 0, '2023-05-28 11:22:46', '2023-05-28 13:25:02', 1, 1, 0);
INSERT INTO `dish` VALUES (1662660985111973890, '狠霸王牛堡', 1662654163084996609, 2300.00, '', 'a86415f0-dbba-44b6-a17d-a107cc154f5e.png', '', 1, 0, '2023-05-28 11:24:04', '2023-05-28 13:24:41', 1, 1, 0);
INSERT INTO `dish` VALUES (1662662086641061889, '炫辣鸡腿堡', 1662654163084996609, 1600.00, '', '00e080ac-f78f-409e-b76f-6833e96ba351.jpg', '', 1, 0, '2023-05-28 11:28:27', '2023-05-28 13:23:59', 1, 1, 0);
INSERT INTO `dish` VALUES (1662662828793794562, '可乐(中)', 1662654270777946113, 800.00, '', 'a5a3d74b-7c0a-4a1a-865e-ccf7037bec20.png', '', 1, 0, '2023-05-28 11:31:24', '2023-05-28 12:00:22', 1, 1, 0);
INSERT INTO `dish` VALUES (1662662931944312833, '可乐(大)', 1662654270777946113, 1000.00, '', '073e6d74-55ad-403f-b540-5a0682c734b4.png', '', 1, 0, '2023-05-28 11:31:48', '2023-05-28 12:00:09', 1, 1, 0);
INSERT INTO `dish` VALUES (1662669468846788610, '雪碧(小)', 1662654270777946113, 600.00, '', '5dbc566c-f633-4157-b9af-31bfd1b6d573.png', '', 1, 0, '2023-05-28 11:57:47', '2023-05-28 11:59:59', 1, 1, 0);
INSERT INTO `dish` VALUES (1662669703471960066, '雪碧(中)', 1662654270777946113, 800.00, '', '789648ef-e9f2-4d4a-9149-ce776f010013.png', '', 1, 0, '2023-05-28 11:58:43', '2023-05-28 11:59:45', 1, 1, 0);
INSERT INTO `dish` VALUES (1662669865443397634, '雪碧(大)', 1662654270777946113, 1000.00, '', 'b8641444-8c16-4baf-ad41-c11810c9e653.png', '', 1, 0, '2023-05-28 11:59:21', '2023-05-28 11:59:21', 1, 1, 0);
INSERT INTO `dish` VALUES (1662670623886807041, '芬达(小)', 1662654270777946113, 600.00, '', 'a6701ff1-b8d0-4303-bd2d-49866b1b736c.png', '', 1, 0, '2023-05-28 12:02:22', '2023-05-28 12:02:22', 1, 1, 0);
INSERT INTO `dish` VALUES (1662670704325169153, '芬达(中)', 1662654270777946113, 800.00, '', '742eb8fc-85e9-4574-9126-4c2d052c6a05.png', '', 1, 0, '2023-05-28 12:02:42', '2023-05-28 12:02:42', 1, 1, 0);
INSERT INTO `dish` VALUES (1662670776039378945, '芬达(大)', 1662654270777946113, 1000.00, '', '7c2c6f48-ad3a-4d3a-ad15-544f7467df6f.png', '', 1, 0, '2023-05-28 12:02:59', '2023-05-28 12:02:59', 1, 1, 0);
INSERT INTO `dish` VALUES (1662689670561009666, 'KING暴风阿华田', 1662658432617943041, 600.00, '', '6e9b17ba-52eb-4b34-a5ce-bd81197cc0fa.jpg', '', 1, 0, '2023-05-28 13:18:03', '2023-05-28 13:18:03', 1, 1, 0);
INSERT INTO `dish` VALUES (1662689976418045954, '洋葱圈', 1662654238490193922, 600.00, '', '553efd7d-4968-4c15-b1bb-a620538038e7.jpg', '洋葱圈(5个)', 1, 0, '2023-05-28 13:19:16', '2023-05-28 13:19:16', 1, 1, 0);
INSERT INTO `dish` VALUES (1662690174754099201, '脆皮坚果新地', 1662658432617943041, 800.00, '', 'fee57578-cc56-4ba8-85e1-4685ac617c63.jpg', '', 1, 0, '2023-05-28 13:20:04', '2023-05-28 13:20:04', 1, 1, 0);
INSERT INTO `dish` VALUES (1662690375027920898, '脆皮浓浓牛奶棒', 1662658432617943041, 1200.00, '', 'e2267993-0769-44ed-9bd0-bbf2996741d9.jpg', '脆皮浓浓牛奶棒(2只)', 1, 0, '2023-05-28 13:20:51', '2023-05-28 13:20:51', 1, 1, 0);
INSERT INTO `dish` VALUES (1662690618247221250, '椰椰香柠风味气泡饮', 1662654270777946113, 800.00, '', '8ca78492-feaa-4725-b417-9db9247413b3.jpg', '', 1, 0, '2023-05-28 13:21:49', '2023-05-28 13:21:49', 1, 1, 0);
INSERT INTO `dish` VALUES (1662690741903691778, 'QQ糯米糍', 1662658432617943041, 1000.00, '', '8b6bde7a-1ddb-4667-90f9-b2794e58a90e.jpg', '', 1, 0, '2023-05-28 13:22:19', '2023-05-28 13:22:19', 1, 1, 0);
INSERT INTO `dish` VALUES (1662697006604226562, '麦辣鸡腿堡', 1662654163084996609, 1600.00, '', 'b87fe3b7-486e-4c41-9a72-0e325eb7c8de.png', '', 1, 0, '2023-05-28 13:47:12', '2023-05-28 13:47:12', 1, 1, 0);
INSERT INTO `dish` VALUES (1662700290727481346, '带劲香辣片片鸡', 1662654238490193922, 900.00, '', '6dfd64f4-0d7a-4f91-b0fc-8e50897915f2', '', 1, 0, '2023-05-28 14:00:15', '2023-05-28 14:00:15', 1, 1, 0);
INSERT INTO `dish` VALUES (1662700645196500994, '奇奇黑脆鸡堡', 1662654163084996609, 2100.00, '', '66e003b5-f10b-43a3-b724-e96f54c10307.jpg', '', 1, 0, '2023-05-28 14:01:40', '2023-05-28 14:01:40', 1, 1, 0);

-- ----------------------------
-- Table structure for dish_flavor
-- ----------------------------
DROP TABLE IF EXISTS `dish_flavor`;
CREATE TABLE `dish_flavor`  (
  `id` bigint NOT NULL COMMENT '主键',
  `dish_id` bigint NOT NULL COMMENT '菜品',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '口味名称',
  `value` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '口味数据list',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜品口味关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish_flavor
-- ----------------------------
INSERT INTO `dish_flavor` VALUES (1397849417888346113, 1397849417854791681, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:37:27', '2021-05-27 09:37:27', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397849936421761025, 1397849936404983809, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:39:30', '2021-05-27 09:39:30', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397849936438538241, 1397849936404983809, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:39:30', '2021-05-27 09:39:30', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850630734262274, 1397850630700707841, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:42:16', '2021-05-27 09:42:16', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850630755233794, 1397850630700707841, '辣度', '[\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:42:16', '2021-05-27 09:42:16', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397853423486414850, 1397853423461249026, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:53:22', '2021-05-27 09:53:22', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397854133632413697, 1397854133603053569, '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-05-27 09:56:11', '2021-05-27 09:56:11', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397855742303186946, 1397855742273826817, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:02:35', '2021-05-27 10:02:35', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397855906497605633, 1397855906468245506, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 10:03:14', '2021-05-27 10:03:14', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397856190573621250, 1397856190540066818, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:04:21', '2021-05-27 10:04:21', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397859056709316609, 1397859056684150785, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:15:45', '2021-05-27 10:15:45', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397859277837217794, 1397859277812051969, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:16:37', '2021-05-27 10:16:37', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397859487502086146, 1397859487476920321, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:17:27', '2021-05-27 10:17:27', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397859757061615618, 1397859757036449794, '甜味', '[\"无糖\",\"少糖\",\"半躺\",\"多糖\",\"全糖\"]', '2021-05-27 10:18:32', '2021-05-27 10:18:32', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397861135754506242, 1397861135733534722, '甜味', '[\"无糖\",\"少糖\",\"半躺\",\"多糖\",\"全糖\"]', '2021-05-27 10:24:00', '2021-05-27 10:24:00', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397861370035744769, 1397861370010578945, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:24:56', '2021-05-27 10:24:56', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397861898467717121, 1397861898438356993, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 10:27:02', '2021-05-27 10:27:02', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398089545865015297, 1398089545676271617, '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-05-28 01:31:38', '2021-05-28 01:31:38', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398089782323097601, 1398089782285348866, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:32:34', '2021-05-28 01:32:34', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090003262255106, 1398090003228700673, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-28 01:33:27', '2021-05-28 01:33:27', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090264554811394, 1398090264517062657, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-28 01:34:29', '2021-05-28 01:34:29', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090455399837698, 1398090455324340225, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:35:14', '2021-05-28 01:35:14', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090685449023490, 1398090685419663362, '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-05-28 01:36:09', '2021-05-28 01:36:09', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090825358422017, 1398090825329061889, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-28 01:36:43', '2021-05-28 01:36:43', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091007051476993, 1398091007017922561, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:37:26', '2021-05-28 01:37:26', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091296164851713, 1398091296131297281, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:38:35', '2021-05-28 01:38:35', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091546531246081, 1398091546480914433, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-28 01:39:35', '2021-05-28 01:39:35', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091729809747969, 1398091729788776450, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:40:18', '2021-05-28 01:40:18', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091889499484161, 1398091889449152513, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:40:56', '2021-05-28 01:40:56', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398092095179763713, 1398092095142014978, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:41:45', '2021-05-28 01:41:45', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398092283877306370, 1398092283847946241, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:42:30', '2021-05-28 01:42:30', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398094018939236354, 1398094018893099009, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:49:24', '2021-05-28 01:49:24', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398094391494094850, 1398094391456346113, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:50:53', '2021-05-28 01:50:53', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1399574026165727233, 1399305325713600514, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-06-01 03:50:25', '2021-06-01 03:50:25', 1399309715396669441, 1399309715396669441, 0);
INSERT INTO `dish_flavor` VALUES (1662658014923984898, 1662658014915596290, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:28:52', '2023-05-28 13:28:52', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662658147107475458, 1662658147107475457, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:28:31', '2023-05-28 13:28:31', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662658633806123011, 1662658633806123010, '甜味', '[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]', '2023-05-28 11:14:44', '2023-05-28 11:14:44', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662658887116918786, 1662658887116918785, '忌口', '[\"不要生菜\",\"不要番茄酱\",\"不要沙拉酱\"]', '2023-05-28 13:28:04', '2023-05-28 13:28:04', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662658887116918787, 1662658887116918785, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:28:04', '2023-05-28 13:28:04', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662659057049145346, 1662659057049145345, '忌口', '[\"不要番茄\",\"不要生菜\",\"不要番茄酱\",\"不要沙拉酱\"]', '2023-05-28 13:27:43', '2023-05-28 13:27:43', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662659057116254209, 1662659057049145345, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:27:43', '2023-05-28 13:27:43', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662659267162804227, 1662659267162804226, '忌口', '[\"不要番茄\",\"不要生菜\",\"不要番茄酱\",\"不要沙拉酱\"]', '2023-05-28 11:17:15', '2023-05-28 11:17:15', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662659267162804228, 1662659267162804226, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 11:17:15', '2023-05-28 11:17:15', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662659441935257602, 1662659441935257601, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:27:08', '2023-05-28 13:27:08', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662659687469813762, 1662659687469813761, '忌口', '[\"不要番茄\",\"不要生菜\",\"不要番茄酱\",\"不要沙拉酱\"]', '2023-05-28 13:26:51', '2023-05-28 13:26:51', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662659687469813763, 1662659687469813761, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:26:51', '2023-05-28 13:26:51', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662660253109456898, 1662660253109456897, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:26:26', '2023-05-28 13:26:26', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662660338060890114, 1662660338060890113, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:25:50', '2023-05-28 13:25:50', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662660410743984131, 1662660410743984130, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:25:33', '2023-05-28 13:25:33', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662660475105579010, 1662660475105579009, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:25:14', '2023-05-28 13:25:14', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662660656983183363, 1662660656983183362, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:25:02', '2023-05-28 13:25:02', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662660985179082753, 1662660985111973890, '忌口', '[\"不要生菜\",\"不要沙拉酱\"]', '2023-05-28 13:24:41', '2023-05-28 13:24:41', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662660985179082754, 1662660985111973890, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:24:41', '2023-05-28 13:24:41', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662662086641061890, 1662662086641061889, '忌口', '[\"不要生菜\",\"不要沙拉酱\"]', '2023-05-28 13:23:59', '2023-05-28 13:23:59', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662662086641061891, 1662662086641061889, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:23:59', '2023-05-28 13:23:59', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662662828793794563, 1662662828793794562, '温度', '[\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2023-05-28 12:00:22', '2023-05-28 12:00:22', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662662828793794564, 1662662828793794562, '甜味', '[\"无糖\",\"全糖\"]', '2023-05-28 12:00:22', '2023-05-28 12:00:22', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662662931944312834, 1662662931944312833, '甜味', '[\"无糖\",\"全糖\"]', '2023-05-28 12:00:09', '2023-05-28 12:00:09', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662662931944312835, 1662662931944312833, '温度', '[\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2023-05-28 12:00:09', '2023-05-28 12:00:09', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662662999564881922, 1662655304355115009, '甜味', '[\"无糖\",\"全糖\"]', '2023-05-28 12:00:51', '2023-05-28 12:00:51', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662669468846788611, 1662669468846788610, '甜味', '[\"无糖\",\"全糖\"]', '2023-05-28 11:59:59', '2023-05-28 11:59:59', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662669468846788612, 1662669468846788610, '温度', '[\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2023-05-28 11:59:59', '2023-05-28 11:59:59', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662669703471960067, 1662669703471960066, '温度', '[\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2023-05-28 11:59:45', '2023-05-28 11:59:45', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662669865443397635, 1662669865443397634, '甜味', '[\"无糖\",\"全糖\"]', '2023-05-28 11:59:21', '2023-05-28 11:59:21', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662669865443397636, 1662669865443397634, '温度', '[\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2023-05-28 11:59:21', '2023-05-28 11:59:21', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662669965594988546, 1662669703471960066, '甜味', '[\"无糖\",\"全糖\"]', '2023-05-28 11:59:45', '2023-05-28 11:59:45', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662670240519032833, 1662655304355115009, '温度', '[\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2023-05-28 12:00:51', '2023-05-28 12:00:51', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662670623886807042, 1662670623886807041, '温度', '[\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2023-05-28 12:02:22', '2023-05-28 12:02:22', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662670704325169154, 1662670704325169153, '温度', '[\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2023-05-28 12:02:42', '2023-05-28 12:02:42', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662670776039378946, 1662670776039378945, '温度', '[\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2023-05-28 12:02:59', '2023-05-28 12:02:59', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662690618247221251, 1662690618247221250, '温度', '[\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2023-05-28 13:21:49', '2023-05-28 13:21:49', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662692640014995457, 1662655110213365761, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:29:51', '2023-05-28 13:29:51', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662692640014995458, 1662655110213365761, '忌口', '[\"不要沙拉酱\"]', '2023-05-28 13:29:51', '2023-05-28 13:29:51', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662697006604226563, 1662697006604226562, '忌口', '[\"不要生菜\",\"不要沙拉酱\"]', '2023-05-28 13:47:12', '2023-05-28 13:47:12', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662697006604226564, 1662697006604226562, '辣度', '[\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 13:47:12', '2023-05-28 13:47:12', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662700290790395906, 1662700290727481346, '辣度', '[\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 14:00:15', '2023-05-28 14:00:15', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662700645196500995, 1662700645196500994, '忌口', '[\"不要生菜\",\"不要沙拉酱\"]', '2023-05-28 14:01:40', '2023-05-28 14:01:40', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1662700645196500996, 1662700645196500994, '辣度', '[\"微辣\",\"中辣\",\"重辣\"]', '2023-05-28 14:01:40', '2023-05-28 14:01:40', 1, 1, 0);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '员工信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'cqq', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13297325886', '0', '430523200207028026', 1, '2023-05-20 05:20:00', '2023-05-28 14:08:28', 1, 1);
INSERT INTO `employee` VALUES (1660460282016763905, '张三', '汉堡王员工002', 'e10adc3949ba59abbe56e057f20f883e', '13297320002', '1', '000000000000000002', 1, '2023-05-22 09:39:16', '2023-05-28 13:05:15', 1, 1);
INSERT INTO `employee` VALUES (1660947674465075202, '李四', '汉堡王员工001', 'e10adc3949ba59abbe56e057f20f883e', '13297320001', '1', '000000000000000001', 0, '2023-05-23 17:55:59', '2023-05-28 13:05:29', 1, 1);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名字',
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
  `order_id` bigint NOT NULL COMMENT '订单id',
  `dish_id` bigint NULL DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint NULL DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '口味',
  `number` int NOT NULL DEFAULT 1 COMMENT '数量',
  `amount` decimal(10, 2) NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (1662705256384970753, '【单人餐】双层脆鸡堡+鸡腿+小薯+中可', 'c86ae286-623e-428a-81a8-bb002d301331.jpg', 1662705256322056194, NULL, 1662666694977392641, NULL, 1, 29.00);
INSERT INTO `order_detail` VALUES (1662705256384970754, 'QQ糯米糍', '8b6bde7a-1ddb-4667-90f9-b2794e58a90e.jpg', 1662705256322056194, 1662690741903691778, NULL, NULL, 1, 10.00);
INSERT INTO `order_detail` VALUES (1662706246026805251, '椰椰香柠风味气泡饮', '8ca78492-feaa-4725-b417-9db9247413b3.jpg', 1662706246026805250, 1662690618247221250, NULL, '少冰', 1, 8.00);
INSERT INTO `order_detail` VALUES (1662706246026805252, '脆皮浓浓牛奶棒', 'e2267993-0769-44ed-9bd0-bbf2996741d9.jpg', 1662706246026805250, 1662690375027920898, NULL, NULL, 1, 12.00);
INSERT INTO `order_detail` VALUES (1662706246026805253, '脆皮坚果新地', 'fee57578-cc56-4ba8-85e1-4685ac617c63.jpg', 1662706246026805250, 1662690174754099201, NULL, NULL, 1, 8.00);
INSERT INTO `order_detail` VALUES (1662706246026805254, '【单人餐】狠霸王牛堡+鸡块+小薯+中可', '982a3ae2-a410-439c-9165-714c58cc78ed.jpg', 1662706246026805250, NULL, 1662663620137324546, NULL, 1, 29.00);
INSERT INTO `order_detail` VALUES (1662706246026805255, '【双人餐】双人经济餐A', 'e039112a-89cb-4c92-9771-846b1213d078.jpg', 1662706246026805250, NULL, 1662698104610099202, NULL, 1, 42.00);
INSERT INTO `order_detail` VALUES (1664062885158002690, '【双人餐】双人经济餐B', '471f3455-59eb-495d-a91a-59420269a588.jpg', 1664062885086699522, NULL, 1662698756308471810, NULL, 1, 47.00);
INSERT INTO `order_detail` VALUES (1664062885158002691, '霸王鸡盒', 'e612a966-35d1-46e7-b2f0-b738be6b4c52.png', 1664062885086699522, 1662658014915596290, NULL, '微辣', 1, 29.00);
INSERT INTO `order_detail` VALUES (1664062885158002692, '椰椰香柠风味气泡饮', '8ca78492-feaa-4725-b417-9db9247413b3.jpg', 1664062885086699522, 1662690618247221250, NULL, '少冰', 1, 8.00);
INSERT INTO `order_detail` VALUES (1664249939842125825, '麦辣鸡腿堡', 'b87fe3b7-486e-4c41-9a72-0e325eb7c8de.png', 1664249939833737218, 1662697006604226562, NULL, '不要沙拉酱,重辣', 1, 16.00);
INSERT INTO `order_detail` VALUES (1664249939900846081, 'KING暴风阿华田', '6e9b17ba-52eb-4b34-a5ce-bd81197cc0fa.jpg', 1664249939833737218, 1662689670561009666, NULL, NULL, 1, 6.00);
INSERT INTO `order_detail` VALUES (1664249939900846082, 'QQ糯米糍', '8b6bde7a-1ddb-4667-90f9-b2794e58a90e.jpg', 1664249939833737218, 1662690741903691778, NULL, NULL, 1, 10.00);
INSERT INTO `order_detail` VALUES (1664249939900846083, '北海道风味华夫筒', 'e71aeeba-634b-4106-b8aa-72631c2febe8.jpg', 1664249939833737218, 1662658633806123010, NULL, '少糖', 1, 3.00);
INSERT INTO `order_detail` VALUES (1664249939900846084, '【单人餐】王道川蜀鸡翅+小薯+小可', '0f6a3578-1f5a-46d9-baaa-d6dac01c5d3d.jpg', 1664249939833737218, NULL, 1662666346581725186, NULL, 1, 18.00);
INSERT INTO `order_detail` VALUES (1664833168038957057, '霸王鸡条', '125ef291-9e5e-499f-ac25-0788e7127e16.png', 1664833167971848194, 1662658147107475457, NULL, '中辣', 1, 9.00);
INSERT INTO `order_detail` VALUES (1664833168038957058, '薯条(中)', '9de9361f-dd8b-4ac7-b415-3f83cec6e0f0.png', 1664833167971848194, 1662660338060890113, NULL, '中辣', 1, 10.00);
INSERT INTO `order_detail` VALUES (1664833168038957059, '椰椰香柠风味气泡饮', '8ca78492-feaa-4725-b417-9db9247413b3.jpg', 1664833167971848194, 1662690618247221250, NULL, '多冰', 1, 8.00);
INSERT INTO `order_detail` VALUES (1664833168038957060, 'KING暴风阿华田', '6e9b17ba-52eb-4b34-a5ce-bd81197cc0fa.jpg', 1664833167971848194, 1662689670561009666, NULL, NULL, 1, 6.00);
INSERT INTO `order_detail` VALUES (1664833168038957061, 'QQ糯米糍', '8b6bde7a-1ddb-4667-90f9-b2794e58a90e.jpg', 1664833167971848194, 1662690741903691778, NULL, NULL, 1, 10.00);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint NOT NULL COMMENT '主键',
  `number` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单号',
  `status` int NOT NULL DEFAULT 1 COMMENT '订单状态 1待付款，2待派送，3已派送，4已完成，5已取消',
  `user_id` bigint NOT NULL COMMENT '下单用户',
  `address_book_id` bigint NOT NULL COMMENT '地址id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `checkout_time` datetime NOT NULL COMMENT '结账时间',
  `pay_method` int NOT NULL DEFAULT 1 COMMENT '支付方式 1微信,2支付宝',
  `amount` decimal(10, 2) NOT NULL COMMENT '实收金额',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `consignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1662705256322056194, '1662705256322056194', 4, 1662703442851794946, 1662704856772657153, '2023-05-28 14:19:59', '2023-05-28 14:19:59', 1, 39.00, '', '13297325886', 'hnust', NULL, 'wangwu');
INSERT INTO `orders` VALUES (1662706246026805250, '1662706246026805250', 3, 1661662755305832450, 1662706004665581570, '2023-05-28 14:23:55', '2023-05-28 14:23:55', 1, 99.00, '', '13297325886', '湖南科技大学八区', 'cqq', 'cqq');
INSERT INTO `orders` VALUES (1664062885086699522, '1664062885086699522', 2, 1661662755305832450, 1662706004665581570, '2023-06-01 08:14:43', '2023-06-01 08:14:43', 1, 84.00, '', '13297325886', '湖南科技大学八区', 'cqq', 'cqq');
INSERT INTO `orders` VALUES (1664249939833737218, '1664249939833737218', 2, 1661662755305832450, 1662706004665581570, '2023-06-01 20:38:01', '2023-06-01 20:38:01', 1, 53.00, '', '13297325886', '湖南科技大学八区', 'cqq', 'cqq');
INSERT INTO `orders` VALUES (1664833167971848194, '1664833167971848194', 2, 1661662755305832450, 1662706137000067073, '2023-06-03 11:15:33', '2023-06-03 11:15:33', 1, 43.00, '', '13297325886', '湖南长沙雨花区', 'cqq', 'cqq');

-- ----------------------------
-- Table structure for setmeal
-- ----------------------------
DROP TABLE IF EXISTS `setmeal`;
CREATE TABLE `setmeal`  (
  `id` bigint NOT NULL COMMENT '主键',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '套餐名称',
  `price` decimal(10, 2) NOT NULL COMMENT '套餐价格',
  `status` int NULL DEFAULT NULL COMMENT '状态 0:停用 1:启用',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述信息',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_setmeal_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '套餐' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of setmeal
-- ----------------------------
INSERT INTO `setmeal` VALUES (1662663620137324546, 1662654359579750401, '【单人餐】狠霸王牛堡+鸡块+小薯+中可', 2900.00, 1, '', '狠霸王牛堡+鸡块+小薯+中可', '982a3ae2-a410-439c-9165-714c58cc78ed.jpg', '2023-05-28 11:34:33', '2023-05-28 13:32:40', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1662664161533890561, 1662654359579750401, '【单人餐】鸡腿堡+椒香鸡腿+小薯+中可', 2600.00, 1, '', '炫辣鸡腿堡+王道椒香鸡腿+小薯+中可', 'cc3d7e39-c27c-4912-8548-d56626e62879.jpg', '2023-05-28 11:36:42', '2023-05-28 13:32:11', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1662664756659490818, 1662654359579750401, '【单人餐】皇堡+果木风味鸡腿堡', 2500.00, 1, '', '皇堡+果木风味鸡腿堡', 'ebf8b160-83b5-472f-9df9-e41edaf82ba9.jpg', '2023-05-28 11:39:03', '2023-05-28 13:33:03', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1662665282105118721, 1662654359579750401, '【单人餐】3层芝士牛堡+鸡块+小薯+小可', 2900.00, 1, '', '3层芝士牛堡+鸡块+小薯+小可', '216c4f9f-14c3-4378-95b2-c70058b3b677.jpg', '2023-05-28 11:41:09', '2023-05-28 13:33:26', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1662665804744757250, 1662654359579750401, '【单人餐】果味鸡腿堡+鸡腿+小薯+中可', 2600.00, 1, '', '果木风味鸡腿堡+王道椒香鸡腿+小薯+中可', '727d7a43-d811-4675-87ef-951e144e1ed9.jpg', '2023-05-28 11:43:13', '2023-05-28 13:33:59', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1662666346581725186, 1662654359579750401, '【单人餐】王道川蜀鸡翅+小薯+小可', 1800.00, 1, '', '【单人餐】王道川蜀鸡翅+小薯+小可', '0f6a3578-1f5a-46d9-baaa-d6dac01c5d3d.jpg', '2023-05-28 11:45:23', '2023-05-28 13:34:32', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1662666694977392641, 1662654359579750401, '【单人餐】双层脆鸡堡+鸡腿+小薯+中可', 2900.00, 1, '', '双层脆鸡堡+鸡腿+小薯+中可', 'c86ae286-623e-428a-81a8-bb002d301331.jpg', '2023-05-28 11:46:46', '2023-05-28 13:36:27', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1662666897176399874, 1662654359579750401, '【单人餐】霸王鸡盒+中可', 3400.00, 1, '', '霸王鸡盒+中可', '40a1be7b-5878-42a5-9d99-9e24f845766e.jpg', '2023-05-28 11:47:34', '2023-05-28 13:34:53', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1662667135232512001, 1662654359579750401, '【单人餐】皇堡+霸王鸡条+小可', 3200.00, 1, '', '皇堡+霸王鸡条+小可', 'a413cd47-3580-41ef-b32f-2a6c7a71cb0b.jpg', '2023-05-28 11:48:31', '2023-05-28 13:35:21', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1662667563076685826, 1662654359579750401, '【单人餐】皇堡×2+雪碧(中)', 3300.00, 1, '', '皇堡×2+雪碧(中)', '63c59f0e-d6a4-42bd-97a4-b731c36f2101.jpg', '2023-05-28 11:50:13', '2023-05-28 13:30:48', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1662667816286818306, 1662654359579750401, '【单人餐】大嘴安格斯+鸡条+中可', 3900.00, 1, '', '大嘴安格斯+霸王鸡条+中可', '698b28d6-230c-4560-9084-99e894afc6f3.jpg', '2023-05-28 11:51:13', '2023-05-28 13:35:54', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1662698104610099202, 1662697124178956289, '【双人餐】双人经济餐A', 4200.00, 1, '', '双层脆鸡堡+炫辣鸡腿堡+王道嫩香鸡块+薯条(小)+可乐(中)×2', 'e039112a-89cb-4c92-9771-846b1213d078.jpg', '2023-05-28 13:51:34', '2023-05-28 13:51:34', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1662698756308471810, 1662697124178956289, '【双人餐】双人经济餐B', 4700.00, 0, '', '皇堡+双层脆鸡堡+王道嫩香鸡块+薯条(小)+可乐(中)×2', '471f3455-59eb-495d-a91a-59420269a588.jpg', '2023-05-28 13:54:10', '2023-06-01 19:25:33', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1662699607282421762, 1662697170144333826, '【三人餐】三人爆款餐', 5400.00, 0, '', '炫辣鸡腿堡+双层脆鸡堡+脆鸡堡+鸡块+薯条(中)+可乐(中)×3', 'c02eb5ec-df64-415e-b144-03c137031f6c.jpg', '2023-05-28 13:57:33', '2023-06-01 19:25:33', 1, 1, 0);

-- ----------------------------
-- Table structure for setmeal_dish
-- ----------------------------
DROP TABLE IF EXISTS `setmeal_dish`;
CREATE TABLE `setmeal_dish`  (
  `id` bigint NOT NULL COMMENT '主键',
  `setmeal_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '套餐id ',
  `dish_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜品id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜品名称 （冗余字段）',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '菜品原价（冗余字段）',
  `copies` int NOT NULL COMMENT '份数',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '套餐菜品关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of setmeal_dish
-- ----------------------------
INSERT INTO `setmeal_dish` VALUES (1662692876380803073, '1662667563076685826', '1662659057049145345', '皇堡', 1900.00, 2, 0, '2023-05-28 13:30:48', '2023-05-28 13:30:48', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662692876380803074, '1662667563076685826', '1662669703471960066', '雪碧(中)', 800.00, 1, 0, '2023-05-28 13:30:48', '2023-05-28 13:30:48', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693227163029505, '1662664161533890561', '1662662086641061889', '炫辣鸡腿堡', 1600.00, 1, 0, '2023-05-28 13:32:11', '2023-05-28 13:32:11', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693227163029506, '1662664161533890561', '1662660656983183362', '王道椒香鸡腿', 900.00, 1, 0, '2023-05-28 13:32:11', '2023-05-28 13:32:11', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693227163029507, '1662664161533890561', '1662660253109456897', '薯条(小)', 800.00, 1, 0, '2023-05-28 13:32:11', '2023-05-28 13:32:11', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693227163029508, '1662664161533890561', '1662662828793794562', '可乐(中)', 800.00, 1, 0, '2023-05-28 13:32:11', '2023-05-28 13:32:11', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693345853444098, '1662663620137324546', '1662660985111973890', '狠霸王牛堡', 2300.00, 1, 0, '2023-05-28 13:32:40', '2023-05-28 13:32:40', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693345853444099, '1662663620137324546', '1662660475105579009', '鸡块', 900.00, 1, 0, '2023-05-28 13:32:40', '2023-05-28 13:32:40', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693345853444100, '1662663620137324546', '1662660253109456897', '薯条(小)', 800.00, 1, 0, '2023-05-28 13:32:40', '2023-05-28 13:32:40', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693345853444101, '1662663620137324546', '1662662828793794562', '可乐(中)', 800.00, 1, 0, '2023-05-28 13:32:40', '2023-05-28 13:32:40', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693443727527938, '1662664756659490818', '1662659057049145345', '皇堡', 1900.00, 1, 0, '2023-05-28 13:33:03', '2023-05-28 13:33:03', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693443727527939, '1662664756659490818', '1662659687469813761', '果木风味鸡腿堡', 1900.00, 1, 0, '2023-05-28 13:33:03', '2023-05-28 13:33:03', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693539357659137, '1662665282105118721', '1662655110213365761', '3层芝士牛堡', 2500.00, 1, 0, '2023-05-28 13:33:26', '2023-05-28 13:33:26', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693539357659138, '1662665282105118721', '1662660475105579009', '鸡块', 900.00, 1, 0, '2023-05-28 13:33:26', '2023-05-28 13:33:26', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693539357659139, '1662665282105118721', '1662660253109456897', '薯条(小)', 800.00, 1, 0, '2023-05-28 13:33:26', '2023-05-28 13:33:26', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693539357659140, '1662665282105118721', '1662655304355115009', '可乐(小)', 600.00, 1, 0, '2023-05-28 13:33:26', '2023-05-28 13:33:26', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693677283151874, '1662665804744757250', '1662659687469813761', '果木风味鸡腿堡', 1900.00, 1, 0, '2023-05-28 13:33:59', '2023-05-28 13:33:59', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693677283151875, '1662665804744757250', '1662660656983183362', '王道椒香鸡腿', 900.00, 1, 0, '2023-05-28 13:33:59', '2023-05-28 13:33:59', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693677283151876, '1662665804744757250', '1662660253109456897', '薯条(小)', 800.00, 1, 0, '2023-05-28 13:33:59', '2023-05-28 13:33:59', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693677283151877, '1662665804744757250', '1662662828793794562', '可乐(中)', 800.00, 1, 0, '2023-05-28 13:33:59', '2023-05-28 13:33:59', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693817339351041, '1662666346581725186', '1662660253109456897', '薯条(小)', 800.00, 1, 0, '2023-05-28 13:34:32', '2023-05-28 13:34:32', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693817339351042, '1662666346581725186', '1662659441935257601', '王道川蜀鸡翅', 900.00, 1, 0, '2023-05-28 13:34:32', '2023-05-28 13:34:32', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693817339351043, '1662666346581725186', '1662655304355115009', '可乐(小)', 600.00, 1, 0, '2023-05-28 13:34:32', '2023-05-28 13:34:32', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693903310000129, '1662666897176399874', '1662658014915596290', '霸王鸡盒', 2900.00, 1, 0, '2023-05-28 13:34:53', '2023-05-28 13:34:53', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662693903310000130, '1662666897176399874', '1662662828793794562', '可乐(中)', 800.00, 1, 0, '2023-05-28 13:34:53', '2023-05-28 13:34:53', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662694021731979265, '1662667135232512001', '1662659057049145345', '皇堡', 1900.00, 1, 0, '2023-05-28 13:35:21', '2023-05-28 13:35:21', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662694021731979266, '1662667135232512001', '1662658147107475457', '霸王鸡条', 900.00, 1, 0, '2023-05-28 13:35:21', '2023-05-28 13:35:21', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662694021731979267, '1662667135232512001', '1662655304355115009', '可乐(小)', 600.00, 1, 0, '2023-05-28 13:35:21', '2023-05-28 13:35:21', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662694161104506881, '1662667816286818306', '1662658887116918785', '大嘴安格斯', 2800.00, 1, 0, '2023-05-28 13:35:54', '2023-05-28 13:35:54', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662694161104506882, '1662667816286818306', '1662658147107475457', '霸王鸡条', 900.00, 1, 0, '2023-05-28 13:35:54', '2023-05-28 13:35:54', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662694161104506883, '1662667816286818306', '1662662828793794562', '可乐(中)', 800.00, 1, 0, '2023-05-28 13:35:54', '2023-05-28 13:35:54', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662694301395587073, '1662666694977392641', '1662659267162804226', '双层脆鸡堡', 2200.00, 1, 0, '2023-05-28 13:36:27', '2023-05-28 13:36:27', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662694301395587074, '1662666694977392641', '1662660656983183362', '王道椒香鸡腿', 900.00, 1, 0, '2023-05-28 13:36:27', '2023-05-28 13:36:27', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662694301395587075, '1662666694977392641', '1662660253109456897', '薯条(小)', 800.00, 1, 0, '2023-05-28 13:36:27', '2023-05-28 13:36:27', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662694301395587076, '1662666694977392641', '1662662828793794562', '可乐(中)', 800.00, 1, 0, '2023-05-28 13:36:27', '2023-05-28 13:36:27', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662698104677208065, '1662698104610099202', '1662659267162804226', '双层脆鸡堡', 2200.00, 1, 0, '2023-05-28 13:51:34', '2023-05-28 13:51:34', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662698104677208066, '1662698104610099202', '1662662086641061889', '炫辣鸡腿堡', 1600.00, 1, 0, '2023-05-28 13:51:34', '2023-05-28 13:51:34', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662698104677208067, '1662698104610099202', '1662660253109456897', '薯条(小)', 800.00, 1, 0, '2023-05-28 13:51:34', '2023-05-28 13:51:34', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662698104677208068, '1662698104610099202', '1662660475105579009', '鸡块', 900.00, 1, 0, '2023-05-28 13:51:34', '2023-05-28 13:51:34', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662698104677208069, '1662698104610099202', '1662662828793794562', '可乐(中)', 800.00, 2, 0, '2023-05-28 13:51:34', '2023-05-28 13:51:34', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662699607282421763, '1662699607282421762', '1662662086641061889', '炫辣鸡腿堡', 1600.00, 1, 0, '2023-05-28 13:57:33', '2023-05-28 13:57:33', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662699607282421764, '1662699607282421762', '1662659267162804226', '双层脆鸡堡', 2200.00, 1, 0, '2023-05-28 13:57:33', '2023-05-28 13:57:33', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662699607282421765, '1662699607282421762', '1662697006604226562', '麦辣鸡腿堡', 1600.00, 1, 0, '2023-05-28 13:57:33', '2023-05-28 13:57:33', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662699607282421766, '1662699607282421762', '1662660475105579009', '鸡块', 900.00, 1, 0, '2023-05-28 13:57:33', '2023-05-28 13:57:33', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662699607282421767, '1662699607282421762', '1662660338060890113', '薯条(中)', 1000.00, 1, 0, '2023-05-28 13:57:33', '2023-05-28 13:57:33', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1662699607282421768, '1662699607282421762', '1662662828793794562', '可乐(中)', 800.00, 3, 0, '2023-05-28 13:57:33', '2023-05-28 13:57:33', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1663026861032079362, '1662698756308471810', '1662659057049145345', '皇堡', 1900.00, 1, 0, '2023-05-29 11:37:56', '2023-05-29 11:37:56', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1663026861032079363, '1662698756308471810', '1662659267162804226', '双层脆鸡堡', 2200.00, 1, 0, '2023-05-29 11:37:56', '2023-05-29 11:37:56', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1663026861032079364, '1662698756308471810', '1662660253109456897', '薯条(小)', 800.00, 1, 0, '2023-05-29 11:37:56', '2023-05-29 11:37:56', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1663026861032079365, '1662698756308471810', '1662660475105579009', '鸡块', 900.00, 1, 0, '2023-05-29 11:37:56', '2023-05-29 11:37:56', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1663026861032079366, '1662698756308471810', '1662662828793794562', '可乐(中)', 800.00, 2, 0, '2023-05-29 11:37:56', '2023-05-29 11:37:56', 1, 1, 0);

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
  `user_id` bigint NOT NULL COMMENT '主键',
  `dish_id` bigint NULL DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint NULL DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '口味',
  `number` int NOT NULL DEFAULT 1 COMMENT '数量',
  `amount` decimal(10, 2) NOT NULL COMMENT '金额',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES (1662705360214966273, '狠霸王牛堡', 'a86415f0-dbba-44b6-a17d-a107cc154f5e.png', 1662703442851794946, 1662660985111973890, NULL, '不要生菜,中辣', 1, 23.00, '2023-05-28 14:20:24');
INSERT INTO `shopping_cart` VALUES (1662705378275639297, '【双人餐】双人经济餐B', '471f3455-59eb-495d-a91a-59420269a588.jpg', 1662703442851794946, NULL, 1662698756308471810, NULL, 1, 47.00, '2023-05-28 14:20:28');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `status` int NULL DEFAULT 0 COMMENT '状态 0:禁用，1:正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1661662755305832450, 'cqq', '13297325886', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (1662703442851794946, NULL, '19898820557', NULL, NULL, NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
