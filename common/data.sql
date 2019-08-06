# 创建数据库
CREATE DATABASE IF NOT EXISTS
    spring_security_examples
    DEFAULT CHARSET utf8mb4
    COLLATE utf8mb4_general_ci;

# 使用数据库
USE spring_security_examples;

# 创建 user 表
CREATE TABLE IF NOT EXISTS `user` (
    `id`       INT(11) PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(16) NOT NULL COMMENT '用户名',
    `password` VARCHAR(64) NOT NULL COMMENT '密码',
    `updated`  DATETIME    NOT NULL DEFAULT current_timestamp
        ON UPDATE current_timestamp COMMENT '更新时间，记录更新时自动更新为当前时间',
    `created`  DATETIME    NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    UNIQUE KEY `uidx_username` (`username`) COMMENT '用户名唯一索引',
    KEY `idx_created` (`created`) COMMENT '创建时间索引'
)
    ENGINE = InnoDB
    DEFAULT CHARSET utf8mb4 COMMENT '用户表';

# 创建角色表
CREATE TABLE IF NOT EXISTS `user_role` (
    `id`        INT(11) PRIMARY KEY AUTO_INCREMENT,
    `user_id`   INT(11)     NOT NULL COMMENT 'user id',
    `role_name` VARCHAR(16) NOT NULL COMMENT '角色名',
    UNIQUE INDEX `uidx_user_id_role_name` (`user_id`, `role_name`) COMMENT '用户 id 和角色唯一索引'
)
    ENGINE = InnoDB
    DEFAULT CHARSET utf8mb4 COMMENT '角色表，用户 1:N 角色';

# 模块二数据
INSERT INTO user (username, password)
VALUES ('admin02', 'admin'),
       ('user02', 'user');

INSERT INTO user_role(user_id, role_name)
VALUES ((SELECT id FROM user WHERE user.username = 'admin02'), 'ROLE_ADMIN'),
       ((SELECT id FROM user WHERE user.username = 'user02'), 'ROLE_USER')