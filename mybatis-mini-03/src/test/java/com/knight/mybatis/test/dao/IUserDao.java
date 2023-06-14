package com.knight.mybatis.test.dao;

public interface IUserDao {

    String findUserName(String uid);

    Integer findUserAge(String uid);

}
