package com.example.learn.design.patterns.prototype.shallow;

import java.io.Serializable;

/**
 * <p>
 *      浅克隆
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/27 16:34
 **/
public class ShallowClone implements Serializable {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ShallowClone{" +
                "username='" + username + '\'' +
                '}';
    }
}
