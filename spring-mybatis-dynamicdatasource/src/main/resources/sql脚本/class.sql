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

 Date: 01/02/2019 09:53:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '班级ID',
  `grade` int(4) NOT NULL COMMENT '年级',
  `class` int(3) NOT NULL COMMENT '班级',
  `master` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班主任',
  `count` int(3) NOT NULL COMMENT '人数',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
