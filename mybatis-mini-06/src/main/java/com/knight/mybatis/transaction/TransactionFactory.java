package com.knight.mybatis.transaction;

import com.knight.mybatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 事务工厂
 * @desc
 * @author knight
 * @date 2023/7/1
 */
public interface TransactionFactory {

    /**
     * 根据 Connection 创建 Transaction
     * @param conn
     * @return
     */
    Transaction newTransaction(Connection conn);

    /**
     * 根据 dataSource 和 level 创建 Transaction
     * @param dataSource 数据源
     * @param level      事务隔离级别
     * @param autoCommit 是否自动提交
     * @return
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);
}
