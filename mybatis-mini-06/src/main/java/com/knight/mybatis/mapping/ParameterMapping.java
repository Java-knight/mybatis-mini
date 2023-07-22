package com.knight.mybatis.mapping;

import com.knight.mybatis.session.Configuration;
import com.knight.mybatis.type.JdbcType;

/**
 * 参数映射
 * @desc
 * @author knight
 * @date 2023/7/1
 */
public class ParameterMapping {

    // 配置项
    private Configuration configuration;

    // 属性
    private String property;

    // javaType = int
    private Class<?> javaType = Object.class;

    // jdbcType = numeric
    private JdbcType jdbcType;

    public ParameterMapping() {
    }

    public static class Builder {
        private ParameterMapping parameterMapping = new ParameterMapping();

        public Builder(Configuration configuration, String property) {
            parameterMapping.configuration = configuration;
            parameterMapping.property = property;
        }

        public Builder javaType(Class<?> javaType) {
            parameterMapping.javaType = javaType;
            return this;
        }

        public Builder jdbcType(JdbcType jdbcType) {
            parameterMapping.jdbcType = jdbcType;
            return this;
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getProperty() {
        return property;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }
}

