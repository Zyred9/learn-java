package com.example.sys.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Setter
@Getter
public class User {

    private String userName;

    private String password;

    private Integer loginCounter;

    private String sessionId;

    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;

            if (Objects.equals(user.getPassword(), this.password)
                    && Objects.equals(user.getUserName(), this.userName)) {
                return true;
            }
        }
        return false;
    }
}
