package com.example.test.mapper;

import com.example.test.entity.User;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public interface UserMapper {

    List<User> getUserPage(String phone);

}