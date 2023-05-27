package com.knight.mybatis.binding;


import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @desc 映射器代理工厂
 * @date 2023/4/15
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    /**
     * 获取 mapper代理(jdk的动态代理)
     * @param sqlSession
     * @return
     */
    public T newInstance(Map<String, String> sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
