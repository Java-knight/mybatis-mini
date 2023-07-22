package com.knight.mybatis.mapping;

import com.knight.mybatis.session.Configuration;

import java.util.Map;

/**
 * 映射语句类
 * @desc
 * @author knight
 * @date 2023/6/15
 */
public class MappedStatement {

    // 配置项
    private Configuration configuration;

    // mapped id
    private String id;

    // sql 语句类型
    private SqlCommandType sqlCommandType;

    // 参数类型
    private BoundSql boundSql;

    private MappedStatement() {
        // constructor disable
    }

    /**
     * 建造者
     */
    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration config, String id, SqlCommandType sqlCommandType, BoundSql boundSql) {
            mappedStatement.configuration = config;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.boundSql = boundSql;
        }


        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            return mappedStatement;
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }


    public String getId() {
        return id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public BoundSql getBoundSql() {
        return boundSql;
    }
}
