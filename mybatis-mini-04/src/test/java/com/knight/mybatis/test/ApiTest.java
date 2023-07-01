package com.knight.mybatis.test;

import com.knight.mybatis.io.Resources;
import com.knight.mybatis.session.SqlSession;
import com.knight.mybatis.session.SqlSessionFactory;
import com.knight.mybatis.session.SqlSessionFactoryBuilder;
import com.knight.mybatis.test.dao.IUserDao;
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

    @Test
    public void test_MapperProxyFactory() throws IOException {
        // (1) 从 SqlSessionFactory 中获取 SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // (2) 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // (3) 测试验证
        String res = userDao.findUserInfoById("10001");
        logger.info("test result: {}", res);

    }
}
