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
    `updateTime` BIGINT(10) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;