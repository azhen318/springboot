/*
 Navicat Premium Data Transfer

 Source Server         :
 Source Server Type    : MySQL
 Source Server Version : 50631
 Source Host           :
 Source Schema         :

 Target Server Type    : MySQL
 Target Server Version : 50631
 File Encoding         : 65001

 Date: 01/02/2019 09:54:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `sex` int(1) NOT NULL COMMENT '性别',
  `classID` int(3) NULL DEFAULT NULL COMMENT '班级外键',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  UNIQUE INDEX `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
