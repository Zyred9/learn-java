package com.example.learn.design.patterns.flyweight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

/**
 * <p>
 *      享元模式：
 *          对象的重复利用，降低重复多次创建可复用的对像的成本
 * </p>
 *
 * @author Zyred
 * @project spring-security
 * @date 2020/9/7 21:58
 **/
public class ConnectionPool {

    private Vector<Connection> pool;

    private int initializePoolSize = 127;
    private String userName = "root";
    private String password = "root";
    private String url = "jdbc:mysql://localhost:3306/test";
    private String driverClassName ="com.mysql.jdbc.Driver";

    public ConnectionPool() {
        try {
            pool = new Vector<Connection>();
            Class.forName(this.driverClassName);
            for (int i = 0; i < initializePoolSize; i++) {
                Connection conn = DriverManager.getConnection(this.url, this.userName, this.password);
                this.pool.add(conn);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public synchronized Connection getConnection(){
        Connection connection = this.pool.get(0);
        this.pool.remove(connection);
        return connection;
    }


    public synchronized void release(Connection conn){
        this.pool.add(conn);
    }
}
