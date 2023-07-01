package com.knight.mybatis.session.defaults;

import com.knight.mybatis.mapping.MappedStatement;
import com.knight.mybatis.session.Configuration;
import com.knight.mybatis.session.SqlSession;

/**
 * 默认 sqlSession 的实现类
 * @desc
 * @date 2023/5/27
 */
public class DefaultSqlSession implements SqlSession {

    /**
     * 映射注册机
     */
    private Configuration configuration;

    public DefaultSqlSession(Configuration config) {
        this.configuration = config;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("your operation is proxy! statement: " + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("your operation is proxy! " + "\nstatement:" + statement + "\nparam: " + parameter + "\nexecute sql: " + mappedStatement.getSql());
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
}
