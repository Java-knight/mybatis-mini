package com.knight.mybatis.session.defaults;

import com.knight.mybatis.session.Configuration;
import com.knight.mybatis.session.SqlSession;
import com.knight.mybatis.session.SqlSessionFactory;

/**
 * 默认 sqlSessionFactory 的实现
 * @desc
 * @date 2023/5/27
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration config) {
        this.configuration = config;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
