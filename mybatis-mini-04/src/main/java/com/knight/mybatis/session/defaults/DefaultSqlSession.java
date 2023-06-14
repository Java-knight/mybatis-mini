package com.knight.mybatis.session.defaults;

import com.knight.mybatis.binding.MapperRegistry;
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
    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("your operation is proxy! statement: " + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("your operation is proxy! statement: " + statement + ", param: " + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
