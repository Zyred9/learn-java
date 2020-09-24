package com.example.fastbatis.session.defaults;

import com.example.fastbatis.builder.SqlBuilder;
import com.example.fastbatis.session.Configuration;
import com.example.fastbatis.session.SqlSession;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/23 14:00
 **/
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    // TODO
    // private Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T getMapper() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public Object selectOne(String statementId, Object[] args, Class object) {
        SqlBuilder sqlBuilder = this.configuration.getMappedStatement(statementId);
        return null;
    }
}
