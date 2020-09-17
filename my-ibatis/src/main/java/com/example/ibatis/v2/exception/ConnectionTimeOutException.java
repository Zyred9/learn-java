package com.example.ibatis.v2.exception;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 17:25
 **/
public class ConnectionTimeOutException extends RuntimeException {

    public ConnectionTimeOutException() {
        super();
    }

    public ConnectionTimeOutException(String message) {
        super(message);
    }
}
