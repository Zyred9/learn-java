package com.example.learn.design.patterns.flyweight;

import java.sql.Connection;

/**
 * <p>
 *      连接池客户端
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/8 17:07
 **/
public class ConnectionPoolClient {


    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool();
        // 得到连接
        Connection connection = pool.getConnection();

        // TODO

        // 关闭
        pool.release(connection);
    }

}
