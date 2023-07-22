package com.knight.mybatis.test;

import com.alibaba.fastjson.JSON;
import com.knight.mybatis.io.Resources;
import com.knight.mybatis.session.SqlSession;
import com.knight.mybatis.session.SqlSessionFactory;
import com.knight.mybatis.session.SqlSessionFactoryBuilder;
import com.knight.mybatis.test.dao.IUserDao;
import com.knight.mybatis.test.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * @desc
 * @author knight
 * @date 2023/6/15
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test_findUserInfoById() {
        // (1) 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // (2) 测试验证
        User res = userDao.findUserInfoById(1L);
        logger.info("test result: {}", JSON.toJSONString(res));
    }

    @Test
    public void test_findUserInfo() {
        // (1) 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // (2) 测试验证
        User res = userDao.findUserInfo(new User(1L, "10001"));
        logger.info("test result: {}", JSON.toJSONString(res));
    }
}
