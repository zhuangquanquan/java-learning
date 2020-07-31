DROP TABLE IF EXISTS `wechat_user`;
CREATE TABLE `wechat_user` (
    `id` BIGINT(10) NOT NULL,
    `user_name` VARCHAR(64) NOT NULL,
    `password` VARCHAR(128) NOT NULL,
    `type` INT(4) NOT NULL,
    `phone` VARCHAR(32) NULL DEFAULT NULL,
    `email` VARCHAR(128) NULL DEFAULT NULL,
    `status` INT(4) NOT NULL,
    `lockedAt` BIGINT(10) NULL DEFAULT NULL,
    `description` VARCHAR(256) NULL DEFAULT NULL,
    `createTime` BIGINT(10) NULL DEFAULT NULL,
    `updateTime` BIGINT(10) NULL DEFAULT NULL,
    `role` VARCHAR(64) NOT NULL,
    `permission` VARCHAR(64) NOT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wechat_role`;
CREATE TABLE `wechat_role` (
    `id` BIGINT(10) NOT NULL,
    `role` VARCHAR(64) NOT NULL,
    `permission` VARCHAR(64) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `wechat_user` VALUES ('1', 'admin1', '123456', '1', '1', '1', '1', '1', '1', '1', '1', 'admin', 'admin');
INSERT INTO `wechat_user` VALUES ('2', 'manage1', '123456', '1', '1', '1', '1', '1', '1', '1', '1', 'manage', 'manage');
INSERT INTO `wechat_user` VALUES ('3', 'user1', '123456', '1', '1', '1', '1', '1', '1', '1', '1', 'user', 'user');

INSERT INTO `wechat_role` VALUES ('1', 'admin', 'admin');
INSERT INTO `wechat_role` VALUES ('2', 'manage', 'manage');
INSERT INTO `wechat_role` VALUES ('3', 'user', 'user');