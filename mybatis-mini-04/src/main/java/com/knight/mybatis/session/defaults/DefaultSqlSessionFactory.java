package com.knight.mybatis.session.defaults;

import com.knight.mybatis.binding.MapperRegistry;
import com.knight.mybatis.session.SqlSession;
import com.knight.mybatis.session.SqlSessionFactory;

/**
 * 默认 sqlSessionFactory 的实现
 * @desc
 * @date 2023/5/27
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
