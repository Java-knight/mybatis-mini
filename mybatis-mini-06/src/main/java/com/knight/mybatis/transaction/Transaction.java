package com.knight.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务接口
 * @desc
 * @author knight
 * @date 2023/7/1
 */
public interface Transaction {

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;

    /**
     * 提交事务
     * @throws SQLException
     */
    void commit() throws SQLException;

    /**
     * 回滚事务
     * @throws SQLException
     */
    void rollback() throws SQLException;

    /**
     * 关闭连接/事务
     * @throws SQLException
     */
    void close() throws SQLException;
}
