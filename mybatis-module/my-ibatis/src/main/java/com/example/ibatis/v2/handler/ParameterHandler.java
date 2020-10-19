package com.example.ibatis.v2.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 *      sql参数处理
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 17:16
 **/
public class ParameterHandler {

    private PreparedStatement preparedStatement;

    public ParameterHandler(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    /**
     * 从方法中获取参数，遍历设置SQL中的？占位符
     * @param parameters
     */
    public void setParameters(Object[] parameters) {
        try {
            // PreparedStatement的序号是从1开始的
            for (int i = 0; i <parameters.length; i++) {
                int k =i+1;
                if (parameters[i] instanceof Integer) {
                    preparedStatement.setInt(k, (Integer) parameters[i]);
                } else if (parameters[i] instanceof Long) {
                    preparedStatement.setLong(k, (Long) parameters[i]);
                } else if (parameters[i] instanceof String) {
                    preparedStatement.setString(k , String.valueOf(parameters[i]));
                } else if (parameters[i] instanceof Boolean) {
                    preparedStatement.setBoolean(k, (Boolean) parameters[i]);
                } else {
                    preparedStatement.setString(k, String.valueOf(parameters[i]));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
