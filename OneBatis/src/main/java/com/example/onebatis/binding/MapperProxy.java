package com.example.onebatis.binding;

import com.example.onebatis.builder.SqlBuilder;
import com.example.onebatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * @author Clinton Begin
 * @author Eduardo Macarron
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private SqlSession sqlSession;
    private Class object;

    public MapperProxy(SqlSession sqlSession, Class object) {
        this.sqlSession = sqlSession;
        this.object = object;
    }

    /**
     * 所有Mapper接口的方法调用都会走到这里
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperInterface = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String statementId = mapperInterface + "." + methodName;
        // 如果根据接口类型+方法名能找到映射的SQL，则执行SQL
        if (sqlSession.getConfiguration().hasStatement(statementId)) {
            SqlBuilder sqlBuilder = sqlSession.getConfiguration().getMappedStatement(statementId);
            // 执行sql查询
            return sqlSession.selectList(sqlBuilder, args, object);
        }
        // 否则直接执行被代理对象的原方法
        return method.invoke(proxy, args);
    }
}
