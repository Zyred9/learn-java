package com.example.mybatis.mapper;

import com.example.mybatis.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 获取分页
     * @param phone 电话
     * @return
     */
    List<User> getUserPage(@Param("phone") String phone);

}
