package com.example.ibatis.v2.session;

/**
 * <p>
 *
 * </p>
 *
 * @author Zyred
 * @project spring-security
 * @date 2020/9/17 19:54
 **/
public interface SqlSession {

    /**
     * 创建sqlSession
     * @param statementId
     * @param args
     * @param object
     * @param <T>
     * @return
     */
    <T> T selectOne(String statementId, Object[] args, Class object);

    /**
     * 根据mapper创建实体类
     * @param mapper
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> mapper);

}
