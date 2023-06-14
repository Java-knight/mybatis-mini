package com.knight.mybatis.session;

/**
 * sql 会话
 *
 * @desc 定义执行 SQL 语句的标准, 获取映射器及管理事务等
 * @date 2023/5/27
 */
public interface SqlSession {

    /**
     * 根据指定的 sqlId 获取一条记录的封装对象
     * @param statement sqlId
     * @param <T> 返回对象类型 (封装之后的对象类型)
     * @return 映射器对象mapper object 封装之后的对象
     */
    <T> T selectOne(String statement);

    /**
     * 根据指定的 sqlId 获取一条记录的封装对象
     * 一般这个参数对象是一个 pojo\Map
     * @param <T>
     * @param statement sqlId
     * @param parameter 传递参数对象
     * @return
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * 获取应摄取, 这里使用了泛型, 使得类型安全
     * @param <T>
     * @param type
     * @return
     */
    <T> T getMapper(Class<T> type);
}
