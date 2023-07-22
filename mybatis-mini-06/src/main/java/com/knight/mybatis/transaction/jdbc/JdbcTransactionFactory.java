package com.knight.mybatis.transaction.jdbc;

import com.knight.mybatis.session.TransactionIsolationLevel;
import com.knight.mybatis.transaction.Transaction;
import com.knight.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * jdbc 事务工厂
 * @desc
 * @author knight
 * @date 2023/7/1
 */
public class JdbcTransactionFactory implements TransactionFactory {
    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
