/*
 Navicat Premium Data Transfer

 Source Server         : student
 Source Server Type    : MariaDB
 Source Server Version : 100130
 Source Host           : localhost:3306
 Source Schema         : student

 Target Server Type    : MariaDB
 Target Server Version : 100130
 File Encoding         : 65001

 Date: 22/05/2018 14:18:40
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id`        varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '学号',
  `password`  varchar(255)  CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL DEFAULT '123456',
  `name`      varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
  `sex`       char(1)       CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
  `birthday`  datetime(0)                                               NULL DEFAULT NULL,
  `grade`     varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
  `major`     varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
  `phone`     varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
  `email`     varchar(255)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
  `wechat`    varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
  `qq`        varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
  `notes`     text          CHARACTER SET utf8 COLLATE utf8_general_ci  NULL,
  `cdate`     datetime(0)   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('201521010000', '123456', '张三丰', 'M', '1800-01-01 00:00:00', '1800', 'dao', '8888888888', 'haven@haven.com', 'heaven', '123456789', '道教鼻祖', '2018-05-22 14:15:34');
INSERT INTO `student` VALUES ('201521010001', '123456', '张无忌', 'M', '1900-01-01 00:00:00', '1900', 'ming', '999999999999', 'hell@hell.com', 'hell', '234567890', '明教教主', '2018-05-22 14:17:06');
INSERT INTO `student` VALUES ('201521010002', '123456', '张无忌', 'M', '1900-01-01 00:00:00', '1900', 'ming', '999999999999', 'hell@hell.com', 'hell', '234567890', '明教教主', '2018-05-22 14:17:06');
INSERT INTO `student` VALUES ('201521010003', '123456', '张无忌', 'M', '1900-01-01 00:00:00', '1900', 'ming', '999999999999', 'hell@hell.com', 'hell', '234567890', '明教教主', '2018-05-22 14:17:06');

SET FOREIGN_KEY_CHECKS = 1;
