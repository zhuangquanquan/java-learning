DROP TABLE IF EXISTS `wechat_user`;
CREATE TABLE `wechat_user` (
    `id` BIGINT(10) NOT NULL,
    `user_name` VARCHAR(64) NOT NULL,
    `password` VARCHAR(128) NOT NULL,
    `real_name` VARCHAR(128) NOT NULL,
    `phone` VARCHAR(32) NULL DEFAULT NULL,
    `email` VARCHAR(128) NULL DEFAULT NULL,
    `status` INT(4) NOT NULL,
    `lock_at` BIGINT(10) NULL DEFAULT NULL,
    `description` VARCHAR(256) NULL DEFAULT NULL,
    `create_time` BIGINT(10) NULL DEFAULT NULL,
    `update_time` BIGINT(10) NULL DEFAULT NULL,
    `role` VARCHAR(64) NOT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `wechat_user` VALUES ('1', 'admin1', '123456', '管理员', '1', '1', '1', '1', '1', '1', '1', 'admin');
INSERT INTO `wechat_user` VALUES ('2', 'manage1', '123456', '客户经理', '1', '1', '1', '1', '1', '1', '1', 'manage');
INSERT INTO `wechat_user` VALUES ('3', 'user1', '123456', '用户', '1', '1', '1', '1', '1', '1', '1', 'customer');