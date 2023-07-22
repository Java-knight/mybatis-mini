package com.knight.mybatis.session;

import java.sql.Connection;

/**
 * 事务的隔离级别
 * @desc
 * @author knight
 * @date 2023/7/1
 */
public enum TransactionIsolationLevel {

    NONE(Connection.TRANSACTION_NONE),  // 未知级别
    READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),  // 未提交读
    READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),      // 已提交读[大厂设置]
    REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),    // 可重复读
    SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);          // 序列化


    private final int level;

    TransactionIsolationLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
