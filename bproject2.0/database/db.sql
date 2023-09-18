DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user(
                         `user_id` INT(255) NOT NULL AUTO_INCREMENT  COMMENT '用户的id' ,
                         `username` VARCHAR(255) NOT NULL   COMMENT '用户账号' ,
                         `password` VARCHAR(255) NOT NULL   COMMENT '用户密码' ,
                         `nickname` VARCHAR(255)    COMMENT '用户昵称' ,
                         `is_delete` INT(1) NOT NULL   COMMENT '是否被删除' ,
                         PRIMARY KEY (user_id)
)  COMMENT = '用户表';

DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role(
                         `rid` INT(32) NOT NULL AUTO_INCREMENT  COMMENT '角色编号' ,
                         `rname` VARCHAR(32) NOT NULL   COMMENT '角色名称' ,
                         PRIMARY KEY (rid)
)  COMMENT = '角色表';

DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role(
                              `uid` INT    COMMENT '用户id' ,
                              `rid` INT    COMMENT '角色id'
)  COMMENT = '用户角色表';

DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission(
                               `perm_id` INT(255) NOT NULL  auto_increment  COMMENT '权限id' ,
                               `perm_name` VARCHAR(255)    COMMENT '权限名称' ,
                               `perm_url` VARCHAR(255)    COMMENT '权限路径' ,
                               `perm_type` VARCHAR(255)    COMMENT '权限类型' ,
                               `parent_id` INT(255)    COMMENT '父类id' ,
                               `perm_code` VARCHAR(255)    COMMENT '权限编码' ,
                               PRIMARY KEY (perm_id)
)  COMMENT = '权限表';

DROP TABLE IF EXISTS sys_role_permission;
CREATE TABLE sys_role_permission(
                                    `role_id` INT    COMMENT '角色id' ,
                                    `permission_id` INT    COMMENT '权限id'
)  COMMENT = '角色权限表';

