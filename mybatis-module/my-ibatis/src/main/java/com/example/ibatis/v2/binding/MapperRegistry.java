package com.example.ibatis.v2.binding;

import com.example.ibatis.v2.session.DefaultSqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 13:58
 **/
public class MapperRegistry {

    private static Map<Class<?>, MapperProxyFactory> knownMappers = new HashMap<>();

    public void addMapper(Class<?> mapper, Class<?> pojo) {
        knownMappers.put(mapper, new MapperProxyFactory(mapper, pojo));
    }

    public <T> T getMapper(Class<T> clazz, DefaultSqlSession sqlSession) {
        MapperProxyFactory proxyFactory = knownMappers.get(clazz);
        if (proxyFactory == null) {
            throw new RuntimeException("Type: " + clazz + " can not find");
        }
        return (T) proxyFactory.newInstance(sqlSession);
    }
}
