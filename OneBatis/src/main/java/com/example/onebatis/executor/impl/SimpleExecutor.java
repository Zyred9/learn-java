package com.example.onebatis.executor.impl;

import com.example.onebatis.builder.SqlBuilder;
import com.example.onebatis.handler.StatementHandler;
import com.example.onebatis.session.Configuration;

import java.util.List;

/**
 * <p>
 *          简单执行器
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class SimpleExecutor extends BaseExecutor {

    private Configuration configuration;
    private boolean autoCommit;

    public SimpleExecutor(Configuration configuration, boolean autoCommit) {
        this.configuration = configuration;
        this.autoCommit = autoCommit;
    }

    @Override
    public <T> List<T> query(Object[] parameters, SqlBuilder sqlBuilder) {
        StatementHandler statementHandler = new StatementHandler();
        return statementHandler.query(parameters, sqlBuilder, this.configuration, autoCommit);
    }

    @Override
    public int update(Object[] parameters, SqlBuilder sqlBuilder) {
        return super.update(parameters, sqlBuilder);
    }

}
