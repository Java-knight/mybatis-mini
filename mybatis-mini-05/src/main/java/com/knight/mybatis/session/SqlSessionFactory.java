package com.knight.mybatis.session;

/**
 * sql 会话工厂
 *
 * @desc
 * @date 2023/5/27
 */
public interface SqlSessionFactory {

    /**
     * 打开一个 sql session
     * @return
     */
    SqlSession openSession();
}
