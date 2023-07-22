package com.knight.mybatis.builder;

import com.knight.mybatis.session.Configuration;
import com.knight.mybatis.type.TypeAliasRegistry;

/**
 * 构建器的父类, 建造者模式
 * @desc
 * @author knight
 * @date 2023/6/15
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration config) {
        this.configuration = config;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
