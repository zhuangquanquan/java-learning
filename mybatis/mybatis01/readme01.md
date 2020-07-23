# 
主要包括mybatis基本的增删改查功能

1. mysql数据库创建
```sql
create database cloudmap;
use cloudmap;

DROP TABLE IF EXISTS `my_user`;
CREATE TABLE `my_user` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='my_user';

-- ----------------------------
-- Records of my_user
-- ----------------------------
INSERT INTO `my_user` VALUES ('1', 'kang', '123456');
INSERT INTO `my_user` VALUES ('2', 'feng', '123412');
INSERT INTO `my_user` VALUES ('3', 'wang', '324212');
