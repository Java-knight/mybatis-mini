package com.knight.mybatis.test;

import com.knight.mybatis.binding.MapperProxyFactory;
import com.knight.mybatis.test.dao.IUserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc
 * @date 2023/5/27
 */
@Slf4j
public class ApiTest {


    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<>();

        sqlSession.put("com.knight.mybatis.test.dao.IUserDao.findUserName",
                "模拟执行Mapper.xml 中 SQL 语句的操作: 查询用户姓名");
        sqlSession.put("com.knight.mybatis.test.dao.IUserDao.findUserAge",
                "模拟执行Mapper.xml 中 SQL 语句的操作: 查询用户年龄");
        IUserDao userDao = factory.newInstance(sqlSession);
        String res = userDao.findUserName("knight");
        log.info("test result: " + res);
    }

    @Test
    public void test_proxy_class() {
        IUserDao userDao = (IUserDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserDao.class}, (proxy, method, args) -> "你的操作被单例!");
        String result = userDao.findUserName("knight");
        System.out.printf("test result: " + result);
    }
}
