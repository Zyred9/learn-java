package com.example.ibatis.v1;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * <p>
 * 全局配置文件，目前主要是通过 properties 文件加载sql语句的
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/16 16:13
 **/
public class Configuration {

    public static final ResourceBundle sqlMapping;

    static {
        // 读取sql.properties文件，类被初始化的时候，会读取文件中的内容
        sqlMapping = ResourceBundle.getBundle("sql");
    }

    /**
     * 通过class动态代理mapper接口
     *
     * @param clazz      mapper接口
     * @param sqlSession sqlSession
     * @param <T>        被代理的对象
     * @return
     */
    public <T> T getMapper(Class<?> clazz, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{clazz},
                new MapperProxy(sqlSession));
    }
}
