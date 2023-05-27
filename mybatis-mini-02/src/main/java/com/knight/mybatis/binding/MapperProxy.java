package com.knight.mybatis.binding;

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
    private Map<String, String> sqlSession;

    private final Class<T> mapperInterface;

    public MapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 如果不是 Object 提供的 toString、hashCode 等方法, 则需要代理执行
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return "你的操作被代理了!" + sqlSession.get(mapperInterface.getName() + "." + method.getName());
        }
    }
}
