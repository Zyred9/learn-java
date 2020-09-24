package com.example.fastbatis.session;

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
     * @return
     */
    <T> T getMapper();

    /**
     * 得到configuration对象
     *
     * @return
     */
    Configuration getConfiguration();

    /**
     * @param statementId
     * @param args
     * @param object
     * @return
     */
    Object selectOne(String statementId, Object[] args, Class object);
}
