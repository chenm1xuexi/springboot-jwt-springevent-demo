-- ----------------------------
-- Table structure for t_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_organization`;
CREATE TABLE `t_organization`
(
    `id`                bigint(13)   NOT NULL AUTO_INCREMENT COMMENT '资源主键',
    `organization_id`   varchar(50)  NOT NULL COMMENT '组织id',
    `organization_name` varchar(100) NOT NULL COMMENT '组织名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`
(
    `id`        bigint(13)  NOT NULL AUTO_INCREMENT COMMENT '资源主键',
    `role_id`   varchar(50) NOT NULL COMMENT '角色id',
    `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`              bigint(13)   NOT NULL AUTO_INCREMENT COMMENT '资源主键',
    `user_id`         varchar(50)  NOT NULL COMMENT '用户id',
    `username`        varchar(50)  NOT NULL COMMENT '用户名称',
    `password`        varchar(255) NOT NULL COMMENT '密码 md5加密',
    `role_id`         varchar(50)  NOT NULL COMMENT '角色id',
    `organization_id` varchar(50)  NOT NULL COMMENT '组织id,以此区分租户',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;