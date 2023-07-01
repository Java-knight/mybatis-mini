package com.knight.mybatis.mapping;

/**
 * sql 指令类型
 * @desc
 * @author knight
 * @date 2023/6/15
 */
public enum SqlCommandType {

    UNKNOWN,   // 未知

    INSERT,    // 插入

    DELETE,    // 删除

    UPDATE,    // 更新

    SELECT;    // 查询
}
