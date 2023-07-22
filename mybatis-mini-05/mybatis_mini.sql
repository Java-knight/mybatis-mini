CREATE DATABASE mybatis_mini;

USE mybatis_mini;

DROP TABLE user;

CREATE TABLE user(
  id bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  userId varchar(9) COMMENT '用户id',
  userName varchar(64),
  userHead varchar(16) COMMENT '用户头像',
  createTime timestamp NULL COMMENT '创建时间',
  updateTime timestamp NULL COMMENT '更新时间',
  PRIMARY KEY(id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

insert into user (id, userId, userName, userHead, createTime, updateTime)
  values
  (1, '10001', 'knight', 'knight.png', '2023-07-01 00:00:00', '2022-07-01 00:00:00');
