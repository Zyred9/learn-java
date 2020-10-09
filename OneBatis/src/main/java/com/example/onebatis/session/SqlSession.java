package com.example.onebatis.session;

import com.example.onebatis.builder.SqlBuilder;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * session 接口
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/23 13:59
 **/
public interface SqlSession {

    /**
     * 获取Mapper代理对象
     *
     * @param <T>
     * @param clazz
     * @return
     */
    <T> T getMapper(Class<?> clazz);

    /**
     * 得到configuration对象
     *
     * @return
     */
    Configuration getConfiguration();

    /**
     * 查询单条记录
     *
     * @param sqlBuilder
     * @param args
     * @param object
     * @return
     */
    <T> List<T> selectList(SqlBuilder sqlBuilder, Object[] args, Class object);

    /**
     * 单挑查询
     * @param args          sql参数
     * @param sqlBuilder    sql
     * @return
     */
    <T> T selectOne(SqlBuilder sqlBuilder, Object[] args, Class object);

    int insert(Object[] args, SqlBuilder sqlBuilder);

    int update(Object[] args, SqlBuilder sqlBuilder);

    Object flushStatements();


}
