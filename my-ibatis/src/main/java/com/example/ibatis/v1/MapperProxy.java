package com.example.ibatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>
 *     代理mapper接口的对象
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/16 16:25
 **/
public class MapperProxy implements InvocationHandler {
    private final static String DOT = ".";
    SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String clazzName = method.getDeclaringClass().getName();
        String methodName = method.getName();

        return this.sqlSession.selectOne(clazzName.concat(DOT).concat(methodName), args[0]);
    }
}
