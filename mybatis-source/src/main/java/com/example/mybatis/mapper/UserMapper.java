package com.example.mybatis.mapper;

import com.example.mybatis.domain.User;

import java.util.List;

public interface UserMapper {

    /**
     * 获取分页
     * @return
     */
    List<User> getUserPage();

}
