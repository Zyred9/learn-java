package com.example.ibatis.v2.session;

import com.example.ibatis.v2.executor.Executor;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 14:34
 **/
public class DefaultSqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = configuration.newExecutor();
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public Object selectOne(String statementId, Object[] args, Class object) {
        String sql = this.configuration.getMappedStatement(statementId);
        return executor.query(sql, args, object);
    }
}
