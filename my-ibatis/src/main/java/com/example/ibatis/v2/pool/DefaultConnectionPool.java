package com.example.ibatis.v2.pool;

import com.example.ibatis.v2.exception.ConnectionTimeOutException;
import com.example.ibatis.v2.session.Configuration;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

/**
 * <p>
 * 连接池
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 16:58
 **/
public class DefaultConnectionPool {

    private static LinkedList<Connection> pool;

    private DefaultConnectionPool() {
    }

    static {
        String size = getProperties("connection.size");
        size = size == null || size == "" ? "2" : size;
        Integer connectionSize = Integer.parseInt(size);
        pool = new LinkedList<>();
        for (Integer i = 1; i <= connectionSize; i++) {
            pool.add(createConnection());
        }
    }

    public synchronized static Connection getConnection() {
        Connection first = pool.getFirst();

        int counter = 0;
        while (pool.getFirst() == null) {
            counter++;
            if (counter == 50000) {
                throw new ConnectionTimeOutException("Database connection Exception.");
            }
        }
        pool.remove(first);
        return first;
    }

    public synchronized static void close(Connection conn) {
        pool.addLast(conn);
    }

    @SneakyThrows
    private static Connection createConnection() {
        String driverClassName = getProperties("jdbc.driver");
        String userName = getProperties("jdbc.username");
        String url = getProperties("jdbc.url");
        String password = getProperties("jdbc.password");

        Class.forName(driverClassName);
        return DriverManager.getConnection(url, userName, password);
    }

    private static String getProperties(String name) {
        return Configuration.properties.getString(name);
    }
}
