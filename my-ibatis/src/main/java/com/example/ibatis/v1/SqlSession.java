package com.example.ibatis.v1;

/**
 * <p>
 *      sql会话
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/16 16:17
 **/
public class SqlSession {
    Configuration configuration;
    Executor executor;

    public SqlSession(Configuration configuration, Executor executor){
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * 单条查询
     * @param statementId
     * @param param
     * @param <T>
     * @return
     */
    public <T> T selectOne (String statementId, Object param){
        // 拿到sql语句
        String sql = Configuration.sqlMapping.getString(statementId);
        return this.executor.query(sql, param);
    }

    /**
     * 通过动态代理去执行方法，在invoke方法中执行查询
     * @param clazz     mapper接口
     * @param <T>       动态代理的类
     * @return
     */
    public <T> T getMapper(Class<?> clazz){
        return this.configuration.getMapper(clazz, this);
    }
}
