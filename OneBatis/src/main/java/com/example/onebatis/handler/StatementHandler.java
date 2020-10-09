package com.example.onebatis.handler;


import com.example.onebatis.builder.SqlBuilder;
import com.example.onebatis.session.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 查询预处理
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 16:48
 **/
public class StatementHandler {

    private ResultSetHandler resultSetHandler = new ResultSetHandler();

    public <T> List<T> query(Object[] parameters, SqlBuilder sqlBuilder, Configuration configuration, boolean autoCommit) {
        Connection connection = null;
        try {
            connection = configuration.getConnectionPool().getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlBuilder.getSql());
            ParameterHandler parameterHandler = new ParameterHandler(statement);
            // 构造 PreparedStatement 中的请求查询参数
            parameterHandler.setParameters(parameters);

            statement.execute();

            String resultType = sqlBuilder.getResultType();
            Class<?> pojo = Class.forName(resultType);

            return (List<T>) this.resultSetHandler.handlerResult(pojo, statement.getResultSet());
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            // 归还连接池
            configuration.getConnectionPool().close(connection);
        }
        return null;
    }


    public int update(Object[] args, SqlBuilder sqlBuilder, Configuration configuration){
        Connection connection = null;
        try {
            connection = configuration.getConnectionPool().getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlBuilder.getSql());
            ParameterHandler parameterHandler = new ParameterHandler(statement);
            // 构造 PreparedStatement 中的请求查询参数
            parameterHandler.setParameters(args);
            statement.execute();
            return statement.getUpdateCount();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // 归还连接池
            configuration.getConnectionPool().close(connection);
        }
        return 0;
    }
}
