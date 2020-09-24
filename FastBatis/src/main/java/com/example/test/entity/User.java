package com.example.test.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/24 15:34
 **/
@Setter
@Getter
@ToString
public class User implements Serializable {

    private String id;
    private String username;
    private String password;
    private String address;
    private String phone;
}
