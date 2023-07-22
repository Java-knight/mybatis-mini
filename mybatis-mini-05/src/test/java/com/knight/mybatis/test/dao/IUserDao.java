package com.knight.mybatis.test.dao;

import com.knight.mybatis.test.pojo.User;

public interface IUserDao {

    User findUserInfoById(Long id);

    User findUserInfo(User user);

}
