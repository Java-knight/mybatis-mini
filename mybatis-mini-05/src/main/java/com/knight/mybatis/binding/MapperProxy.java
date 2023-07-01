package com.knight.mybatis.binding;

import com.knight.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @desc 映射器代理类
 * @date 2023/4/15
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -8552037315199203455L;

    // 对 db 的所有操作 <interface.method, action>
    private SqlSession sqlSession;

    private final Class<T> mapperInterface;

    // 映射器缓存
    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 如果不是 Object 提供的 toString、hashCode 等方法, 则需要代理执行
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {  // 映射器方法
            final MapperMethod mapperMethod = cachedMapperMethod(method);
            return mapperMethod.execute(sqlSession, args);
        }
    }

    /**
     * 去缓存中获取 MapperMethod
     * @param method
     * @return
     */
    private MapperMethod cachedMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if (null == mapperMethod) {
            mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
