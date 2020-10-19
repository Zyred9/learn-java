package com.example.ibatis.v2.executor;

import com.example.ibatis.v2.handler.StatementHandler;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 15:22
 **/
public class SimpleExecutor implements Executor{

    @Override
    public <T> T query(String sql, Object[] parameters, Class<?> resultPojo) {
        StatementHandler statementHandler = new StatementHandler();
        return (T)statementHandler.query(sql, parameters, resultPojo);
    }

    @Override
    public int update(String statementId, Object[] parameter) {
        return 0;
    }

}
