package com.knight.mybatis.builder;

import com.knight.mybatis.session.Configuration;

/**
 * 构建器的父类, 建造者模式
 * @desc
 * @author knight
 * @date 2023/6/15
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration config) {
        this.configuration = config;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
