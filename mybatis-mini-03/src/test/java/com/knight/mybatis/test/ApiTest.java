package com.knight.mybatis.test;

import com.knight.mybatis.binding.MapperRegistry;
import com.knight.mybatis.session.SqlSession;
import com.knight.mybatis.session.defaults.DefaultSqlSessionFactory;
import com.knight.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @desc
 * @author knight
 * @date 2023/6/15
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_MapperProxyFactory() {
        // (1) register mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("com.knight.mybatis.test.dao");

        // (2) 从 sqlSessionFactory get sqlSession
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // (3) get mapper
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // (4) test assert
        String res = userDao.findUserName("10001");
        logger.info("test result: {}", res);
    }
}
