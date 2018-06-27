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
INSERT INTO `student` VALUES ('201521012358', '123456', '李宗尧', 'M', '1997-07-02 00:00:00', '2015', 'CS', '999999999999', 'BobLi@163.com', '黑夜银痕', '234567890', '优秀的人', '2018-05-22 14:17:06');

SET FOREIGN_KEY_CHECKS = 1;
