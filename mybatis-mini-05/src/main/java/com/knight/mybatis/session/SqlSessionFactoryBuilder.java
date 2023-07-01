package com.knight.mybatis.session;

import com.knight.mybatis.builder.xml.XMLConfigBuilder;
import com.knight.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * 构建 SqlSessionFactory 工厂
 * @desc
 * @author knight
 * @date 2023/6/15
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}
