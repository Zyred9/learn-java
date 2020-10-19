package com.example.ibatis.v2.binding;

import com.example.ibatis.v2.session.DefaultSqlSession;

import java.lang.reflect.Proxy;

/**
 * <p>
 * 生产MapperProxy工厂
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 14:29
 **/
public class MapperProxyFactory<T> {

    private Class<T> mapperInterface;
    private Class<?> object;

    public MapperProxyFactory(Class<T> mapperInterface, Class<?> object) {
        this.mapperInterface = mapperInterface;
        this.object = object;
    }


    public T newInstance(DefaultSqlSession defaultSqlSession) {
        return
                (T) Proxy.newProxyInstance(
                        this.mapperInterface.getClassLoader(),
                        new Class[]{this.mapperInterface},
                        new MapperProxy(defaultSqlSession, object)
                );
    }

}
