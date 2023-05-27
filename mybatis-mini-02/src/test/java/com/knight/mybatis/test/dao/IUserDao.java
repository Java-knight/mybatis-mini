package com.knight.mybatis.test.dao;

/**
 * @desc
 * @date 2023/5/27
 */
public interface IUserDao {

    String findUserName(String uid);

    Integer findUserAge(String uid);
}
