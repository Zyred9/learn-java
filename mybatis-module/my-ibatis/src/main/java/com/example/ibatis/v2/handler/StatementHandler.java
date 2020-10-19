package com.example.ibatis.v2.handler;

import com.example.ibatis.v2.pool.DefaultConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public <T> T query(String sql, Object[] parameters, Class<?> resultPojo) {
        Connection connection = null;
        try {

            connection = DefaultConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ParameterHandler parameterHandler = new ParameterHandler(statement);
            // 构造 PreparedStatement 中的请求查询参数
            parameterHandler.setParameters(parameters);

            statement.execute();

            return (T) this.resultSetHandler.handlerResult(resultPojo, statement.getResultSet());
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // 归还连接池
            DefaultConnectionPool.close(connection);
        }
        return null;
    }

}
