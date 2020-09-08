package com.example.socket.mvc;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *      消息实体类
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/8 15:35
 * @company 中再云图技术有限公司
 **/
public class MessageEntity implements Serializable {

    private String userName;

    private String userId;

    private String message;

    private String time;


    public String getUserName() {
        return userName;
    }

    public MessageEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public MessageEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MessageEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getTime() {
        return time;
    }

    public MessageEntity setTime() {
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return this;
    }
}
