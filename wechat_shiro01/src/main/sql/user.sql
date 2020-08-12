-- 1. 用户表
DROP TABLE IF EXISTS `wechat_user`;
CREATE TABLE `wechat_user` (
    `id` BIGINT(10) NOT NULL,
    `user_name` VARCHAR(64) NOT NULL,
    `password` VARCHAR(128) NOT NULL,
    `real_name` VARCHAR(500) NOT NULL,
    `phone` VARCHAR(500) NULL DEFAULT NULL,
    `email` VARCHAR(500) NULL DEFAULT NULL,
    `status` INT(4) NOT NULL,
    `lock_at` BIGINT(10) NULL DEFAULT NULL,
    `description` VARCHAR(256) NULL DEFAULT NULL,
    `create_time` BIGINT(10) NULL DEFAULT NULL,
    `update_time` BIGINT(10) NULL DEFAULT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `wechat_user` VALUES (1, 'admin', '123456', '管理员', '1', '1', '1', '1', '1', '1', '1');
-- INSERT INTO `wechat_user` VALUES (2, 'manage', '123456', '客户经理', '1', '1', '1', '1', '1', '1', '1');
-- INSERT INTO `wechat_user` VALUES (3, 'customer', '123456', '用户', '1', '1', '1', '1', '1', '1', '1');

-- 2. 角色表
DROP TABLE IF EXISTS `wechat_role`;
CREATE TABLE IF NOT EXISTS `wechat_role` (
  `id` bigint(10) NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `create_time` bigint(10) DEFAULT NULL,
  `update_time` bigint(10) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `wechat_role` VALUES (1, 'admin', '后台管理员', 0, 0);
INSERT INTO `wechat_role` VALUES (2, 'manage', '电信客户经理', 0, 0);
INSERT INTO `wechat_role` VALUES (3, 'customer', '客户', 0, 0);

-- 3. 权限表
DROP TABLE IF EXISTS `wechat_permission`;
CREATE TABLE IF NOT EXISTS `wechat_permission` (
  `id` bigint(10) NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `wechat_permission` VALUES (1, 'insert', '添加');
INSERT INTO `wechat_permission` VALUES (2, 'delete', '删除');
INSERT INTO `wechat_permission` VALUES (3, 'update', '修改');
INSERT INTO `wechat_permission` VALUES (4, 'query', '查询');
INSERT INTO `wechat_permission` VALUES (5, 'import', '导入');
INSERT INTO `wechat_permission` VALUES (6, 'export', '导出');

-- 4. 角色权限表
DROP TABLE IF EXISTS `wechat_role_permission`;
CREATE TABLE IF NOT EXISTS `wechat_role_permission` (
  `role_id` varchar(64) NOT NULL,
  `permission_id` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `wechat_role_permission` VALUES (1, 1);
INSERT INTO `wechat_role_permission` VALUES (1, 2);
INSERT INTO `wechat_role_permission` VALUES (1, 3);
INSERT INTO `wechat_role_permission` VALUES (1, 4);
INSERT INTO `wechat_role_permission` VALUES (1, 5);
INSERT INTO `wechat_role_permission` VALUES (1, 6);

-- 5. 用户角色表
DROP TABLE IF EXISTS `wechat_user_role`;
CREATE TABLE IF NOT EXISTS `wechat_user_role` (
  `user_id` bigint(10) NOT NULL,
  `role_id` bigint(10) NOT NULL,
   PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `wechat_user_role` VALUES (1, 1);
-- INSERT INTO `wechat_user_role` VALUES (2, 2);
-- INSERT INTO `wechat_user_role` VALUES (3, 3);
