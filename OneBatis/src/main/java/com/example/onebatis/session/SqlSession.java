package com.example.onebatis.session;

import com.example.onebatis.builder.SqlBuilder;
import com.example.test.mapper.UserMapper;

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
    Object selectList(SqlBuilder sqlBuilder, Object[] args, Class object);
}
