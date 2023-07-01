package com.knight.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import com.knight.mybatis.session.Configuration;
import com.knight.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * mapper 注册表
 *
 * @desc 提供包路径的扫描和映射器代理类注册机制服务, 完成接口对象的代理类注册
 * @date 2023/5/27
 */
public class MapperRegistry {

    /**
     * 配置项
     */
    private Configuration config;

    /**
     * 将已添加的映射器[mapper]代理加入到 HashMap 缓存中
     * <type, MapperProxyFactory>
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public MapperRegistry(Configuration config) {
        this.config = config;
    }

    /**
     * 根据类型从 Map 注册表中获取 映射器mapper
     * @param type 类型
     * @param sqlSession
     * @return
     * @param <T>
     */
    public <T> T getMapper(Class<?> type, SqlSession sqlSession) {
        final MapperProxyFactory<T>  mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + "is not know to the MapperRegistry. ");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    /**
     * 根据类型向 Map注册表 添加 mapper映射器
     * @param type
     * @param <T>
     */
    public <T> void addMapper(Class<T> type) {
        /** Mapper 必须是接口才会注册 */
        if (type.isInterface()) {
            if (hasMapper(type)) {
                // 如果已经存在, 直接报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry. ");
            }
            // 注册映射器代理工厂
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    /**
     * 根据包名批量向 Map注册表 添加 mapper映射器
     * @param packageName
     */
    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }
}
